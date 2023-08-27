package uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess.callback_get_address;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess.Callback_search_with_city;
import uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess.Callback_search_with_number_of_rooms;
import uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess.Callback_search_with_price;
import uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess.editApartment.Callback_edit_city;
import uz.pdp.telegram_bot.apartmentBot.bot.repo.impl.ClientRepoImpl;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForAddressBack;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForBack;
import uz.pdp.telegram_bot.apartmentBot.bot.util.GetAndSetStates;
import uz.pdp.telegram_bot.apartmentBot.bot.util.SendApartment;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UpdateProcessor;
import uz.pdp.telegram_bot.apartmentBot.model.Client;

public class GetAddressBack {
    public static void process(Update update, TelegramLongPollingBot bot){
        GetAndSetStates.setBackButtonState(update,StateForBack.MY_APARTMENT);
        StateForAddressBack state = GetAndSetStates.getAddressBackState(update);
        switch (state){
            case MY_APARTMENT -> {
                Client client = ClientRepoImpl.getInstance().get(UpdateProcessor.extractChatId(update));
                SendApartment.sendMyApartmentsInformation(update, bot, client.getApartment());
            }
            case CITY -> SendApartment.sendApartmentsInformation(update,bot,Callback_search_with_city.sorted);
            case PRICE -> SendApartment.sendApartmentsInformation(update,bot, Callback_search_with_price.sorted);
            case ROOMS -> SendApartment.sendApartmentsInformation(update,bot, Callback_search_with_number_of_rooms.sorted);
        }
    }
}
