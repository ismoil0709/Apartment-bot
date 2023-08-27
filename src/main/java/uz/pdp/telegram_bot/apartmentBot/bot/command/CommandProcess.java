package uz.pdp.telegram_bot.apartmentBot.bot.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChat;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.telegram_bot.apartmentBot.bot.auth.AuthSendMessageProcess;
import uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess.editApartment.Callback_edit_apartment;
import uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess.editApartment.Callback_edit_location;
import uz.pdp.telegram_bot.apartmentBot.bot.repo.impl.ClientRepoImpl;
import uz.pdp.telegram_bot.apartmentBot.bot.state.*;
import uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess.Callback_menu;
import uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess.Home_sate;
import uz.pdp.telegram_bot.apartmentBot.bot.util.DbConnectForTest;
import uz.pdp.telegram_bot.apartmentBot.bot.util.GetAndSetStates;
import uz.pdp.telegram_bot.apartmentBot.bot.util.SendApartment;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UpdateProcessor;
import uz.pdp.telegram_bot.apartmentBot.bot.util.buttons.Buttons_eng;
import uz.pdp.telegram_bot.apartmentBot.model.Client;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CommandProcess {
    private static final Logger log = LoggerFactory.getLogger(CommandProcess.class.getName());

    public static void process(Update update, TelegramLongPollingBot bot) {
        String command = update.getMessage().getText();
        switch (Commands.of(command)) {
            case START -> {
                DbConnectForTest.putForTest(update);
                setStates(update);
                Home_sate.process(update, bot);
            }
            case ADMIN -> AdminCommand.process(update, bot);
            case HELP -> HelpCommand.process(update, bot);
            case MENU -> {
                GetAndSetStates.setBackButtonState(update, StateForBack.MENU);
                Callback_menu.process(update, bot);
            }
            case SETTINGS -> AccountSettings.process(update, bot);
            case AUTHENTICATION -> AuthenticationCommand.process(update, bot);
            case BACK_CONTACT -> {
                try {
                    bot.execute(SendMessage.builder()
                            .text("Sign up canceled")
                            .replyMarkup(Home_sate.INLINE_MARKUP_HOME_STATE)
                            .chatId(UpdateProcessor.extractChatId(update))
                            .build());
                } catch (TelegramApiException e) {
                    log.error(e.getLocalizedMessage());
                }
                AuthSendMessageProcess.sendMessage(update, bot);
            }
            case BACK_LOCATION -> {
                try {
                    bot.execute(SendMessage.builder()
                            .text("Canceled")
                            .replyMarkup(Home_sate.INLINE_MARKUP_HOME_STATE)
                            .chatId(UpdateProcessor.extractChatId(update))
                            .build()
                    );
                    Callback_menu.process(update, bot);
                } catch (TelegramApiException e) {
                    log.error(e.getLocalizedMessage());
                }
            }
            case BACK_EDIT_LOCATION -> {
                try {
                    bot.execute(SendMessage.builder()
                            .text("Canceled")
                            .replyMarkup(Home_sate.INLINE_MARKUP_HOME_STATE)
                            .chatId(UpdateProcessor.extractChatId(update))
                            .build()
                    );
                    Callback_edit_apartment.sendMessage(update, bot);
                } catch (TelegramApiException e) {
                    log.error(e.getLocalizedMessage());
                }
            }
        }
    }

    private static void setStates(Update update) {
        GetAndSetStates.setWriteState(update, StateForWrite.DEFAULT);
        GetAndSetStates.setIdentifyListState(update, StateForGetList.DEFAULT);
        GetAndSetStates.setBackButtonState(update, StateForBack.DEFAULT);
        GetAndSetStates.setSignUpState(update, StateForSignUp.DEFAULT);
        GetAndSetStates.setPostAnAdState(update, StateForPostAnAd.DEFAULT);
        GetAndSetStates.setNotFoundState(update, StateForNotFoundBack.DEFAULT);
    }
}
