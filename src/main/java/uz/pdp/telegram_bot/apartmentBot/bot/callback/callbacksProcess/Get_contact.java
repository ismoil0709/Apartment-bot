package uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForBack;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForSignUp;
import uz.pdp.telegram_bot.apartmentBot.bot.util.GetAndSetStates;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UpdateProcessor;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UtilLists;
import uz.pdp.telegram_bot.apartmentBot.model.Client;

public class Get_contact {
    private static final Logger log = LoggerFactory.getLogger(Get_contact.class.getName());
    public static void process(Update update, TelegramLongPollingBot bot) {
        GetAndSetStates.setBackButtonState(update, StateForBack.AUTH);
        Contact contact = update.getMessage().getContact();
        Client client = Client.from(contact);
        client.setUsername(update.getMessage().getFrom().getUserName());
        UtilLists.clientSignUpMap.put(UpdateProcessor.extractChatId(update), client);
        GetAndSetStates.setSignUpState(update, StateForSignUp.EMAIL);
        try {
            bot.execute(SendMessage.builder()
                    .text("contact sent successfully")
                    .chatId(UpdateProcessor.extractChatId(update))
                    .replyMarkup(Home_sate.INLINE_MARKUP_HOME_STATE)
                    .build()
            );
        } catch (TelegramApiException e) {
            log.error(e.getLocalizedMessage());
        }
    }
}
