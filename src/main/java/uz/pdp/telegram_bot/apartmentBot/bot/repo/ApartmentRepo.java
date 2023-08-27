package uz.pdp.telegram_bot.apartmentBot.bot.repo;

import uz.pdp.telegram_bot.apartmentBot.model.Apartment;

public interface ApartmentRepo {
    void put(Apartment apartment);
    Apartment remove(String id);
    Apartment get(String id);
}
