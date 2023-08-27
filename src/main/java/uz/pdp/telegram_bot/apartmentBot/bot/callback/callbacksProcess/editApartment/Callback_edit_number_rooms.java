package uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess.editApartment;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess.Callback_my_apartments;
import uz.pdp.telegram_bot.apartmentBot.bot.regex.Regex;
import uz.pdp.telegram_bot.apartmentBot.bot.repo.impl.ApartmentRepoImpl;
import uz.pdp.telegram_bot.apartmentBot.bot.repo.impl.ClientRepoImpl;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForBack;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForWrite;
import uz.pdp.telegram_bot.apartmentBot.bot.util.GetAndSetStates;
import uz.pdp.telegram_bot.apartmentBot.bot.util.Markup;
import uz.pdp.telegram_bot.apartmentBot.bot.util.SendApartment;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UpdateProcessor;
import uz.pdp.telegram_bot.apartmentBot.model.Apartment;
import uz.pdp.telegram_bot.apartmentBot.model.Client;

public class Callback_edit_number_rooms {
    public static void process(Update update, TelegramLongPollingBot bot) {
        if (update.hasMessage()) {
            if (Regex.digitValidator(update.getMessage().getText())) {
                Client client = ClientRepoImpl.getInstance().get(UpdateProcessor.extractChatId(update));
                Apartment apartment = client.getApartment().get(Callback_my_apartments.index);
                apartment.setRooms(Integer.valueOf(update.getMessage().getText()));
                client.getApartment().set(Callback_my_apartments.index, apartment);
                ApartmentRepoImpl.getInstance().put(apartment);
                try {
                    bot.execute(SendMessage.builder()
                            .text("Successfully")
                            .chatId(UpdateProcessor.extractChatId(update))
                            .build()
                    );
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                GetAndSetStates.setWriteState(update, StateForWrite.DEFAULT);
                SendApartment.sendMyApartmentsInformation(update,bot,client.getApartment());
            } else {
                try {
                    bot.execute(SendMessage.builder()
                            .text("Invalid format \uD83D\uDEAB\uD83D\uDEAB\uD83D\uDEAB\n" +
                                    "Please re enter")
                            .chatId(UpdateProcessor.extractChatId(update))
                            .replyMarkup(Markup.INLINE_MARKUP_BACK_STATE)
                            .build()
                    );
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                GetAndSetStates.setWriteState(update, StateForWrite.WRITE_EDIT_ROOMS);
            }
        }
    }

    public static void sendMessage(Update update, TelegramLongPollingBot bot) {
        try {
            bot.execute(EditMessageText.builder()
                    .text("Enter number of rooms : ")
                    .replyMarkup(Markup.INLINE_MARKUP_BACK_STATE)
                    .messageId(UpdateProcessor.extractMessageId(update))
                    .chatId(UpdateProcessor.extractChatId(update))
                    .build()
            );
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
        GetAndSetStates.setBackButtonState(update, StateForBack.EDIT_ROOMS);
        GetAndSetStates.setWriteState(update, StateForWrite.WRITE_EDIT_ROOMS);
    }

}
