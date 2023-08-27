package uz.pdp.telegram_bot.apartmentBot.bot.auth.steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
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

public class PasswordStep {
    private static final Logger log = LoggerFactory.getLogger(PasswordStep.class.getName());
    public static void process(Update update, TelegramLongPollingBot bot) {
        if (Regex.passwordValidator(update.getMessage().getText())) {
            Client client = new Client();
            for (Client c : UtilLists.clientSignUpMap.values()) {
                if (c.getId().equals(UpdateProcessor.extractChatId(update))) client = c;
            }
            client.setPassword(update.getMessage().getText());
            UtilLists.clientSignUpMap.put(UpdateProcessor.extractChatId(update), client);
            try {
                bot.execute(SendMessage.builder()
                        .text("Successfully ✅")
                        .replyMarkup(InlineKeyboardMarkup.builder()
                                .keyboardRow(List.of(Buttons_eng.BTN_LOGIN))
                                .build())
                        .chatId(UpdateProcessor.extractChatId(update))
                        .build()
                );
            } catch (TelegramApiException e) {
                log.error(e.getLocalizedMessage());
            }
        }else {
            try {
                bot.execute(SendMessage.builder()
                        .text("Invalid format ‼️\nPlease re enter")
                                .replyMarkup(Markup.INLINE_MARKUP_BACK_STATE)
                        .chatId(UpdateProcessor.extractChatId(update))
                        .build()
                );
                GetAndSetStates.setSignUpState(update, StateForSignUp.WRITE_PASSWORD);
            } catch (TelegramApiException e) {
                log.error(e.getLocalizedMessage());
            }
        }
    }
}
