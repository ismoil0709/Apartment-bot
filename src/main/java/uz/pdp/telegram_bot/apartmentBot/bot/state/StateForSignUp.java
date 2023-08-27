package uz.pdp.telegram_bot.apartmentBot.bot.state;

public enum StateForSignUp {
    DEFAULT,
    CONTACT,
    EMAIL,
    WRITE_EMAIL,
    CODE,
    WRITE_CODE,
    PASSWORD,
    WRITE_PASSWORD,
    LOGIN_EMAIL,
    LOGIN_WRITE_EMAIL,
    LOGIN_PASSWORD,
    LOGIN_WRITE_PASSWORD,
    LOGIN_FINISH,
    REENTER;
}
