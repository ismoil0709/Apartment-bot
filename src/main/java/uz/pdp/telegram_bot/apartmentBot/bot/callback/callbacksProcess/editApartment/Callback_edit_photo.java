package uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess.editApartment;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess.Callback_menu;
import uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess.Callback_my_apartments;
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

import java.util.List;

public class Callback_edit_photo {
    public static void sendMessage(Update update, TelegramLongPollingBot bot) {
        try {
            bot.execute(EditMessageText.builder()
                    .text("Send photo \uD83D\uDDBC")
                    .replyMarkup(Markup.INLINE_MARKUP_BACK_STATE)
                    .chatId(UpdateProcessor.extractChatId(update))
                    .messageId(UpdateProcessor.extractMessageId(update))
                    .build());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
        GetAndSetStates.setBackButtonState(update, StateForBack.EDIT_PHOTO);
        GetAndSetStates.setWriteState(update, StateForWrite.SEND_PHOTO);
    }

    public static void process(Update update, TelegramLongPollingBot bot) {
        if (update.hasMessage() && update.getMessage().hasPhoto()) {
            List<PhotoSize> photo = update.getMessage().getPhoto();
            Client client = ClientRepoImpl.getInstance().get(UpdateProcessor.extractChatId(update));
            Apartment apartment = client.getApartment().get(Callback_my_apartments.index);
            apartment.setPhoto(photo.get(0));
            client.getApartment().set(Callback_my_apartments.index, apartment);
            ApartmentRepoImpl.getInstance().put(apartment);
            try {
                bot.execute(SendMessage.builder()
                        .text("Successfully  ✅")
                        .chatId(UpdateProcessor.extractChatId(update))
                        .build()
                );
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
            GetAndSetStates.setWriteState(update,StateForWrite.DEFAULT);
            GetAndSetStates.setBackButtonState(update,StateForBack.MY_APARTMENT_EDIT);
            SendApartment.sendMyApartmentsInformation(update,bot,client.getApartment());
        }
    }
}
