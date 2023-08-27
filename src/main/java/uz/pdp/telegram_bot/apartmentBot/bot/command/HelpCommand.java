package uz.pdp.telegram_bot.apartmentBot.bot.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.telegram_bot.apartmentBot.bot.util.UpdateProcessor;

public class HelpCommand {
    private static final Logger log = LoggerFactory.getLogger(HelpCommand.class.getName());

    public static void process(Update update, TelegramLongPollingBot bot) {
        try {
            bot.execute(SendMessage.builder()
                    .text("""
                            🔨 Bot instructions:
                                                                                                                                                                
                                                                                                                                                                
                                                                                                                                                                You can easily find the apartments you need through this bot ✅
                                                                                                                                                                
                                                                                                                                                                Apartment search 🔍
                                                                                                                                                                
                                                                                                                                                                  - Press the "Menu" button and press the "Search apartment" button
                                                                                                                                                                and find the apartment you want
                                                                                                                                                                
                                                                                                                                                                📤 Upload the apartment to the bot
                                                                                                                                                                
                                                                                                                                                                - Click the menu button and click the Post an ad button and register and you will be able to upload as many ads as you want for free
                            """)
                    .chatId(UpdateProcessor.extractChatId(update))
                    .build()
            );
        } catch (TelegramApiException e) {
            log.error(e.getLocalizedMessage());
        }
    }
}
