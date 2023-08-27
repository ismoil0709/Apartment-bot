package uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.telegram_bot.apartmentBot.bot.regex.Regex;
import uz.pdp.telegram_bot.apartmentBot.bot.repo.impl.ApartmentRepoImpl;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForGetList;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForBack;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForNotFoundBack;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForWrite;
import uz.pdp.telegram_bot.apartmentBot.bot.util.Markup;
import uz.pdp.telegram_bot.apartmentBot.bot.util.GetAndSetStates;
import uz.pdp.telegram_bot.apartmentBot.bot.util.SendApartment;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UpdateProcessor;
import uz.pdp.telegram_bot.apartmentBot.model.Apartment;

import java.util.ArrayList;
import java.util.List;

public class Callback_search_with_price {
    private static final Logger log = LoggerFactory.getLogger(Callback_search_with_price.class.getName());
    public static List<Apartment> sorted = new ArrayList<>();
    public static void sendMessagePriceWithEdition(Update update, TelegramLongPollingBot bot) {
        GetAndSetStates.setWriteState(update, StateForWrite.WRITE_PRICE);
        try {
            bot.execute(EditMessageText.builder()
                    .chatId(UpdateProcessor.extractChatId(update))
                    .replyMarkup(Markup.INLINE_MARKUP_BACK_STATE)
                    .messageId(UpdateProcessor.extractMessageId(update))
                    .text("Enter price (20000-40000)")
                    .build());
        } catch (TelegramApiException e) {
            log.error(e.getLocalizedMessage());
        }
    }

    public static void sendMessagePrice(Update update, TelegramLongPollingBot bot) {
        try {
            bot.execute(DeleteMessage.builder()
                    .messageId(UpdateProcessor.extractMessageId(update))
                    .chatId(UpdateProcessor.extractChatId(update))
                    .build()
            );
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
        GetAndSetStates.setWriteState(update, StateForWrite.WRITE_PRICE);
        try {
            bot.execute(SendMessage.builder()
                    .chatId(UpdateProcessor.extractChatId(update))
                    .replyMarkup(Markup.INLINE_MARKUP_BACK_STATE)
                    .text("Enter price (20000-40000)")
                    .build());
        } catch (TelegramApiException e) {
            log.error(e.getLocalizedMessage());
        }
    }

    public static void process(Update update, TelegramLongPollingBot bot) {
        sorted = new ArrayList<>();
        GetAndSetStates.setBackButtonState(update, StateForBack.WRITE_PRICE);
        GetAndSetStates.setIdentifyListState(update, StateForGetList.PRICE);
        GetAndSetStates.setNotFoundState(update, StateForNotFoundBack.NOT_FOUND_PRICE);

        if (update.hasMessage()) {
            if (Regex.writePriceValidator(update.getMessage().getText())) {
                String[] parts = update.getMessage().getText().split("-", 2);
                for (Apartment a : ApartmentRepoImpl.getInstance().list()) {
                    if (a.getPrice() > Double.parseDouble(parts[0]) && a.getPrice() < Double.parseDouble(parts[1])) {
                        sorted.add(a);
                    }
                }
                SendApartment.sendApartmentsInformation(update, bot, sorted);
                GetAndSetStates.setBackButtonState(update, StateForBack.SEARCH_WITH_PRICE);
                GetAndSetStates.setWriteState(update, StateForWrite.DEFAULT);
            }else {
                try {
                    bot.execute(SendMessage.builder()
                            .text("Invalid format \uD83D\uDEAB\uD83D\uDEAB\uD83D\uDEAB\n" +
                                    "Please re enter")
                            .chatId(UpdateProcessor.extractChatId(update))
                            .replyMarkup(Markup.INLINE_MARKUP_BACK_STATE)
                            .build()
                    );
                    GetAndSetStates.setWriteState(update, StateForWrite.WRITE_PRICE);
                } catch (TelegramApiException e) {
                    log.error(e.getLocalizedMessage());
                }
            }
        }
    }
}
