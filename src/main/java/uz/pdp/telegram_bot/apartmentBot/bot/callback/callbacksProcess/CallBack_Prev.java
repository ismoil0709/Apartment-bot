package uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess.editApartment.Callback_edit_apartment;
import uz.pdp.telegram_bot.apartmentBot.bot.repo.impl.ApartmentRepoImpl;
import uz.pdp.telegram_bot.apartmentBot.bot.repo.impl.ClientRepoImpl;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForGetList;
import uz.pdp.telegram_bot.apartmentBot.bot.util.GetAndSetStates;
import uz.pdp.telegram_bot.apartmentBot.bot.util.SendApartment;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UpdateProcessor;
import uz.pdp.telegram_bot.apartmentBot.model.Apartment;
import uz.pdp.telegram_bot.apartmentBot.model.Client;

import java.util.List;

public class CallBack_Prev {
    private static final Logger log = LoggerFactory.getLogger(CallBack_Prev.class.getName());

    private static void getList(Update update, TelegramLongPollingBot bot, List<Apartment> apartments) {
        if (SendApartment.index == 0) {
            try {
                bot.execute(AnswerCallbackQuery.builder()
                        .text("Not found ☹️")
                        .callbackQueryId(update.getCallbackQuery().getId())
                        .showAlert(true)
                        .build()
                );
            } catch (TelegramApiException e) {
                log.error(e.getLocalizedMessage());
            }
        } else {
            SendApartment.index--;
            SendApartment.sendApartmentsInformation(update, bot, apartments);
        }
    }
    private static void getMyList(Update update,TelegramLongPollingBot bot,List<Apartment> apartments){
        if (SendApartment.index == 0) {
            try {
                bot.execute(AnswerCallbackQuery.builder()
                        .text("Not found ☹️")
                        .callbackQueryId(update.getCallbackQuery().getId())
                        .showAlert(true)
                        .build()
                );
            } catch (TelegramApiException e) {
                log.error(e.getLocalizedMessage());
            }
        } else {
            SendApartment.index--;
            Callback_my_apartments.index = Callback_my_apartments.index-1;
            SendApartment.sendMyApartmentsInformation(update, bot, apartments);
        }
    }

    public static void process(Update update, TelegramLongPollingBot bot) {
        StateForGetList state = GetAndSetStates.getIdentifyListState(update);
        switch (state) {
            case PRICE -> CallBack_Prev.getList(update, bot, Callback_search_with_price.sorted);
            case ROOMS -> CallBack_Prev.getList(update, bot, Callback_search_with_number_of_rooms.sorted);
            case CITY -> CallBack_Prev.getList(update, bot, Callback_search_with_city.sorted);
            case RECENTLY_UPLOADED -> CallBack_Prev.getList(update, bot, ApartmentRepoImpl.getInstance().list());
            case MY_APARTMENTS -> {
                Client client = ClientRepoImpl.getInstance().get(UpdateProcessor.extractChatId(update));
                CallBack_Prev.getMyList(update,bot,client.getApartment());
            }

        }
    }
}
