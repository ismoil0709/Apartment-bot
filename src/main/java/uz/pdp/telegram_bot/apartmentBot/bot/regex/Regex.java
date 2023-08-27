package uz.pdp.telegram_bot.apartmentBot.bot.regex;

import java.util.regex.Pattern;

public class Regex {
    private static final Pattern WRITE_PRICE_VALIDATOR = Pattern.compile("\\d+\\s*-\\s*\\d+");
    private static final Pattern DIGIT_VALIDATOR = Pattern.compile("\\d+");
    private static final Pattern EMAIL_VALIDATOR = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final Pattern PASSWORD_VALIDATOR = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");

    private static final Pattern PHONE_NUMBER_VALIDATOR = Pattern.compile("\\+998\\d{9}");
    public static boolean writePriceValidator(String text) {
        return WRITE_PRICE_VALIDATOR.matcher(text).matches();
    }

    public static boolean digitValidator(String text) {
        return DIGIT_VALIDATOR.matcher(text).matches();
    }

    public static boolean emailValidator(String text) {
        return EMAIL_VALIDATOR.matcher(text).matches();
    }

    public static boolean passwordValidator(String text) {
        return PASSWORD_VALIDATOR.matcher(text).matches();
    }
    public static boolean phoneNumberValidator(String text){
        return PHONE_NUMBER_VALIDATOR.matcher(text).matches();
    }
}
