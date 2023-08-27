package uz.pdp.telegram_bot.apartmentBot.bot.util;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import uz.pdp.telegram_bot.apartmentBot.bot.util.buttons.Buttons_eng;

import java.util.List;

public class Markup {
    public static final InlineKeyboardMarkup INLINE_MARKUP_BACK_STATE = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(Buttons_eng.BTN_BACK))
            .build();
}
