package uz.pdp.telegram_bot.apartmentBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import uz.pdp.telegram_bot.apartmentBot.bot.ApartmentTelegramBot;
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class.getName());
    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new ApartmentTelegramBot());
        } catch (TelegramApiException e) {
            log.error(e.getLocalizedMessage());
        }
    }

}
