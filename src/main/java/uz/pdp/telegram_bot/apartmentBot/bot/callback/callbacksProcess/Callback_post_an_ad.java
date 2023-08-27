package uz.pdp.telegram_bot.apartmentBot.bot.callback.callbacksProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.telegram_bot.apartmentBot.bot.auth.AuthSendMessageProcess;
import uz.pdp.telegram_bot.apartmentBot.bot.repo.impl.ClientRepoImpl;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForBack;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForPostAnAd;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForSignUp;
import uz.pdp.telegram_bot.apartmentBot.bot.util.GetAndSetStates;
import uz.pdp.telegram_bot.apartmentBot.bot.util.Markup;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UpdateProcessor;
import uz.pdp.telegram_bot.apartmentBot.model.Client;

public class Callback_post_an_ad {
    private static final Logger log = LoggerFactory.getLogger(Callback_post_an_ad.class.getName());
    public static void process(Update update, TelegramLongPollingBot bot) {
        GetAndSetStates.setBackButtonState(update, StateForBack.POST_AN_AD);
        Client client = ClientRepoImpl.getInstance().get(UpdateProcessor.extractChatId(update));
        if (client != null && client.getState() != null && client.getState().equals(StateForSignUp.LOGIN_FINISH)) {
            try {
                bot.execute(EditMessageText.builder()
                        .text("Send photo \uD83D\uDDBC")
                        .replyMarkup(Markup.INLINE_MARKUP_BACK_STATE)
                        .messageId(UpdateProcessor.extractMessageId(update))
                        .chatId(UpdateProcessor.extractChatId(update))
                        .build());
            } catch (TelegramApiException e) {
                log.error(e.getLocalizedMessage());
            }
            GetAndSetStates.setPostAnAdState(update, StateForPostAnAd.PHOTO);
        } else {
            AuthSendMessageProcess.editMessage(update, bot);
        }

    }
}
