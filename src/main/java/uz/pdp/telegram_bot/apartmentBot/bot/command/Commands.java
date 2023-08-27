package uz.pdp.telegram_bot.apartmentBot.bot.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
public enum Commands {
    DEFAULT("def"),
    START("/start"),
    HELP("/help"),
    ADMIN("/admin"),
    MENU("\uD83D\uDCCA Menu"),
    SETTINGS("⚙️ Account settings"),
    AUTHENTICATION("\uD83D\uDD10 Authentication"),
    CONTACT("📞 Share contact"),
    LOCATION("\uD83D\uDCCD Share location"),
    BACK_CONTACT("⬅️ Back"),
    BACK_LOCATION("\uD83D\uDD19 Back"),
    BACK_EDIT_LOCATION("📍 Back");
    final String value;
    public static final Map<String, Commands> map = new HashMap<>();
    static {
        for (Commands c: Commands.values()) {
            map.put(c.getValue(),c);
        }
    }
    public static Commands of(String command){
        Commands commands = map.get(command);
        if(commands != null){
            return commands;
        }
        return DEFAULT;
    }

}
