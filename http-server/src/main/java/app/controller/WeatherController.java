package app.controller;

import app.model.Weather;
import app.service.WeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import http.ContentType;
import http.HttpStatus;
import server.Request;
import server.Response;

import java.util.List;

public class WeatherController extends Controller {
    private WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        super();
        this.weatherService = weatherService;
    }

    // GET /weather
    public Response getWeather() {
        try {
            List weatherData = this.weatherService.getWeather();
            // "[ { \"id\": 1, \"city\": \"Vienna\", \"temperature\": 9.0 }, { ... }, { ... } ]"
            String weatherDataJSON = this.getObjectMapper().writeValueAsString(weatherData);

            return new Response(
                HttpStatus.OK,
                ContentType.JSON,
                weatherDataJSON
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new Response(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ContentType.JSON,
                "{ \"message\" : \"Internal Server Error\" }"
            );
        }
    }

    // POST /weather
    public Response addWeather(Request request) {
        Weather weather = null;
        try {

            // request.getBody() => "{ \"id\": 4, \"city\": \"Graz\", ... }
            weather = this.getObjectMapper().readValue(request.getBody(), Weather.class);
            this.weatherService.addWeather(weather);

            return new Response(
                HttpStatus.CREATED,
                ContentType.JSON,
                "{ message: \"Success\" }"
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new Response(
            HttpStatus.INTERNAL_SERVER_ERROR,
            ContentType.JSON,
            "{ \"message\" : \"Internal Server Error\" }"
      //  protected String id;
        );
    }
}
