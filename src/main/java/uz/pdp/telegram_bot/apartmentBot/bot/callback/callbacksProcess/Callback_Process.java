package uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.pdp.telegram_bot.apartmentBot.bot.callback.Callbacks;
import uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess.callback_get_address.SendLocationProcess;
import uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess.editAccount.*;
import uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess.editApartment.*;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForSignUp;
import uz.pdp.telegram_bot.apartmentBot.bot.util.GetAndSetStates;
import uz.pdp.telegram_bot.apartmentBot.bot.util.SendApartment;

public class Callback_Process {
    public static void process(Update update, TelegramLongPollingBot bot) {
        final String data = update.getCallbackQuery().getData();
        Callbacks callbacks = Callbacks.of(data);
        switch (callbacks) {
            case CALLBACK_APARTMENT_SEARCH -> Callback_searchApartment.processEditMessage(update, bot);
            case CALLBACK_POST_AN_AD -> Callback_post_an_ad.process(update, bot);
            case CALLBACK_RECENTLY_UPLOADED -> {
                SendApartment.index = 0;
                Callback_recentlyUploads.process(update, bot);
            }
            case CALLBACK_NEXT -> CallBack_Next.process(update, bot);
            case CALLBACK_PREV -> CallBack_Prev.process(update, bot);
            case CALLBACK_SEARCH_WITH_PRICE -> {
                SendApartment.index = 0;
                Callback_search_with_price.sendMessagePriceWithEdition(update, bot);
            }
            case CALLBACK_SEARCH_WITH_NUMBER_OF_ROOMS -> {
                SendApartment.index = 0;
                Callback_search_with_number_of_rooms.sendMessageRoomsWithEdition(update, bot);
            }
            case CALLBACK_SEARCH_WITH_CITY -> {
                SendApartment.index = 0;
                Callback_search_with_city.sendMessageCityWithEdition(update, bot);
            }
            case CALLBACK_BACK -> CallBack_back.process(update, bot);
            case CALLBACK_GET_ADDRESS -> SendLocationProcess.process(update, bot);
            case CALLBACK_SIGN_UP -> Callback_sign_up.process(update, bot);
            case CALLBACK_LOGIN -> Callback_login.process(update, bot);
            case CALLBACK_REENTER -> GetAndSetStates.setSignUpState(update,StateForSignUp.REENTER);
            case CALLBACK_MY_APARTMENTS -> {
                SendApartment.index = 0;
                Callback_my_apartments.process(update,bot);
            }
            case CALLBACKS_DELETE_APARTMENT -> Callback_delete_apartment.process(update,bot);
            case CALLBACK_NOT_FOUND_BACK -> CallBack_back.notFoundProcess(update, bot);
            case CALLBACK_EDIT_APARTMENT -> Callback_edit_apartment.sendMessage(update, bot);
            case CALLBACK_EDIT_PHOTO -> Callback_edit_photo.sendMessage(update, bot);
            case CALLBACK_EDIT_PRICE -> Callback_edit_price.sendMessage(update,bot);
            case CALLBACK_EDIT_LOCATION -> Callback_edit_location.sendMessage(update, bot);
            case CALLBACK_EDIT_DESCRIPTION -> Callback_edit_description.sendMessage(update, bot);
            case CALLBACK_EDIT_CITY -> Callback_edit_city.sendMessage(update, bot);
            case CALLBACK_EDIT_ROOMS -> Callback_edit_number_rooms.sendMessage(update, bot);
            case CALLBACK_EDIT_BACK -> Callback_my_apartments.process(update, bot);
            case CALLBACK_EDIT_NAME -> Callback_edit_name.sendMessage(update, bot);
            case CALLBACK_EDIT_EMAIL -> Callback_edit_email.sendMessage(update, bot);
            case CALLBACK_EDIT_PASSWORD -> Callback_edit_password.sendMessage(update, bot);
            case CALLBACK_EDIT_USERNAME -> Callback_edit_username.sendMessage(update, bot);
            case CALLBACK_EDIT_PHONE_NUMBER -> Callback_edit_number.sendMessage(update, bot);
        }
    }
}
