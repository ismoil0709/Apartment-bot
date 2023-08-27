package uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForSignUp;
import uz.pdp.telegram_bot.apartmentBot.bot.util.GetAndSetStates;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UpdateProcessor;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UtilLists;
import uz.pdp.telegram_bot.apartmentBot.model.Client;

public class Callback_login {
    private static final Logger log = LoggerFactory.getLogger(Callback_login.class.getName());

    public static void process(Update update, TelegramLongPollingBot bot) {
        Client client = new Client();
        client.setId(UpdateProcessor.extractChatId(update));
        UtilLists.clientLoginMap.put(UpdateProcessor.extractChatId(update), client);
        try {
            bot.execute(EditMessageText.builder()
                    .text("Login : ")
                    .messageId(UpdateProcessor.extractMessageId(update))
                    .chatId(UpdateProcessor.extractChatId(update))
                    .build()
            );
        } catch (TelegramApiException e) {
            log.error(e.getLocalizedMessage());
        }
        GetAndSetStates.setSignUpState(update, StateForSignUp.LOGIN_EMAIL);
    }
}
