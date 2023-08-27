package uz.pdp.telegram_bot.apartmentBot.bot.newApartment;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForPostAnAd;
import uz.pdp.telegram_bot.apartmentBot.bot.util.GetAndSetStates;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UpdateProcessor;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UtilLists;
import uz.pdp.telegram_bot.apartmentBot.model.Apartment;

public class CityStep {
    public static void process(Update update, TelegramLongPollingBot bot) {
        Apartment apartment = new Apartment();
        for (Apartment a : UtilLists.apartmentMap.values()) {
            if (a.getUserId().equals(UpdateProcessor.extractChatId(update))) apartment = a;
        }
        String text = update.getMessage().getText();
        apartment.getAddress().setCity(text);
        UtilLists.apartmentMap.put(UpdateProcessor.extractChatId(update), apartment);
    }
}
