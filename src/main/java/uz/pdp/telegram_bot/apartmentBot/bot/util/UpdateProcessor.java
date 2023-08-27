package uz.pdp.telegram_bot.apartmentBot.bot.util;

import org.telegram.telegrambots.meta.api.objects.Update;

public class UpdateProcessor {
    public static String extractChatId(Update update) {
        if (update.hasMessage()) {
            return update.getMessage().getChatId().toString();
        } else if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getMessage().getChatId().toString();
        }
        throw new IllegalArgumentException("Chat ID not found.");
    }

    public static Integer extractMessageId(Update update) {
        if (update.hasMessage()) {
            return update.getMessage().getMessageId();
        } else if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getMessage().getMessageId();
        }
        throw new IllegalArgumentException("Chat ID not found.");
    }
}
