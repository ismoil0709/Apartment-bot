package uz.pdp.telegram_bot.apartmentBot.bot.auth.steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.telegram_bot.apartmentBot.bot.regex.Regex;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForSignUp;
import uz.pdp.telegram_bot.apartmentBot.bot.util.GetAndSetStates;
import uz.pdp.telegram_bot.apartmentBot.bot.util.Markup;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UpdateProcessor;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UtilLists;
import uz.pdp.telegram_bot.apartmentBot.bot.util.buttons.Buttons_eng;
import uz.pdp.telegram_bot.apartmentBot.model.Client;

import java.util.List;

public class LoginEmailStep {
    private static final Logger log = LoggerFactory.getLogger(LoginEmailStep.class.getName());
    private static final InlineKeyboardMarkup INLINE_MARKUP_BACK_STATE = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(Buttons_eng.BTN_BACK))
            .build();

    public static void process(Update update, TelegramLongPollingBot bot) {
        if (Regex.emailValidator(update.getMessage().getText())) {
            GetAndSetStates.setSignUpState(update, StateForSignUp.LOGIN_WRITE_PASSWORD);
            Client client = new Client();
            for (Client c : UtilLists.clientLoginMap.values()) {
                if (c.getId().equals(UpdateProcessor.extractChatId(update))) client = c;
            }
            client.setEmail(update.getMessage().getText());
            UtilLists.clientLoginMap.put(UpdateProcessor.extractChatId(update), client);
            try {
                bot.execute(SendMessage.builder()
                        .text("Enter password : ")
                        .replyMarkup(INLINE_MARKUP_BACK_STATE)
                        .chatId(UpdateProcessor.extractChatId(update))
                        .build()
                );
            } catch (TelegramApiException e) {
                log.error(e.getLocalizedMessage());
            }
        } else {
            try {
                bot.execute(SendMessage.builder()
                        .text("Invalid format ‼️\nPlease re enter")
                                .replyMarkup(Markup.INLINE_MARKUP_BACK_STATE)
                        .chatId(UpdateProcessor.extractChatId(update))
                        .build()
                );
                GetAndSetStates.setSignUpState(update, StateForSignUp.LOGIN_WRITE_EMAIL);
            } catch (TelegramApiException e) {
                log.error(e.getLocalizedMessage());
            }
        }
    }

    public static void sendMessageEmail(Update update, TelegramLongPollingBot bot) {
        GetAndSetStates.setSignUpState(update, StateForSignUp.LOGIN_WRITE_EMAIL);
        try {
            bot.execute(SendMessage.builder()
                    .replyMarkup(INLINE_MARKUP_BACK_STATE)
                    .text("Enter email")
                    .chatId(UpdateProcessor.extractChatId(update))
                    .build()
            );
        } catch (TelegramApiException e) {
            log.error(e.getLocalizedMessage());
        }
    }

    public static void sendMessageEmailWithEdition(Update update, TelegramLongPollingBot bot) {
        GetAndSetStates.setSignUpState(update, StateForSignUp.LOGIN_WRITE_EMAIL);
        try {
            bot.execute(EditMessageText.builder()
                    .replyMarkup(INLINE_MARKUP_BACK_STATE)
                    .text("Enter email")
                    .messageId(UpdateProcessor.extractMessageId(update))
                    .chatId(UpdateProcessor.extractChatId(update))
                    .build()
            );
        } catch (TelegramApiException e) {
            log.error(e.getLocalizedMessage());
        }
    }
}
