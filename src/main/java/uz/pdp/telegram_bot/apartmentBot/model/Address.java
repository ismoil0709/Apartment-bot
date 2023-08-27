package uz.pdp.telegram_bot.apartmentBot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private Double latitude;
    private Double longitude;
    private String city;
}
