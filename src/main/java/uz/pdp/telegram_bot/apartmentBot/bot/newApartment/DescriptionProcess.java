package uz.pdp.telegram_bot.apartmentBot.bot.newApartment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess.Home_sate;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForPostAnAd;
import uz.pdp.telegram_bot.apartmentBot.bot.util.GetAndSetStates;
import uz.pdp.telegram_bot.apartmentBot.bot.util.Markup;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UpdateProcessor;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UtilLists;
import uz.pdp.telegram_bot.apartmentBot.model.Apartment;

public class DescriptionProcess {
    private static final Logger log = LoggerFactory.getLogger(DescriptionProcess.class.getName());
    public static void process(Update update, TelegramLongPollingBot bot) {
        Apartment apartment = new Apartment();
        for (Apartment a : UtilLists.apartmentMap.values()) {
            if (a.getUserId().equals(UpdateProcessor.extractChatId(update))) apartment = a;
        }
        String text = update.getMessage().getText();
        apartment.setDescription(text);
        UtilLists.apartmentMap.put(UpdateProcessor.extractChatId(update), apartment);
        try {
            bot.execute(SendMessage.builder()
                    .text("Enter price \uD83D\uDCB0")
                    .replyMarkup(Markup.INLINE_MARKUP_BACK_STATE)
                    .chatId(UpdateProcessor.extractChatId(update))
                    .build()
            );
        } catch (TelegramApiException e) {
            log.error(e.getLocalizedMessage());
        }
        GetAndSetStates.setPostAnAdState(update, StateForPostAnAd.WRITE_PRICE);
    }

    public static void sendMessage(Update update, TelegramLongPollingBot bot) {
        try {
            bot.execute(SendMessage.builder()
                    .text("Enter Description \uD83D\uDCDD")
                    .replyMarkup(Markup.INLINE_MARKUP_BACK_STATE)
                    .chatId(UpdateProcessor.extractChatId(update))
                    .build()
            );
        } catch (TelegramApiException e) {
            log.error(e.getLocalizedMessage());
        }
        GetAndSetStates.setPostAnAdState(update, StateForPostAnAd.WRITE_DESCRIPTION);
    }
}
