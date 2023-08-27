package uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess.callback_get_address;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess.Callback_search_with_city;
import uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess.Callback_search_with_number_of_rooms;
import uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess.Callback_search_with_price;
import uz.pdp.telegram_bot.apartmentBot.bot.repo.impl.ApartmentRepoImpl;
import uz.pdp.telegram_bot.apartmentBot.bot.repo.impl.ClientRepoImpl;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForAddressBack;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForGetList;
import uz.pdp.telegram_bot.apartmentBot.bot.util.GetAndSetStates;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UpdateProcessor;
import uz.pdp.telegram_bot.apartmentBot.model.Client;

public class SendLocationProcess {
    public static void process (Update update, TelegramLongPollingBot bot){
        StateForGetList state = GetAndSetStates.getIdentifyListState(update);
        switch (state){
            case ROOMS -> {
                GetAndSetStates.setAddressBackState(update, StateForAddressBack.ROOMS);
                CallBack_get_address.sendLocation(update,bot,Callback_search_with_number_of_rooms.sorted);
            }
            case PRICE -> {
                GetAndSetStates.setAddressBackState(update,StateForAddressBack.PRICE);
                CallBack_get_address.sendLocation(update,bot, Callback_search_with_price.sorted);
            }
            case CITY -> {
                GetAndSetStates.setAddressBackState(update,StateForAddressBack.CITY);
                CallBack_get_address.sendLocation(update,bot, Callback_search_with_city.sorted);
            }
            case RECENTLY_UPLOADED -> {
                GetAndSetStates.setAddressBackState(update,StateForAddressBack.RECENTLY_UPLOADS);
                CallBack_get_address.sendLocation(update,bot, ApartmentRepoImpl.getInstance().list());
            }
            case MY_APARTMENTS -> {
                System.out.println("my");
                GetAndSetStates.setAddressBackState(update,StateForAddressBack.MY_APARTMENT);
                Client client = ClientRepoImpl.getInstance().get(UpdateProcessor.extractChatId(update));
                CallBack_get_address.sendLocation(update,bot,client.getApartment());
            }
        }
    }
}
