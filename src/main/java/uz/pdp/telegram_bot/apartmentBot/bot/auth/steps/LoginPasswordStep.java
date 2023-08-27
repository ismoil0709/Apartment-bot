package uz.pdp.telegram_bot.apartmentBot.bot.auth.steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess.Home_sate;
import uz.pdp.telegram_bot.apartmentBot.bot.regex.Regex;
import uz.pdp.telegram_bot.apartmentBot.bot.repo.impl.ClientRepoImpl;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForSignUp;
import uz.pdp.telegram_bot.apartmentBot.bot.util.GetAndSetStates;
import uz.pdp.telegram_bot.apartmentBot.bot.util.Markup;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UpdateProcessor;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UtilLists;
import uz.pdp.telegram_bot.apartmentBot.bot.util.buttons.Buttons_eng;
import uz.pdp.telegram_bot.apartmentBot.model.Client;

import java.util.ArrayList;
import java.util.List;

public class LoginPasswordStep {
    private static final Logger log = LoggerFactory.getLogger(LoginPasswordStep.class.getName());

    public static InlineKeyboardMarkup INLINE_MARKUP_REENTER = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(Buttons_eng.BTN_REENTER))
            .keyboardRow(List.of(Buttons_eng.BTN_BACK))
            .build();
    public static void process(Update update, TelegramLongPollingBot bot) {
        if (Regex.passwordValidator(update.getMessage().getText())) {
            Client client = UtilLists.clientLoginMap.get(UpdateProcessor.extractChatId(update));
            GetAndSetStates.setSignUpState(update, StateForSignUp.LOGIN_FINISH);
            client.setPassword(update.getMessage().getText());
            boolean isTrue = false;
            for (Client c : UtilLists.clientSignUpMap.values()) {
                if (c.getId().equals(client.getId()) && c.getEmail().equals(client.getEmail()) && c.getPassword().equals(client.getPassword())) {
                    client = c;
                    isTrue = true;
                    break;
                }
            }
            if (isTrue) {
                try {
                    bot.execute(SendMessage.builder()
                            .text("Successfully ✅")
                            .replyMarkup(Home_sate.INLINE_MARKUP_HOME_STATE)
                            .chatId(UpdateProcessor.extractChatId(update))
                            .build());
                } catch (TelegramApiException e) {
                    log.error(e.getLocalizedMessage());
                }
                client.setApartment(new ArrayList<>());
                client.setState(StateForSignUp.LOGIN_FINISH);
                ClientRepoImpl.getInstance().put(client);
            } else {
                try {
                    bot.execute(SendMessage.builder()
                            .text("Not Found \uD83D\uDD34")
                            .replyMarkup(INLINE_MARKUP_REENTER)
                            .chatId(UpdateProcessor.extractChatId(update))
                            .build()
                    );
                } catch (TelegramApiException e) {
                    log.error(e.getLocalizedMessage());
                }
            }
        } else {
            try {
                bot.execute(SendMessage.builder()
                        .text("Invalid format ‼️\nPlease re enter")
                        .replyMarkup(Markup.INLINE_MARKUP_BACK_STATE)
                        .chatId(UpdateProcessor.extractChatId(update))
                        .build()
                );
                GetAndSetStates.setSignUpState(update, StateForSignUp.LOGIN_WRITE_PASSWORD);
            } catch (TelegramApiException e) {
                log.error(e.getLocalizedMessage());
            }
        }
    }

    public static void sendMessagePassword(Update update, TelegramLongPollingBot bot) {
        GetAndSetStates.setSignUpState(update, StateForSignUp.LOGIN_WRITE_PASSWORD);
        try {
            bot.execute(SendMessage.builder()
                    .text("Enter password : ")
                    .chatId(UpdateProcessor.extractChatId(update))
                    .build()
            );
        } catch (TelegramApiException e) {
            log.error(e.getLocalizedMessage());
        }
    }
}
