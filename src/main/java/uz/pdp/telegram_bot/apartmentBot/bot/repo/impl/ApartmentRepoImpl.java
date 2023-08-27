package uz.pdp.telegram_bot.apartmentBot.bot.repo.impl;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import uz.pdp.telegram_bot.apartmentBot.bot.repo.ApartmentRepo;
import uz.pdp.telegram_bot.apartmentBot.model.Address;
import uz.pdp.telegram_bot.apartmentBot.model.Apartment;

import java.time.LocalDateTime;
import java.util.*;

public class ApartmentRepoImpl implements ApartmentRepo {
    private static final List<Apartment> APARTMENTS = new ArrayList<>();
    private static ApartmentRepo apartmentRepo;

    static {
        APARTMENTS.add(new Apartment(UUID.randomUUID(), LocalDateTime.now(), "1084271471",
                "ismoil_0709",
                "h",
                new Address(41.361506, 69.166629,"BUco"),
                new PhotoSize("AgACAgIAAxkBAAOsZOo3S9VEz4uELrh2dHHtQgleFBYAApXNMRuIOVlLDDfsJs3Mjr8BAAMCAANzAAMwBA",
                        "AQADlc0xG4g5WUt4",
                        90, 83, 1661, null),
                1D, "e",7
        ));
        APARTMENTS.add(new Apartment(UUID.randomUUID(),LocalDateTime.parse("2022-08-27T20:02:06")
                , "1084271471",
                "rrrr",
                "h",
                new Address(33.361506, 64.166629,"Tashkent"),
                new PhotoSize("AgACAgIAAxkBAAOsZOo3S9VEz4uELrh2dHHtQgleFBYAApXNMRuIOVlLDDfsJs3Mjr8BAAMCAANzAAMwBA",
                        "AQADlc0xG4g5WUt4",
                        90, 83, 1661, null),
                1700D, "r",5
        ));

    }

    @Override
    public void put(Apartment apartment) {
        if (APARTMENTS.contains(apartment)) {
            for (int i = 0; i < APARTMENTS.size(); i++) {
                if (APARTMENTS.get(i).getId().equals(apartment.getId())) {
                    APARTMENTS.set(i, apartment);
                    break;
                }
            }
        } else {
            APARTMENTS.add(apartment);
        }
    }

    @Override
    public Apartment remove(String id) {
        for (Apartment a : APARTMENTS) {
            if (a.getId().equals(UUID.fromString(id))) {
                APARTMENTS.remove(a);
                return a;
            }
        }
        return null;
    }
    public List<Apartment> list(){
        return APARTMENTS;
    }

    @Override
    public Apartment get(String id) {
        for (Apartment a: APARTMENTS) {
            if(UUID.fromString(id).equals(a.getId())){
                return a;
            }
        }
        return null;
    }
    public static ApartmentRepoImpl getInstance() {
        if (apartmentRepo == null) {
            return new ApartmentRepoImpl();
        }
        return (ApartmentRepoImpl) apartmentRepo;
    }
}