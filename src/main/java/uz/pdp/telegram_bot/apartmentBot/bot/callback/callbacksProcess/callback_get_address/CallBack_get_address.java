package uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess.callback_get_address;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendLocation;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForBack;
import uz.pdp.telegram_bot.apartmentBot.bot.util.GetAndSetStates;
import uz.pdp.telegram_bot.apartmentBot.bot.util.buttons.Buttons_eng;
import uz.pdp.telegram_bot.apartmentBot.bot.util.SendApartment;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UpdateProcessor;
import uz.pdp.telegram_bot.apartmentBot.model.Apartment;

import java.util.List;

public class CallBack_get_address {
    private static final Logger log = LoggerFactory.getLogger(CallBack_get_address.class.getName());
    public static InlineKeyboardMarkup INLINE_MARKUP_BACK_STATE = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(Buttons_eng.BTN_BACK))
            .build();

    public static void sendLocation(Update update, TelegramLongPollingBot bot, List<Apartment> apartments) {
        GetAndSetStates.setBackButtonState(update, StateForBack.GET_ADDRESS);
        Apartment apartment = apartments.get(SendApartment.index);
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
            bot.execute(SendLocation.builder()
                    .chatId(UpdateProcessor.extractChatId(update))
                    .replyMarkup(INLINE_MARKUP_BACK_STATE)
                    .latitude(apartment.getAddress().getLatitude())
                    .longitude(apartment.getAddress().getLongitude())
                    .build());
        } catch (TelegramApiException e) {
            log.error(e.getLocalizedMessage());
        }
    }
}
