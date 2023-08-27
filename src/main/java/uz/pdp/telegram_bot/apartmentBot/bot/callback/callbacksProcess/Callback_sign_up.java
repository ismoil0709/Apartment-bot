package uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForSignUp;
import uz.pdp.telegram_bot.apartmentBot.bot.util.GetAndSetStates;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UpdateProcessor;
import uz.pdp.telegram_bot.apartmentBot.bot.util.buttons.Buttons_eng;

import java.util.List;

public class Callback_sign_up {
    private static final Logger log = LoggerFactory.getLogger(Callback_sign_up.class.getName());
    public static ReplyKeyboardMarkup MARKUP_CONTACT_STATE = ReplyKeyboardMarkup.builder()
            .resizeKeyboard(true)
            .keyboardRow(new KeyboardRow(List.of(Buttons_eng.BTN_CONTACT)))
            .keyboardRow(new KeyboardRow(List.of(Buttons_eng.BTN_BACK_CONTACT)))
            .build();

    public static void process(Update update, TelegramLongPollingBot bot) {
        try {
            bot.execute(EditMessageText.builder()
                    .text("You select sign up")
                    .messageId(UpdateProcessor.extractMessageId(update))
                    .chatId(UpdateProcessor.extractChatId(update))
                    .build()
            );
        } catch (TelegramApiException e) {
            log.error(e.getLocalizedMessage());
        }
        GetAndSetStates.setSignUpState(update, StateForSignUp.CONTACT);
        try {
            bot.execute(SendMessage.builder()
                    .text("Share contact")
                    .chatId(UpdateProcessor.extractChatId(update))
                    .replyMarkup(MARKUP_CONTACT_STATE).build());
        } catch (TelegramApiException e) {
            log.error(e.getLocalizedMessage());
        }
    }
}
