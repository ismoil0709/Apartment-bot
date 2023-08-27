package uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForBack;
import uz.pdp.telegram_bot.apartmentBot.bot.util.*;
import uz.pdp.telegram_bot.apartmentBot.bot.util.buttons.Buttons_eng;

import javax.ws.rs.QueryParam;
import java.util.List;
public class Callback_searchApartment {
    private static final Logger log = LoggerFactory.getLogger(Callback_searchApartment.class.getName());

    public static InlineKeyboardMarkup INLINE_MARKUP_SEARCH_APARTMENT_STATE = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(Buttons_eng.BTN_RECENTLY_UPLOADED))
            .keyboardRow(List.of(Buttons_eng.BTN_SEARCH_WITH_PRICE))
            .keyboardRow(List.of(Buttons_eng.BTN_SEARCH_WITH_NUMBER_OF_ROOMS))
            .keyboardRow(List.of(Buttons_eng.BTN_SEARCH_WITH_CITY))
            .keyboardRow(List.of(Buttons_eng.BTN_BACK))
            .build();

    public static void process(Update update, TelegramLongPollingBot bot) {
        GetAndSetStates.setBackButtonState(update, StateForBack.SEARCH_APARTMENT);
        try {
            bot.execute(DeleteMessage.builder()
                    .chatId(UpdateProcessor.extractChatId(update))
                    .messageId(UpdateProcessor.extractMessageId(update))
                    .build()
            );
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
        try {
            bot.execute(SendMessage.builder()
                    .chatId(UpdateProcessor.extractChatId(update))
                    .text("Enter text or press button")
                    .replyMarkup(INLINE_MARKUP_SEARCH_APARTMENT_STATE)
                    .build());
        } catch (TelegramApiException e) {
            log.error(e.getLocalizedMessage());
        }
    }

    public static void processEditMessage(Update update, TelegramLongPollingBot bot) {
        GetAndSetStates.setBackButtonState(update, StateForBack.SEARCH_APARTMENT);
        try {
            bot.execute(EditMessageText.builder()
                    .chatId(UpdateProcessor.extractChatId(update))
                    .messageId(UpdateProcessor.extractMessageId(update))
                    .text("Enter text or press button")
                    .replyMarkup(INLINE_MARKUP_SEARCH_APARTMENT_STATE)
                    .build());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
