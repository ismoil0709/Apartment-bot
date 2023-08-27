package uz.pdp.telegram_bot.apartmentBot.bot.writeProcess;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.pdp.telegram_bot.apartmentBot.bot.auth.steps.*;
import uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess.*;
import uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess.editAccount.*;
import uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess.editApartment.*;
import uz.pdp.telegram_bot.apartmentBot.bot.newApartment.*;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForPostAnAd;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForSignUp;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForWrite;
import uz.pdp.telegram_bot.apartmentBot.bot.util.GetAndSetStates;

public class WriteProcess {
    public static void writeSearchApartment(Update update, TelegramLongPollingBot bot) {
        StateForWrite state = GetAndSetStates.getWriteState(update);
        switch (state) {
            case WRITE_PRICE -> Callback_search_with_price.process(update, bot);
            case WRITE_ROOMS -> Callback_search_with_number_of_rooms.process(update, bot);
            case WRITE_CITY -> Callback_search_with_city.process(update, bot);
            case SEND_PHOTO -> Callback_edit_photo.process(update, bot);
            case SEND_LOCATION -> Callback_edit_location.process(update, bot);
            case WRITE_EDIT_PRICE -> Callback_edit_price.process(update, bot);
            case WRITE_EDIT_ROOMS -> Callback_edit_number_rooms.process(update, bot);
            case WRITE_EDIT_CITY -> Callback_edit_city.process(update, bot);
            case WRITE_EDIT_DESCRIPTION -> Callback_edit_description.process(update, bot);
            case WRITE_EDIT_NAME -> Callback_edit_name.process(update, bot);
            case WRITE_EDIT_EMAIL -> Callback_edit_email.process(update, bot);
            case WRITE_EDIT_NUMBER -> Callback_edit_number.process(update, bot);
            case WRITE_EDIT_PASSWORD -> Callback_edit_password.process(update, bot);
            case WRITE_EDIT_USERNAME -> Callback_edit_username.process(update,bot);
        }
    }

    public static void writeSignUp(Update update, TelegramLongPollingBot bot) {
        StateForSignUp state = GetAndSetStates.getSignUpState(update);
        switch (state) {
            case EMAIL -> EmailStep.sendMessageEmail(update, bot);
            case WRITE_EMAIL -> EmailStep.process(update, bot);
            case WRITE_PASSWORD -> PasswordStep.process(update, bot);
            case LOGIN_EMAIL -> LoginEmailStep.sendMessageEmail(update, bot);
            case LOGIN_WRITE_EMAIL -> LoginEmailStep.process(update, bot);
            case LOGIN_PASSWORD -> LoginPasswordStep.sendMessagePassword(update, bot);
            case LOGIN_WRITE_PASSWORD -> LoginPasswordStep.process(update, bot);
            case REENTER -> LoginEmailStep.sendMessageEmailWithEdition(update, bot);
        }
    }

    public static void writeNewApartment(Update update, TelegramLongPollingBot bot) {
        StateForPostAnAd state = GetAndSetStates.getPostAnAdState(update);
        switch (state) {
            case DESCRIPTION -> DescriptionProcess.sendMessage(update, bot);
            case WRITE_DESCRIPTION -> DescriptionProcess.process(update, bot);
            case WRITE_PRICE -> PriceStep.process(update, bot);
            case WRITE_ROOMS -> RoomsStep.process(update, bot);
            case WRITE_CITY -> {
                CityStep.process(update, bot);
                FinishStep.process(update, bot);
            }
        }
    }
}
