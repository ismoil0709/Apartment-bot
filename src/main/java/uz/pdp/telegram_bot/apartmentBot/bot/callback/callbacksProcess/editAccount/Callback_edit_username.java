package uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess.editAccount;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.telegram_bot.apartmentBot.bot.command.AccountSettings;
import uz.pdp.telegram_bot.apartmentBot.bot.regex.Regex;
import uz.pdp.telegram_bot.apartmentBot.bot.repo.impl.ClientRepoImpl;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForBack;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForWrite;
import uz.pdp.telegram_bot.apartmentBot.bot.util.GetAndSetStates;
import uz.pdp.telegram_bot.apartmentBot.bot.util.Markup;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UpdateProcessor;
import uz.pdp.telegram_bot.apartmentBot.model.Client;

public class Callback_edit_username {
    public static void process(Update update, TelegramLongPollingBot bot) {
        if (update.hasMessage()) {
                Client client = ClientRepoImpl.getInstance().get(UpdateProcessor.extractChatId(update));
                client.setUsername(update.getMessage().getText());
                ClientRepoImpl.getInstance().put(client);
                try {
                    bot.execute(SendMessage.builder()
                            .text("Successfully")
                            .chatId(UpdateProcessor.extractChatId(update))
                            .build()
                    );
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                AccountSettings.process(update, bot);
        }
    }

    public static void sendMessage(Update update, TelegramLongPollingBot bot) {
        try {
            bot.execute(EditMessageText.builder()
                    .text("Enter username : ")
                    .messageId(UpdateProcessor.extractMessageId(update))
                    .chatId(UpdateProcessor.extractChatId(update))
                    .replyMarkup(Markup.INLINE_MARKUP_BACK_STATE)
                    .build()
            );
            GetAndSetStates.setWriteState(update, StateForWrite.WRITE_EDIT_USERNAME);
            GetAndSetStates.setBackButtonState(update, StateForBack.EDIT_USERNAME);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
