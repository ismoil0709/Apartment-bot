package uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess.editApartment;

import org.apache.commons.lang3.StringUtils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess.Callback_menu;
import uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess.Callback_my_apartments;
import uz.pdp.telegram_bot.apartmentBot.bot.newApartment.LocationStep;
import uz.pdp.telegram_bot.apartmentBot.bot.newApartment.PhotoStep;
import uz.pdp.telegram_bot.apartmentBot.bot.repo.impl.ApartmentRepoImpl;
import uz.pdp.telegram_bot.apartmentBot.bot.repo.impl.ClientRepoImpl;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForBack;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForWrite;
import uz.pdp.telegram_bot.apartmentBot.bot.util.GetAndSetStates;
import uz.pdp.telegram_bot.apartmentBot.bot.util.SendApartment;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UpdateProcessor;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UtilLists;
import uz.pdp.telegram_bot.apartmentBot.bot.util.buttons.Buttons_eng;
import uz.pdp.telegram_bot.apartmentBot.model.Address;
import uz.pdp.telegram_bot.apartmentBot.model.Apartment;
import uz.pdp.telegram_bot.apartmentBot.model.Client;

import java.util.List;

public class Callback_edit_location {
    private static final ReplyKeyboardMarkup MARKUP_BACK_LOCATION = ReplyKeyboardMarkup.builder()
            .resizeKeyboard(true)
            .keyboardRow(new KeyboardRow(List.of(Buttons_eng.BTN_LOCATION)))
            .keyboardRow(new KeyboardRow(List.of(Buttons_eng.BTN_EDIT_LOCATION_BACK)))
            .build();

    public static void process(Update update, TelegramLongPollingBot bot) {
        if (update.hasMessage() && update.getMessage().hasLocation()) {
            Location location = update.getMessage().getLocation();
            Client client = ClientRepoImpl.getInstance().get(UpdateProcessor.extractChatId(update));
            Apartment apartment = client.getApartment().get(Callback_my_apartments.index);
            Address address = apartment.getAddress();
            address.setLatitude(location.getLatitude());
            address.setLongitude(location.getLongitude());
            apartment.setAddress(address);
            client.getApartment().set(Callback_my_apartments.index, apartment);
            ApartmentRepoImpl.getInstance().put(apartment);

            try {
                bot.execute(SendMessage.builder()
                        .text("Successfully")
                        .replyMarkup(new ReplyKeyboardRemove(true))
                        .chatId(UpdateProcessor.extractChatId(update))
                        .build()
                );
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
            GetAndSetStates.setWriteState(update, StateForWrite.DEFAULT);
            GetAndSetStates.setBackButtonState(update,StateForBack.MY_APARTMENT_EDIT);
            SendApartment.sendMyApartmentsInformation(update,bot,client.getApartment());
        }
    }

    public static void sendMessage(Update update, TelegramLongPollingBot bot) {
        try {
            bot.execute(SendMessage.builder()
                    .text("Send location ")
                    .replyMarkup(MARKUP_BACK_LOCATION)
                    .chatId(UpdateProcessor.extractChatId(update))
                    .build()
            );
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
        GetAndSetStates.setWriteState(update, StateForWrite.SEND_LOCATION);
    }
}
