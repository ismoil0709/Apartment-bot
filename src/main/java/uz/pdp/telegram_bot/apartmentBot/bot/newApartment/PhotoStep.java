package uz.pdp.telegram_bot.apartmentBot.bot.newApartment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.telegram_bot.apartmentBot.bot.repo.impl.ClientRepoImpl;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForPostAnAd;
import uz.pdp.telegram_bot.apartmentBot.bot.util.GetAndSetStates;
import uz.pdp.telegram_bot.apartmentBot.bot.util.Markup;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UpdateProcessor;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UtilLists;
import uz.pdp.telegram_bot.apartmentBot.bot.util.buttons.Buttons_eng;
import uz.pdp.telegram_bot.apartmentBot.model.Apartment;

import java.time.LocalDateTime;
import java.util.List;

public class PhotoStep {
    private static final Logger log = LoggerFactory.getLogger(PhotoStep.class.getName());
    private static final ReplyKeyboardMarkup MARKUP_LOCATION = ReplyKeyboardMarkup.builder()
            .resizeKeyboard(true)
            .keyboardRow(new KeyboardRow(List.of(Buttons_eng.BTN_LOCATION)))
            .keyboardRow(new KeyboardRow(List.of(Buttons_eng.BTN_BACK_LOCATION)))
            .build();

    public static void process(Update update, TelegramLongPollingBot bot) {
        PhotoSize photoSize = update.getMessage().getPhoto().get(0);
        Apartment apartment = new Apartment();
        apartment.setNumber(ClientRepoImpl.getInstance().get(UpdateProcessor.extractChatId(update)).getNumber());
        apartment.setUserId(UpdateProcessor.extractChatId(update));
        apartment.setDateTime(LocalDateTime.now());
        apartment.setUsername(update.getMessage().getFrom().getUserName());
        apartment.setPhoto(photoSize);
        UtilLists.apartmentMap.put(UpdateProcessor.extractChatId(update), apartment);
        try {
            bot.execute(SendMessage.builder()
                    .text("Share Location \uD83D\uDCCD")
                    .chatId(UpdateProcessor.extractChatId(update))
                    .replyMarkup(MARKUP_LOCATION)
                    .build()
            );
        } catch (TelegramApiException e) {
            log.error(e.getLocalizedMessage());
        }
        GetAndSetStates.setPostAnAdState(update, StateForPostAnAd.SEND_LOCATION);
    }
}
