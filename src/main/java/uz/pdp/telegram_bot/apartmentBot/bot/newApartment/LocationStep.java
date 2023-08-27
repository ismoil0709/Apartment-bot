package uz.pdp.telegram_bot.apartmentBot.bot.newApartment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess.Home_sate;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForPostAnAd;
import uz.pdp.telegram_bot.apartmentBot.bot.util.GetAndSetStates;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UpdateProcessor;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UtilLists;
import uz.pdp.telegram_bot.apartmentBot.bot.util.buttons.Buttons_eng;
import uz.pdp.telegram_bot.apartmentBot.model.Address;
import uz.pdp.telegram_bot.apartmentBot.model.Apartment;

import java.util.List;

public class LocationStep {
    private static final Logger log = LoggerFactory.getLogger(LocationStep.class.getName());
    public static void process(Update update, TelegramLongPollingBot bot) {
        Apartment apartment = new Apartment();
        for (Apartment a : UtilLists.apartmentMap.values()) {
            if (a.getUserId().equals(UpdateProcessor.extractChatId(update))) apartment = a;
        }
        Location location = update.getMessage().getLocation();
        Address address = new Address();
        address.setLongitude(location.getLongitude());
        address.setLatitude(location.getLatitude());
        apartment.setAddress(address);
        UtilLists.apartmentMap.put(UpdateProcessor.extractChatId(update), apartment);
        GetAndSetStates.setPostAnAdState(update, StateForPostAnAd.DESCRIPTION);
        try {
            bot.execute(SendMessage.builder()
                    .text("Successfully ✅")
                    .chatId(UpdateProcessor.extractChatId(update))
                    .replyMarkup(new ReplyKeyboardRemove(true))
                    .build()
            );
        } catch (TelegramApiException e) {
            log.error(e.getLocalizedMessage());
        }
    }
}
