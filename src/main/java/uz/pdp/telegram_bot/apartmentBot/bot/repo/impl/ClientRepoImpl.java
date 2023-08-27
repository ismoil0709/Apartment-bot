package uz.pdp.telegram_bot.apartmentBot.bot.repo.impl;

import uz.pdp.telegram_bot.apartmentBot.bot.repo.ClientRepo;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForSignUp;
import uz.pdp.telegram_bot.apartmentBot.model.Client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ClientRepoImpl implements ClientRepo {
    private static ClientRepo clientRepo;

    private ClientRepoImpl() {

    }

    private static final List<Client> CLIENTS = new ArrayList<>();

    static {
        CLIENTS.add(new Client("1084271471","ismoil_0709","+998993310550","      ", new ArrayList<>(),
                "i","i", StateForSignUp.LOGIN_FINISH
                )
        );
    }

    @Override
    public void put(Client client) {
        if (CLIENTS.contains(client)) {
            for (int i = 0; i < CLIENTS.size(); i++) {
                if (CLIENTS.get(i).getId().equals(client.getId())) {
                    CLIENTS.set(i, client);
                    break;
                }
            }
        } else {
            CLIENTS.add(client);
        }
    }

    @Override
    public void remove(String userId) {
        for (Client client : CLIENTS) {
            if (client.getId().equals(userId)) {
                CLIENTS.remove(client);
                break;
            }
        }
    }

    @Override
    public Client get(String userId) {
        for (Client client : CLIENTS) {
            if (client.getId().equals(userId)) {
                return client;
            }
        }
        return null;
    }
    public List<Client> list(){
        return CLIENTS;
    }

    public static ClientRepoImpl getInstance() {
        if (clientRepo == null) {
            return new ClientRepoImpl();
        }
        return (ClientRepoImpl) clientRepo;
    }
}
