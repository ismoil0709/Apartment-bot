package uz.pdp.telegram_bot.apartmentBot.bot.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.telegram_bot.apartmentBot.bot.auth.AuthSendMessageProcess;
import uz.pdp.telegram_bot.apartmentBot.bot.repo.impl.ClientRepoImpl;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForSignUp;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UpdateProcessor;
import uz.pdp.telegram_bot.apartmentBot.model.Client;

public class AuthenticationCommand {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationCommand.class.getName());

    public static void process(Update update, TelegramLongPollingBot bot) {
        Client client = ClientRepoImpl.getInstance().get(UpdateProcessor.extractChatId(update));
        if (client != null && client.getState() != null && client.getState().equals(StateForSignUp.LOGIN_FINISH)) {
            try {
                bot.execute(SendMessage.builder()
                        .text("you are registered")
                        .chatId(UpdateProcessor.extractChatId(update)).build());
            } catch (TelegramApiException e) {
                log.error(e.getLocalizedMessage());
            }
        } else {
            AuthSendMessageProcess.sendMessage(update, bot);
        }
    }
}
