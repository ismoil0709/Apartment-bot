package uz.pdp.telegram_bot.apartmentBot.bot.callback;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum Callbacks {
    CALLBACK_POST_AN_AD("post_add"),
    CALLBACK_APARTMENT_SEARCH("search_a"),
    CALLBACK_RECENTLY_UPLOADED("recently_u"),
    CALLBACK_GET_ADDRESS("g_address"),
    CALLBACK_PREV("prev"),
    CALLBACK_NEXT("next"),
    CALLBACK_SEARCH_WITH_PRICE("s_price"),
    CALLBACK_SEARCH_WITH_NUMBER_OF_ROOMS("s_rooms"),
    CALLBACK_SEARCH_WITH_CITY("s_city"),
    CALLBACK_BACK("back"),
    CALLBACK_SIGN_UP("sign_up"),
    CALLBACK_LOGIN("login"),
    CALLBACK_REENTER("re_enter"),
    CALLBACK_MY_APARTMENTS("m_a"),
    CALLBACK_EDIT_NAME("e_name"),
    CALLBACK_EDIT_EMAIL("e_email"),
    CALLBACK_EDIT_PASSWORD("e_pass"),
    CALLBACK_EDIT_PHONE_NUMBER("e_num"),
    CALLBACK_EDIT_USERNAME("e_username"),
    CALLBACK_EDIT_APARTMENT("e_apartment"),
    CALLBACK_EDIT_PHOTO("e_photo"),
    CALLBACK_EDIT_LOCATION("e_location"),
    CALLBACK_EDIT_PRICE("e_price"),
    CALLBACK_EDIT_ROOMS("e_rooms"),
    CALLBACK_EDIT_CITY("e_city"),
    CALLBACK_EDIT_DESCRIPTION("e_des"),
    CALLBACKS_DELETE_APARTMENT("d_apartment"),
    CALLBACK_NOT_FOUND_BACK("not_found"),
    CALLBACK_EDIT_BACK("e_my_back");

    private final String value;
    public static final Map<String, Callbacks> map = new HashMap<>();
    static {
        for (Callbacks c: Callbacks.values()) {
            map.put(c.getValue(),c);
        }
    }
    public static Callbacks of(String data){
        Callbacks callbacks = map.get(data);
        if(callbacks != null){
            return callbacks;
        }
        throw new IllegalArgumentException();
    }
}
