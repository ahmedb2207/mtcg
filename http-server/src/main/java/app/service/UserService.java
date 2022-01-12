package app.service;

import app.model.Weather;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<User> userData;

    public WeatherService() {
        userData = new ArrayList<>();
        userData.add(new User("Vienna", "Password"));
        userData.add(new User("Ahmed","Password"));
        userData.add(new Weather(3,"Tokyo", 12.f));
    }

    // GET /weather/:id
    public Weather getWeather(Integer ID) {
        Weather foundWaether = weatherData.stream()
                .filter(waether -> ID == waether.getId())
                .findAny()
                .orElse(null);

        return foundWaether;}

    // GET /weather
    public List<Weather> getWeather() {
        return weatherData;
    }

    // POST /weather
    public void addWeather(Weather weather) {
        weatherData.add(weather);
    }
}
