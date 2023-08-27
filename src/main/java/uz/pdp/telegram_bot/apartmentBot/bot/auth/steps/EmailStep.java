package uz.pdp.telegram_bot.apartmentBot.bot.auth.steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.telegram_bot.apartmentBot.bot.regex.Regex;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForSignUp;
import uz.pdp.telegram_bot.apartmentBot.bot.util.GetAndSetStates;
import uz.pdp.telegram_bot.apartmentBot.bot.util.Markup;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UpdateProcessor;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UtilLists;
import uz.pdp.telegram_bot.apartmentBot.model.Client;

public class EmailStep {
    private static final Logger log = LoggerFactory.getLogger(EmailStep.class.getName());

    public static void process(Update update, TelegramLongPollingBot bot) {
        if (Regex.emailValidator(update.getMessage().getText())) {
            Client client = new Client();
            GetAndSetStates.setSignUpState(update, StateForSignUp.WRITE_PASSWORD);
            for (Client c : UtilLists.clientSignUpMap.values()) {
                if (c.getId().equals(UpdateProcessor.extractChatId(update))) client = c;
            }
            client.setEmail(update.getMessage().getText());
            UtilLists.clientSignUpMap.put(UpdateProcessor.extractChatId(update), client);
            try {
                bot.execute(SendMessage.builder()
                        .text("Enter password ")
                        .chatId(UpdateProcessor.extractChatId(update))
                        .replyMarkup(Markup.INLINE_MARKUP_BACK_STATE)
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
                GetAndSetStates.setSignUpState(update, StateForSignUp.WRITE_EMAIL);
            } catch (TelegramApiException e) {
                log.error(e.getLocalizedMessage());
            }
        }
    }

    public static void sendMessageEmail(Update update, TelegramLongPollingBot bot) {
        GetAndSetStates.setSignUpState(update, StateForSignUp.WRITE_EMAIL);
        try {
            bot.execute(SendMessage.builder()
                    .text("Enter email")
                    .replyMarkup(Markup.INLINE_MARKUP_BACK_STATE)
                    .chatId(UpdateProcessor.extractChatId(update))
                    .build()
            );
        } catch (TelegramApiException e) {
            log.error(e.getLocalizedMessage());
        }
    }
}
