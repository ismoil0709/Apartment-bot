package uz.pdp.telegram_bot.apartmentBot.bot.newApartment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.telegram_bot.apartmentBot.bot.regex.Regex;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForPostAnAd;
import uz.pdp.telegram_bot.apartmentBot.bot.util.GetAndSetStates;
import uz.pdp.telegram_bot.apartmentBot.bot.util.Markup;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UpdateProcessor;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UtilLists;
import uz.pdp.telegram_bot.apartmentBot.model.Apartment;

public class PriceStep {
    private static final Logger log = LoggerFactory.getLogger(PhotoStep.class.getName());

    public static void process(Update update, TelegramLongPollingBot bot) {
        Apartment apartment = new Apartment();
        for (Apartment a : UtilLists.apartmentMap.values()) {
            if (a.getUserId().equals(UpdateProcessor.extractChatId(update))) apartment = a;
        }
        String text = update.getMessage().getText();
        if (Regex.digitValidator(text)) {
            apartment.setPrice(Double.valueOf(text));
            UtilLists.apartmentMap.put(UpdateProcessor.extractChatId(update), apartment);
            try {
                bot.execute(SendMessage.builder()
                        .text("Enter number of rooms \uD83C\uDFE2")
                        .replyMarkup(Markup.INLINE_MARKUP_BACK_STATE)
                        .chatId(UpdateProcessor.extractChatId(update))
                        .build()
                );
            } catch (TelegramApiException e) {
                log.error(e.getLocalizedMessage());
            }
            GetAndSetStates.setPostAnAdState(update, StateForPostAnAd.WRITE_ROOMS);
        } else {
            try {
                bot.execute(SendMessage.builder()
                        .text("Invalid format ❌\nPlease re enter")
                        .chatId(UpdateProcessor.extractChatId(update))
                        .replyMarkup(Markup.INLINE_MARKUP_BACK_STATE)
                        .build()
                );
                GetAndSetStates.setPostAnAdState(update, StateForPostAnAd.WRITE_PRICE);
            } catch (TelegramApiException e) {
                log.error(e.getLocalizedMessage());
            }
        }
    }
}
