package uz.pdp.telegram_bot.apartmentBot.bot.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UpdateProcessor;
import uz.pdp.telegram_bot.apartmentBot.bot.util.buttons.Buttons_eng;

import java.util.List;

public class AuthSendMessageProcess {
    private static final Logger log = LoggerFactory.getLogger(AuthSendMessageProcess.class.getName());
    public static InlineKeyboardMarkup INLINE_MARKUP_AUTH = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(Buttons_eng.BTN_SIGN_UP))
            .keyboardRow(List.of(Buttons_eng.BTN_LOGIN))
            .build();

    public static void sendMessage(Update update, TelegramLongPollingBot bot) {
        try {
            bot.execute(SendMessage.builder()
                    .text("Login or sign up")
                    .chatId(UpdateProcessor.extractChatId(update))
                    .replyMarkup(INLINE_MARKUP_AUTH)
                    .build()
            );
        } catch (TelegramApiException e) {
            log.error(e.getLocalizedMessage());
        }
    }

    public static void editMessage(Update update, TelegramLongPollingBot bot) {
        try {
            bot.execute(EditMessageText.builder()
                    .text("Login or sign up")
                    .messageId(UpdateProcessor.extractMessageId(update))
                    .chatId(UpdateProcessor.extractChatId(update))
                    .replyMarkup(INLINE_MARKUP_AUTH)
                    .build()
            );
        } catch (TelegramApiException e) {
            log.error(e.getLocalizedMessage());
        }
    }
}
