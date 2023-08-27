package uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForBack;
import uz.pdp.telegram_bot.apartmentBot.bot.util.buttons.Buttons_eng;
import uz.pdp.telegram_bot.apartmentBot.bot.util.GetAndSetStates;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UpdateProcessor;

import java.util.List;

public class Callback_menu {
    private static final Logger log = LoggerFactory.getLogger(Callback_menu.class.getName());

    public static InlineKeyboardMarkup INLINE_MARKUP_MENU_STATE = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(Buttons_eng.BTN_APARTMENT_SEARCH, Buttons_eng.BTN_POST_AN_AD))
            .keyboardRow(List.of(Buttons_eng.BTN_MY_APARTMENTS))
            .build();

    public static void processWithEdition(Update update, TelegramLongPollingBot bot) {
        GetAndSetStates.setBackButtonState(update,StateForBack.MENU);
        try {
            bot.execute(
                    EditMessageText.builder()
                            .chatId(UpdateProcessor.extractChatId(update))
                            .messageId(UpdateProcessor.extractMessageId(update))
                            .text("Choose one of the menus ")
                            .replyMarkup(INLINE_MARKUP_MENU_STATE)
                            .build()

            );
        } catch (TelegramApiException e) {
            log.error(e.getLocalizedMessage());
        }
    }
    public static void process(Update update,TelegramLongPollingBot bot){
        try {
            bot.execute(DeleteMessage.builder()
                    .messageId(UpdateProcessor.extractMessageId(update))
                    .chatId(UpdateProcessor.extractChatId(update))
                    .build()
            );
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
        try {
            bot.execute(
                    SendMessage.builder()
                            .chatId(UpdateProcessor.extractChatId(update))
                            .text("Choose one of the menus ")
                            .replyMarkup(INLINE_MARKUP_MENU_STATE)
                            .build()

            );
        } catch (TelegramApiException e) {
            log.error(e.getLocalizedMessage());
        }
    }
}
