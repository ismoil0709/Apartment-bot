package uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.telegram_bot.apartmentBot.bot.repo.impl.ApartmentRepoImpl;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForBack;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForGetList;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForNotFoundBack;
import uz.pdp.telegram_bot.apartmentBot.bot.util.GetAndSetStates;
import uz.pdp.telegram_bot.apartmentBot.bot.util.SendApartment;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UpdateProcessor;
import uz.pdp.telegram_bot.apartmentBot.model.Apartment;

import java.util.Comparator;

public class Callback_recentlyUploads {
    public static void process(Update update, TelegramLongPollingBot bot) {
        try {
            bot.execute(DeleteMessage.builder()
                    .chatId(UpdateProcessor.extractChatId(update))
                    .messageId(UpdateProcessor.extractMessageId(update))
                    .build()
            );
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
        GetAndSetStates.setBackButtonState(update, StateForBack.RECENTLY_UPLOADED);
        GetAndSetStates.setIdentifyListState(update, StateForGetList.RECENTLY_UPLOADED);
        GetAndSetStates.setNotFoundState(update, StateForNotFoundBack.NOT_FOUND_RECENTLY_UPLOADS);
        ApartmentRepoImpl.getInstance().list().sort(Comparator.comparing(Apartment::getDateTime).reversed());
        SendApartment.sendApartmentsInformation(update, bot, ApartmentRepoImpl.getInstance().list());
    }
}
