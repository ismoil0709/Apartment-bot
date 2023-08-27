package uz.pdp.telegram_bot.apartmentBot.bot.util;

import org.telegram.telegrambots.meta.api.objects.Update;
import uz.pdp.telegram_bot.apartmentBot.bot.state.*;

public class GetAndSetStates {
    public static StateForWrite getWriteState(Update update) {
        if (!UtilLists.stateToEnterHashMap.isEmpty()) {
            return UtilLists.stateToEnterHashMap.get(UpdateProcessor.extractChatId(update));
        }
        return StateForWrite.DEFAULT;
    }
    public static StateForBack getBackButtonState(Update update){
        if(!UtilLists.stateForBackButtonMap.isEmpty()){
                return UtilLists.stateForBackButtonMap.get(UpdateProcessor.extractChatId(update));
        }
        return StateForBack.DEFAULT;
    }
    public static StateForGetList getIdentifyListState(Update update){
        if(!UtilLists.identifyList.isEmpty()){
            return UtilLists.identifyList.get(UpdateProcessor.extractChatId(update));
        }
        return StateForGetList.DEFAULT;
    }
    public static void setWriteState(Update update, StateForWrite state) {
        UtilLists.stateToEnterHashMap.put(UpdateProcessor.extractChatId(update), state);
    }
    public static void setBackButtonState(Update update, StateForBack state){
        UtilLists.stateForBackButtonMap.put(UpdateProcessor.extractChatId(update),state);
    }
    public static void setIdentifyListState(Update update, StateForGetList state){
        UtilLists.identifyList.put(UpdateProcessor.extractChatId(update),state);
    }
    public static void setSignUpState(Update update, StateForSignUp state){
        UtilLists.signUpStateMap.put(UpdateProcessor.extractChatId(update),state);
    }
    public static StateForSignUp getSignUpState(Update update){
        if(!UtilLists.signUpStateMap.isEmpty()){
            return UtilLists.signUpStateMap.get(UpdateProcessor.extractChatId(update));
        }
        return StateForSignUp.DEFAULT;
    }
    public static void setPostAnAdState(Update update, StateForPostAnAd state){
        UtilLists.stateForPostAnAdMap.put(UpdateProcessor.extractChatId(update),state);
    }
    public static StateForPostAnAd getPostAnAdState(Update update){
        if(!UtilLists.stateForPostAnAdMap.isEmpty()){
            return UtilLists.stateForPostAnAdMap.get(UpdateProcessor.extractChatId(update));
        }
        return StateForPostAnAd.DEFAULT;
    }
    public static void setNotFoundState(Update update, StateForNotFoundBack state){
        UtilLists.stateForNotFoundBackMap.put(UpdateProcessor.extractChatId(update),state);
    }
    public static StateForNotFoundBack getNotFoundState(Update update){
        if(!UtilLists.stateForNotFoundBackMap.isEmpty()){
            return UtilLists.stateForNotFoundBackMap.get(UpdateProcessor.extractChatId(update));
        }
        return StateForNotFoundBack.DEFAULT;
    }
    public static void setAddressBackState(Update update, StateForAddressBack state){
        UtilLists.stringStateForAddressBackMap.put(UpdateProcessor.extractChatId(update),state);
    }
    public static StateForAddressBack getAddressBackState(Update update){
        if(!UtilLists.stringStateForAddressBackMap.isEmpty()){
            return UtilLists.stringStateForAddressBackMap.get(UpdateProcessor.extractChatId(update));
        }
        return StateForAddressBack.DEFAULT;
    }
}
