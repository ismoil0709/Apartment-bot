package uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.pdp.telegram_bot.apartmentBot.bot.auth.AuthSendMessageProcess;
import uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess.callback_get_address.GetAddressBack;
import uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess.editApartment.Callback_edit_apartment;
import uz.pdp.telegram_bot.apartmentBot.bot.command.AccountSettings;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForBack;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForNotFoundBack;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForSignUp;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForWrite;
import uz.pdp.telegram_bot.apartmentBot.bot.util.GetAndSetStates;

public class CallBack_back {
    public static void process(Update update, TelegramLongPollingBot bot) {
        StateForBack state = GetAndSetStates.getBackButtonState(update);
        switch (state) {
            case SEARCH_APARTMENT, POST_AN_AD -> Callback_menu.processWithEdition(update, bot);
            case RECENTLY_UPLOADED -> Callback_searchApartment.process(update, bot);
            case GET_ADDRESS -> GetAddressBack.process(update, bot);
            case SEARCH_WITH_CITY -> Callback_search_with_city.sendMessageCity(update, bot);
            case SEARCH_WITH_NUMBER_OF_ROOMS -> Callback_search_with_number_of_rooms.sendMessageRooms(update, bot);
            case SEARCH_WITH_PRICE -> Callback_search_with_price.sendMessagePrice(update, bot);
            case WRITE_CITY, WRITE_ROOMS, WRITE_PRICE -> {
                GetAndSetStates.setWriteState(update, StateForWrite.DEFAULT);
                Callback_searchApartment.processEditMessage(update, bot);
            }
            case AUTH -> {
                GetAndSetStates.setSignUpState(update, StateForSignUp.DEFAULT);
                AuthSendMessageProcess.editMessage(update, bot);
            }
            case MY_APARTMENT -> Callback_menu.process(update, bot);
            case EDIT_CITY, EDIT_DESCRIPTION, EDIT_PHOTO, EDIT_PRICE, EDIT_ROOMS -> {
                GetAndSetStates.setWriteState(update, StateForWrite.DEFAULT);
                Callback_edit_apartment.sendEditMessage(update, bot);
            }
            case EDIT_NAME,EDIT_EMAIL,EDIT_NUMBER,EDIT_PASSWORD,EDIT_USERNAME ->{
                GetAndSetStates.setWriteState(update,StateForWrite.DEFAULT);
                AccountSettings.processWithEdition(update, bot);
            }
            case MY_APARTMENT_EDIT -> {
                Callback_edit_apartment.sendMessage(update, bot);
            }

        }

    }

    public static void notFoundProcess(Update update, TelegramLongPollingBot bot) {
        StateForNotFoundBack state = GetAndSetStates.getNotFoundState(update);
        switch (state) {
            case NOT_FOUND_CITY -> Callback_search_with_city.sendMessageCityWithEdition(update, bot);
            case NOT_FOUND_PRICE -> Callback_search_with_price.sendMessagePriceWithEdition(update, bot);
            case NOT_FOUND_ROOMS -> Callback_search_with_number_of_rooms.sendMessageRoomsWithEdition(update, bot);
            case NOT_FOUND_RECENTLY_UPLOADS -> Callback_searchApartment.processEditMessage(update, bot);

        }
    }
}
