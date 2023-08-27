package uz.pdp.telegram_bot.apartmentBot.model;

import lombok.*;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Apartment{
    private UUID id = UUID.randomUUID();
    private LocalDateTime dateTime;
    private String userId;
    private String username;
    private String description;
    private Address address;
    private PhotoSize photo;
    private Double price;
    private String number;
    private Integer rooms;
}
