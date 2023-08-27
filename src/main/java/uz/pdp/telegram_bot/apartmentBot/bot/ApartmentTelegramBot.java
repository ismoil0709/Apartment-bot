package uz.pdp.telegram_bot.apartmentBot.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess.Callback_Process;
import uz.pdp.telegram_bot.apartmentBot.bot.command.CommandProcess;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForBack;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForPostAnAd;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForSignUp;
import uz.pdp.telegram_bot.apartmentBot.bot.util.GetAndSetStates;
import uz.pdp.telegram_bot.apartmentBot.bot.writeProcess.WriteProcess;

import java.util.Objects;


public class ApartmentTelegramBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {

        System.out.println(GetAndSetStates.getAddressBackState(update));
        if (update.hasMessage() && update.getMessage().hasText()) {
            CommandProcess.process(update, this);
        } else if (update.hasCallbackQuery()) {
            Callback_Process.process(update, this);
        }
        if (!Objects.equals(GetAndSetStates.getBackButtonState(update), StateForBack.DEFAULT)) {
            WriteProcess.writeSearchApartment(update, this);
        }
        if (!GetAndSetStates.getSignUpState(update).equals(StateForSignUp.DEFAULT)) {
            WriteProcess.writeSignUp(update, this);
        }
        if (!GetAndSetStates.getPostAnAdState(update).equals(StateForPostAnAd.DEFAULT)) {
            WriteProcess.writeNewApartment(update, this);
        }
    }

    public ApartmentTelegramBot() {
        super("6485333618:AAE1MhrcN4Gi1ArPt37gqvoRVK-voybXH48");
    }

    @Override
    public String getBotUsername() {
        return "Ismoil_0709_testbot";
    }

}


