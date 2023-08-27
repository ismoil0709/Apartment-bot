package uz.pdp.telegram_bot.apartmentBot.bot.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.telegram_bot.apartmentBot.bot.auth.AuthSendMessageProcess;
import uz.pdp.telegram_bot.apartmentBot.bot.repo.impl.ClientRepoImpl;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForSignUp;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UpdateProcessor;
import uz.pdp.telegram_bot.apartmentBot.bot.util.buttons.Buttons_eng;
import uz.pdp.telegram_bot.apartmentBot.model.Client;

import java.util.List;

public class AccountSettings {
    private static final Logger log = LoggerFactory.getLogger(AccountSettings.class.getName());
    private static final InlineKeyboardMarkup INLINE_EDIT_STATE = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(Buttons_eng.BTN_EDIT_NAME))
            .keyboardRow(List.of(Buttons_eng.BTN_EDIT_EMAIL))
            .keyboardRow(List.of(Buttons_eng.BTN_EDIT_PASSWORD))
            .keyboardRow(List.of(Buttons_eng.BTN_EDIT_USERNAME))
            .keyboardRow(List.of(Buttons_eng.BTN_EDIT_NUMBER))
            .build();

    public static void process(Update update, TelegramLongPollingBot bot) {
        Client client = ClientRepoImpl.getInstance().get(UpdateProcessor.extractChatId(update));
        if (client != null && client.getState() != null && client.getState().equals(StateForSignUp.LOGIN_FINISH)) {
            String text = "Id : " + client.getId()
                    + "\nName : " + client.getName()
                    + "\nUsername : @" + client.getUsername()
                    + "\nNumber : " + client.getNumber()
                    + "\nEmail : " + client.getEmail()
                    + "\nPassword : " + client.getPassword();
            try {
                bot.execute(SendMessage.builder()
                        .chatId(UpdateProcessor.extractChatId(update))
                        .text(text)
                        .replyMarkup(INLINE_EDIT_STATE)
                        .build()
                );
            } catch (TelegramApiException e) {
                log.error(e.getLocalizedMessage());
            }
        } else {
            AuthSendMessageProcess.sendMessage(update, bot);
        }
    }

    public static void processWithEdition(Update update, TelegramLongPollingBot bot) {
        Client client = ClientRepoImpl.getInstance().get(UpdateProcessor.extractChatId(update));
        if (client != null && client.getState() != null && client.getState().equals(StateForSignUp.LOGIN_FINISH)) {
            String text = "Id : " + client.getId()
                    + "\nName : " + client.getName()
                    + "\nUsername : @" + client.getUsername()
                    + "\nNumber : " + client.getNumber()
                    + "\nEmail : " + client.getEmail()
                    + "\nPassword : " + client.getPassword();
            try {
                bot.execute(EditMessageText.builder()
                        .chatId(UpdateProcessor.extractChatId(update))
                        .text(text)
                        .messageId(UpdateProcessor.extractMessageId(update))
                        .replyMarkup(INLINE_EDIT_STATE)
                        .build()
                );
            } catch (TelegramApiException e) {
                log.error(e.getLocalizedMessage());
            }
        } else {
            AuthSendMessageProcess.sendMessage(update, bot);
        }
    }
}
