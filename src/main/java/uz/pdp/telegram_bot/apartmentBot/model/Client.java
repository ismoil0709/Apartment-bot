package uz.pdp.telegram_bot.apartmentBot.model;

import lombok.*;
import org.telegram.telegrambots.meta.api.objects.Contact;
import uz.pdp.telegram_bot.apartmentBot.bot.state.StateForSignUp;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Client {
    private String id;
    private String username;
    private String number;
    private String name;
    private List<Apartment> apartment;
    private String email;
    private String password;
    private StateForSignUp state;

    public static Client from(Contact contact) {
        Client client = new Client();
        client.id = String.valueOf(contact.getUserId());
        client.name = contact.getFirstName();
        client.number = contact.getPhoneNumber();
        return client;
    }
}
