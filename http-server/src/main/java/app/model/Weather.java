package app.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

public class Weather {
    @Getter
    @Setter
    @JsonAlias({"id"})
    private Integer id;
    @Getter
    @Setter
    @JsonAlias({"city"})
    private String city;
    @Getter
    @Setter
    @JsonAlias({"temperature"})
    private float temperature;

    // Jackson needs the default constructor
    public Weather() {}

    public Weather(Integer id, String city, float temperature) {
        this.id = id;
        this.city = city;
        this.temperature = temperature;
    }
}
