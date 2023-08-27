package uz.pdp.telegram_bot.apartmentBot.bot.newApartment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess.Callback_menu;
import uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess.Home_sate;
import uz.pdp.telegram_bot.apartmentBot.bot.repo.impl.ApartmentRepoImpl;
import uz.pdp.telegram_bot.apartmentBot.bot.repo.impl.ClientRepoImpl;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForPostAnAd;
import uz.pdp.telegram_bot.apartmentBot.bot.util.GetAndSetStates;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UpdateProcessor;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UtilLists;
import uz.pdp.telegram_bot.apartmentBot.model.Apartment;
import uz.pdp.telegram_bot.apartmentBot.model.Client;

public class FinishStep {
    private static final Logger log = LoggerFactory.getLogger(FinishStep.class.getName());

    public static void process(Update update, TelegramLongPollingBot bot) {
        Client client = ClientRepoImpl.getInstance().get(UpdateProcessor.extractChatId(update));
        ApartmentRepoImpl.getInstance().put(UtilLists.apartmentMap.get(UpdateProcessor.extractChatId(update)));
        client.getApartment().add(UtilLists.apartmentMap.get(UpdateProcessor.extractChatId(update)));
        GetAndSetStates.setPostAnAdState(update, StateForPostAnAd.DEFAULT);
        try {
            bot.execute(SendMessage.builder()
                    .text("Successfully ✅")
                    .chatId(UpdateProcessor.extractChatId(update))
                    .build()
            );
            Callback_menu.process(update, bot);
        } catch (TelegramApiException e) {
            log.error(e.getLocalizedMessage());
        }
    }
}
