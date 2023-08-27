package uz.pdp.telegram_bot.apartmentBot.bot.util.buttons;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;

import static uz.pdp.telegram_bot.apartmentBot.bot.callback.Callbacks.*;
import static uz.pdp.telegram_bot.apartmentBot.bot.callback.Callbacks.CALLBACK_SEARCH_WITH_CITY;

public class Buttons_uz {
    public static KeyboardButton BTN_REGISTER = KeyboardButton.builder()
            .text("Register")
            .build();
    public static InlineKeyboardButton BTN_POST_AN_AD = InlineKeyboardButton.builder()
            .text("Post an ad")
            .callbackData(CALLBACK_POST_AN_AD.getValue())
            .build();
    public static InlineKeyboardButton BTN_GET_ADDRESS = InlineKeyboardButton.builder()
            .text("Get Address")
            .callbackData(CALLBACK_GET_ADDRESS.getValue())
            .build();
    public static InlineKeyboardButton BTN_BACK = InlineKeyboardButton.builder()
            .text("Back")
            .callbackData(CALLBACK_BACK.getValue())
            .build();

    public static InlineKeyboardButton BTN_PREV = InlineKeyboardButton.builder()
            .text("⬅️")
            .callbackData(CALLBACK_PREV.getValue())
            .build();

    public static InlineKeyboardButton BTN_NEXT = InlineKeyboardButton.builder()
            .text("➡️")
            .callbackData(CALLBACK_NEXT.getValue())
            .build();



    public static InlineKeyboardButton BTN_APARTMENT_SEARCH = InlineKeyboardButton.builder()
            .text("Search apartment")
            .callbackData(CALLBACK_APARTMENT_SEARCH.getValue())
            .build();
    public static InlineKeyboardButton BTN_RECENTLY_UPLOADED = InlineKeyboardButton.builder()
            .text("Recently uploaded")
            .callbackData(CALLBACK_RECENTLY_UPLOADED.getValue())
            .build();

    public static KeyboardButton BTN_SETTINGS = KeyboardButton.builder()
            .text("⚙️ Account settings")
            .build();
    public static InlineKeyboardButton BTN_SEARCH_WITH_PRICE = InlineKeyboardButton.builder()
            .text("Search with price")
            .callbackData(CALLBACK_SEARCH_WITH_PRICE.getValue())
            .build();
    public static InlineKeyboardButton BTN_SEARCH_WITH_NUMBER_OF_ROOMS = InlineKeyboardButton.builder()
            .text("Search with number of rooms")
            .callbackData(CALLBACK_SEARCH_WITH_NUMBER_OF_ROOMS.getValue())
            .build();
    public static InlineKeyboardButton BTN_SEARCH_WITH_CITY = InlineKeyboardButton.builder()
            .text("Search with city")
            .callbackData(CALLBACK_SEARCH_WITH_CITY.getValue())
            .build();
}
