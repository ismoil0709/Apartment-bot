package uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess.editApartment;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.telegram_bot.apartmentBot.bot.repo.impl.ApartmentRepoImpl;
import uz.pdp.telegram_bot.apartmentBot.bot.util.SendApartment;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UpdateProcessor;
import uz.pdp.telegram_bot.apartmentBot.bot.util.buttons.Buttons_eng;
import uz.pdp.telegram_bot.apartmentBot.model.Apartment;

import java.util.List;

public class Callback_edit_apartment {
    private static final InlineKeyboardMarkup EDIT_MARKUP = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(Buttons_eng.BTN_EDIT_PHOTO))
            .keyboardRow(List.of(Buttons_eng.BTN_EDIT_LOCATION))
            .keyboardRow(List.of(Buttons_eng.BTN_EDIT_PRICE))
            .keyboardRow(List.of(Buttons_eng.BTN_EDIT_CITY))
            .keyboardRow(List.of(Buttons_eng.BTN_EDIT_DESCRIPTION))
            .keyboardRow(List.of(Buttons_eng.BTN_EDIT_ROOMS))
            .keyboardRow(List.of(Buttons_eng.BTN_EDIT_BACK))
            .build();

    public static void sendMessage(Update update, TelegramLongPollingBot bot) {
        try {
            bot.execute(SendMessage.builder()
                    .text("Choose : ")
                    .chatId(UpdateProcessor.extractChatId(update))
                    .replyMarkup(EDIT_MARKUP)
                    .build()
            );
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendEditMessage(Update update, TelegramLongPollingBot bot) {
        try {
            bot.execute(EditMessageText.builder()
                    .text("Choose : ")
                    .messageId(UpdateProcessor.extractMessageId(update))
                    .chatId(UpdateProcessor.extractChatId(update))
                    .replyMarkup(EDIT_MARKUP)
                    .build()
            );
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

}
