package uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.telegram_bot.apartmentBot.bot.auth.AuthSendMessageProcess;
import uz.pdp.telegram_bot.apartmentBot.bot.repo.impl.ApartmentRepoImpl;
import uz.pdp.telegram_bot.apartmentBot.bot.repo.impl.ClientRepoImpl;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForBack;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForGetList;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForSignUp;
import uz.pdp.telegram_bot.apartmentBot.bot.util.GetAndSetStates;
import uz.pdp.telegram_bot.apartmentBot.bot.util.SendApartment;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UpdateProcessor;
import uz.pdp.telegram_bot.apartmentBot.model.Address;
import uz.pdp.telegram_bot.apartmentBot.model.Apartment;
import uz.pdp.telegram_bot.apartmentBot.model.Client;

import java.time.LocalDateTime;
import java.util.UUID;

public class Callback_my_apartments {
    private static final Logger log = LoggerFactory.getLogger(Callback_my_apartments.class.getName());

    public static int index;
    public static void process(Update update, TelegramLongPollingBot bot) {
        Client client = ClientRepoImpl.getInstance().get(UpdateProcessor.extractChatId(update));
        if (client != null && client.getState() != null && client.getState().equals(StateForSignUp.LOGIN_FINISH)) {
            index = 0;
            GetAndSetStates.setBackButtonState(update, StateForBack.MY_APARTMENT);
            GetAndSetStates.setIdentifyListState(update,StateForGetList.MY_APARTMENTS);
            Apartment apartment = new Apartment(UUID.randomUUID(), LocalDateTime.parse("2022-08-27T20:02:06")
                    , "1084271471",
                    "rrrr",
                    "h",
                    new Address(33.361506, 64.166629, "Tashkent"),
                    new PhotoSize("AgACAgIAAxkBAAOsZOo3S9VEz4uELrh2dHHtQgleFBYAApXNMRuIOVlLDDfsJs3Mjr8BAAMCAANzAAMwBA",
                            "AQADlc0xG4g5WUt4",
                            90, 83, 1661, null),
                    1700D, "r", 5
            );
            ApartmentRepoImpl.getInstance().put(apartment);
            client.getApartment().add(apartment);
            if (client.getApartment().isEmpty()) {
                try {
                    bot.execute(AnswerCallbackQuery.builder()
                            .text("You do not have an apartment yet")
                            .callbackQueryId(update.getCallbackQuery().getId())
                            .showAlert(true)
                            .build());
                } catch (TelegramApiException e) {
                    log.error(e.getLocalizedMessage());
                }
            } else {
                GetAndSetStates.setIdentifyListState(update, StateForGetList.MY_APARTMENTS);
                SendApartment.sendMyApartmentsInformation(update, bot, client.getApartment());
            }
        } else AuthSendMessageProcess.editMessage(update, bot);
    }
}
