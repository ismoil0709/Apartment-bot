package uz.pdp.telegram_bot.apartmentBot.bot.util.buttons;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import uz.pdp.telegram_bot.apartmentBot.bot.callback.Callbacks;
import uz.pdp.telegram_bot.apartmentBot.bot.command.Commands;

import static uz.pdp.telegram_bot.apartmentBot.bot.callback.Callbacks.*;

public class Buttons_eng {
    public static KeyboardButton BTN_MENU = KeyboardButton.builder()
            .text(Commands.MENU.getValue())
            .build();

    public static KeyboardButton BTN_AUTHENTICATION = KeyboardButton.builder()
            .text(Commands.AUTHENTICATION.getValue())
            .build();
    public static InlineKeyboardButton BTN_POST_AN_AD = InlineKeyboardButton.builder()
            .text("\uD83D\uDCE4 Post an AD")
            .callbackData(CALLBACK_POST_AN_AD.getValue())
            .build();
    public static InlineKeyboardButton BTN_GET_ADDRESS = InlineKeyboardButton.builder()
            .text("\uD83D\uDCCD")
            .callbackData(CALLBACK_GET_ADDRESS.getValue())
            .build();
    public static InlineKeyboardButton BTN_BACK = InlineKeyboardButton.builder()
            .text("\uD83D\uDD19 Back")
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
            .text("\uD83D\uDD0D Search apartment")
            .callbackData(CALLBACK_APARTMENT_SEARCH.getValue())
            .build();
    public static InlineKeyboardButton BTN_RECENTLY_UPLOADED = InlineKeyboardButton.builder()
            .text("\uD83D\uDCDA Recently uploaded")
            .callbackData(CALLBACK_RECENTLY_UPLOADED.getValue())
            .build();

    public static KeyboardButton BTN_SETTINGS = KeyboardButton.builder()
            .text(Commands.SETTINGS.getValue())
            .build();
    public static InlineKeyboardButton BTN_SEARCH_WITH_PRICE = InlineKeyboardButton.builder()
            .text("\uD83D\uDCB8 Search with price")
            .callbackData(CALLBACK_SEARCH_WITH_PRICE.getValue())
            .build();
    public static InlineKeyboardButton BTN_SEARCH_WITH_NUMBER_OF_ROOMS = InlineKeyboardButton.builder()
            .text("\uD83C\uDFB2 Search with number of rooms")
            .callbackData(CALLBACK_SEARCH_WITH_NUMBER_OF_ROOMS.getValue())
            .build();
    public static InlineKeyboardButton BTN_SEARCH_WITH_CITY = InlineKeyboardButton.builder()
            .text("\uD83C\uDF06 Search with city")
            .callbackData(CALLBACK_SEARCH_WITH_CITY.getValue())
            .build();
    public static InlineKeyboardButton BTN_SIGN_UP = InlineKeyboardButton.builder()
            .text("\uD83D\uDCF2 Sign up")
            .callbackData(CALLBACK_SIGN_UP.getValue())
            .build();
    public static InlineKeyboardButton BTN_LOGIN = InlineKeyboardButton.builder()
            .text("\uD83D\uDD11 Login")
            .callbackData(CALLBACK_LOGIN.getValue())
            .build();
    public static KeyboardButton BTN_CONTACT = KeyboardButton.builder()
            .text(Commands.CONTACT.getValue())
            .requestContact(true)
            .build();
    public static InlineKeyboardButton BTN_REENTER = InlineKeyboardButton.builder()
            .text("\uD83D\uDD04 Re Enter")
            .callbackData(CALLBACK_REENTER.getValue())
            .build();
    public static InlineKeyboardButton BTN_MY_APARTMENTS = InlineKeyboardButton.builder()
            .text("\uD83D\uDCD6 My apartments")
            .callbackData(CALLBACK_MY_APARTMENTS.getValue())
            .build();
    public static InlineKeyboardButton BTN_EDIT_NAME = InlineKeyboardButton.builder()
            .text("✏️ Edit name")
            .callbackData(CALLBACK_EDIT_NAME.getValue())
            .build();
    public static InlineKeyboardButton BTN_EDIT_EMAIL = InlineKeyboardButton.builder()
            .text("✏️Edit email")
            .callbackData(CALLBACK_EDIT_EMAIL.getValue())
            .build();
    public static InlineKeyboardButton BTN_EDIT_PASSWORD = InlineKeyboardButton.builder()
            .text("✏️ Edit password")
            .callbackData(CALLBACK_EDIT_PASSWORD.getValue())
            .build();
    public static InlineKeyboardButton BTN_EDIT_USERNAME = InlineKeyboardButton.builder()
            .text("✏️ Edit username")
            .callbackData(CALLBACK_EDIT_USERNAME.getValue())
            .build();
    public static InlineKeyboardButton BTN_EDIT_NUMBER = InlineKeyboardButton.builder()
            .text("✏️ Edit number")
            .callbackData(CALLBACK_EDIT_PHONE_NUMBER.getValue())
            .build();
    public static KeyboardButton BTN_LOCATION = KeyboardButton.builder()
            .text(Commands.LOCATION.getValue())
            .requestLocation(true)
            .build();
    public static KeyboardButton BTN_BACK_LOCATION = KeyboardButton.builder()
            .text(Commands.BACK_LOCATION.getValue())
            .requestLocation(false)
            .build();
    public static KeyboardButton BTN_BACK_CONTACT = KeyboardButton.builder()
            .text(Commands.BACK_CONTACT.getValue())
            .requestLocation(false)
            .build();
    public static KeyboardButton BTN_EDIT_LOCATION_BACK = KeyboardButton.builder()
            .text(Commands.BACK_EDIT_LOCATION.getValue())
            .build();
    public static InlineKeyboardButton BTN_EDIT_APARTMENT = InlineKeyboardButton.builder()
            .text("\uD83D\uDCDD Edit")
            .callbackData(CALLBACK_EDIT_APARTMENT.getValue())
            .build();
    public static InlineKeyboardButton BTN_DELETE_APARTMENT = InlineKeyboardButton.builder()
            .text("\uD83D\uDDD1 Delete")
            .callbackData(CALLBACKS_DELETE_APARTMENT.getValue())
            .build();
    public static InlineKeyboardButton BTN_EDIT_PHOTO = InlineKeyboardButton.builder()
            .text("\uD83C\uDF05 Edit photo")
            .callbackData(CALLBACK_EDIT_PHOTO.getValue())
            .build();
    public static InlineKeyboardButton BTN_EDIT_LOCATION = InlineKeyboardButton.builder()
            .text("\uD83D\uDCCC Edit location")
            .callbackData(CALLBACK_EDIT_LOCATION.getValue())
            .build();
    public static InlineKeyboardButton BTN_EDIT_PRICE = InlineKeyboardButton.builder()
            .text("\uD83D\uDCB8 Edit price ")
            .callbackData(CALLBACK_EDIT_PRICE.getValue())
            .build();
    public static InlineKeyboardButton BTN_EDIT_DESCRIPTION = InlineKeyboardButton.builder()
            .text("\uD83D\uDCD6 Edit description")
            .callbackData(CALLBACK_EDIT_DESCRIPTION.getValue())
            .build();
    public static InlineKeyboardButton BTN_EDIT_CITY = InlineKeyboardButton.builder()
            .text("\uD83C\uDFD9 Edit city")
            .callbackData(CALLBACK_EDIT_CITY.getValue())
            .build();
    public static InlineKeyboardButton BTN_EDIT_ROOMS = InlineKeyboardButton.builder()
            .text("\uD83D\uDD22 Edit number of rooms")
            .callbackData(CALLBACK_EDIT_ROOMS.getValue())
            .build();
    public static InlineKeyboardButton BTN_EDIT_BACK = InlineKeyboardButton.builder()
            .text("\uD83D\uDD19 Back")
            .callbackData(CALLBACK_EDIT_BACK.getValue())
            .build();

    public static InlineKeyboardButton BTN_NOT_FOUND_BACK = InlineKeyboardButton.builder()
            .text("\uD83D\uDD19 Back")
            .callbackData(CALLBACK_NOT_FOUND_BACK.getValue())
            .build();

}
