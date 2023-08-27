package uz.pdp.telegram_bot.apartmentBot.bot.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess.Callback_delete_apartment;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForAddressBack;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForGetList;
import uz.pdp.telegram_bot.apartmentBot.bot.util.buttons.Buttons_eng;
import uz.pdp.telegram_bot.apartmentBot.model.Apartment;

import java.util.List;

public class SendApartment {
    private static final Logger log = LoggerFactory.getLogger(SendApartment.class.getName());
    public static int index = 0;
    private static final InlineKeyboardMarkup INLINE_MARKUP_APARTMENT_CONTROLLER = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(Buttons_eng.BTN_PREV, Buttons_eng.BTN_GET_ADDRESS, Buttons_eng.BTN_NEXT))
            .keyboardRow(List.of(Buttons_eng.BTN_BACK))
            .build();

    private static final InlineKeyboardMarkup INLINE_MARKUP_MY_APARTMENT_CONTROLLER = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(Buttons_eng.BTN_PREV, Buttons_eng.BTN_GET_ADDRESS, Buttons_eng.BTN_NEXT))
            .keyboardRow(List.of(Buttons_eng.BTN_EDIT_APARTMENT, Buttons_eng.BTN_DELETE_APARTMENT))
            .keyboardRow(List.of(Buttons_eng.BTN_BACK))
            .build();
    private static final InlineKeyboardMarkup INLINE_NOT_FOUND_BACK = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(Buttons_eng.BTN_NOT_FOUND_BACK))
            .build();


    public static void sendApartmentsInformation(Update update, TelegramLongPollingBot bot, List<Apartment> apartments) {
        if (apartments.isEmpty()) {
            if (GetAndSetStates.getIdentifyListState(update).equals(StateForGetList.RECENTLY_UPLOADED)) {
                try {
                    bot.execute(EditMessageText.builder()
                            .chatId(UpdateProcessor.extractChatId(update))
                            .messageId(UpdateProcessor.extractMessageId(update))
                            .text("Not found ❌")
                            .replyMarkup(INLINE_NOT_FOUND_BACK)
                            .build()
                    );
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    bot.execute(SendMessage.builder()
                            .chatId(UpdateProcessor.extractChatId(update))
                            .text("Not found ❌")
                            .replyMarkup(INLINE_NOT_FOUND_BACK)
                            .build()
                    );
                } catch (TelegramApiException e) {
                    log.error(e.getLocalizedMessage());
                }
            }
        } else {
            if (!GetAndSetStates.getAddressBackState(update).equals(StateForAddressBack.DEFAULT) ||
                    GetAndSetStates.getIdentifyListState(update).equals(StateForGetList.RECENTLY_UPLOADED)) {
                try {
                    bot.execute(DeleteMessage.builder()
                            .chatId(UpdateProcessor.extractChatId(update))
                            .messageId(UpdateProcessor.extractMessageId(update))
                            .build()
                    );
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
            Apartment apartment = apartments.get(index);
            String text = "Username : " + apartment.getUsername() +
                    "\nDescription : " + apartment.getDescription() +
                    "\nRooms : " + apartment.getRooms() +
                    "\nDate : " + apartment.getDateTime() +
                    "\nPrice : " + apartment.getPrice() +
                    "\nCity : " + apartment.getAddress().getCity() +
                    "\nNumber : " + apartment.getNumber();
            InputFile file = new InputFile(apartment.getPhoto().getFileId());
            try {
                bot.execute(SendPhoto.builder()
                        .replyMarkup(INLINE_MARKUP_APARTMENT_CONTROLLER)
                        .photo(file)
                        .chatId(UpdateProcessor.extractChatId(update))
                        .caption(text)
                        .build());
            } catch (TelegramApiException e) {
                log.error(e.getLocalizedMessage());
            }
        }
    }

    public static void sendMyApartmentsInformation(Update update, TelegramLongPollingBot bot, List<Apartment> apartments) {
        try {
            bot.execute(DeleteMessage.builder()
                    .chatId(UpdateProcessor.extractChatId(update))
                    .messageId(UpdateProcessor.extractMessageId(update))
                    .build()
            );
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
        Apartment apartment = apartments.get(index);
        Callback_delete_apartment.id = String.valueOf(apartment.getId());
        String text = "Username : " + apartment.getUsername() +
                "\nApartment id " + apartment.getId() +
                "\nUser id = " + apartment.getUserId() +
                "\nDescription : " + apartment.getDescription() +
                "\nRooms : " + apartment.getRooms() +
                "\nDate : " + apartment.getDateTime() +
                "\nPrice : " + apartment.getPrice() +
                "\nCity : " + apartment.getAddress().getCity() +
                "\nNumber : " + apartment.getNumber();
        InputFile file = new InputFile(apartment.getPhoto().getFileId());
        try {
            bot.execute(SendPhoto.builder()
                    .replyMarkup(INLINE_MARKUP_MY_APARTMENT_CONTROLLER)
                    .photo(file)
                    .chatId(UpdateProcessor.extractChatId(update))
                    .caption(text)
                    .build());
        } catch (TelegramApiException e) {
            log.error(e.getLocalizedMessage());
        }
    }
}
