package uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.telegram_bot.apartmentBot.bot.util.buttons.Buttons_eng;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UpdateProcessor;

import java.util.List;

public class Home_sate {
    private static final Logger log = LoggerFactory.getLogger(Home_sate.class.getName());
    public static ReplyKeyboardMarkup INLINE_MARKUP_HOME_STATE = ReplyKeyboardMarkup.builder()
            .resizeKeyboard(true)
            .keyboardRow(new KeyboardRow(List.of(Buttons_eng.BTN_MENU)))
            .keyboardRow(new KeyboardRow(List.of(Buttons_eng.BTN_SETTINGS, Buttons_eng.BTN_AUTHENTICATION)))
            .build();
    public static void process(Update update, TelegramLongPollingBot bot) {
        try {
            bot.execute(SendMessage.builder()
                    .chatId(UpdateProcessor.extractChatId(update))
                    .text("Welcome to BOT \uD83D\uDE4C")
                    .replyMarkup(INLINE_MARKUP_HOME_STATE)
                    .build());
        } catch (TelegramApiException e) {
            log.error(e.getLocalizedMessage());
        }
    }
}
