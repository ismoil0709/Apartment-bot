package uz.pdp.telegram_bot.apartmentBot.bot.repo;

import uz.pdp.telegram_bot.apartmentBot.model.Client;

public interface ClientRepo {
    void put(Client client);
    void remove(String userId);
    Client get(String userId);
}
