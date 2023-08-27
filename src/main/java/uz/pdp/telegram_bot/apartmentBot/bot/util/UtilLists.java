package uz.pdp.telegram_bot.apartmentBot.bot.util;

import uz.pdp.telegram_bot.apartmentBot.bot.state.*;
import uz.pdp.telegram_bot.apartmentBot.model.Apartment;
import uz.pdp.telegram_bot.apartmentBot.model.Client;

import java.util.HashMap;
import java.util.Map;

public class UtilLists {
    public static Map<String, StateForWrite> stateToEnterHashMap = new HashMap<>();
    public static Map<String, StateForBack> stateForBackButtonMap = new HashMap<>();
    public static Map<String, StateForGetList> identifyList = new HashMap<>();
    public static Map<String, StateForSignUp> signUpStateMap = new HashMap<>();
    public static Map<String, Client> clientSignUpMap = new HashMap<>();
    public static Map<String,Client> clientLoginMap = new HashMap<>();
    public static Map<String, Apartment> apartmentMap = new HashMap<>();
    public static Map<String,StateForNotFoundBack> stateForNotFoundBackMap = new HashMap<>();
    public static Map<String, StateForPostAnAd> stateForPostAnAdMap = new HashMap<>();
    public static Map<String,StateForAddressBack> stringStateForAddressBackMap = new HashMap<>();
}
