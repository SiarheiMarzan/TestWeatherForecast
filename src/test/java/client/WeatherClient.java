package client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Forecast;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URL;

import static util.DataReader.getTestData;

public class WeatherClient {

    private RestTemplate restTemplate;

    public WeatherClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> ResponseEntity<T> requestCurrentWeather(String city, Class<T> typeClass) {
        return restTemplate.getForEntity(getTestData("weather.base.url")
                + "/data/2.5/weather?q=" + city + "&appid=" + getTestData("weather.api.key"), typeClass);
    }

    public <T> ResponseEntity<T> requestCurrentWeather(String city, String value, Class<T> typeClass) {
        return restTemplate.getForEntity(getTestData("weather.base.url")
                + "/data/2.5/weather?q=" + city + "&mode=" + "&appid=" + getTestData("weather.api.key"), typeClass);
    }

    public ResponseEntity<Forecast> getCurrentWeather(String city, String unit) {
        String urlWeather = getTestData("weather.base.url") + "/data/2.5/weather?q=" + city
                + "&units=" + unit + "&appid=" + getTestData("weather.api.key");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Forecast> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Forecast> responseCurrentWeather = restTemplate
                .exchange(urlWeather, HttpMethod.GET, entity, Forecast.class);
        return responseCurrentWeather;
    }

    public ResponseEntity<Forecast> getCurrentWeather(String city) {
        String urlWeather = getTestData("weather.base.url") + "/data/2.5/weather?q=" + city
                + "&appid=" + getTestData("weather.api.key");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Forecast> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Forecast> responseCurrentWeather = restTemplate
                .exchange(urlWeather, HttpMethod.GET, entity, Forecast.class);
        return responseCurrentWeather;
    }

    public ResponseEntity<String> getCurrentWeatherTest(String nameCity) {
        String city = getTestData("weather.base.url") + "/data/2.5/forecast?q=" + nameCity
                + "&units=metric&appid=" + getTestData("weather.api.key");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Forecast> entity = new HttpEntity<>(headers);
        ResponseEntity<String> responseCurrentWeather = restTemplate
                .exchange(city, HttpMethod.GET, entity, String.class);
        return responseCurrentWeather;
    }

    public JsonNode getDataTreeJson(String url) {
        JsonNode getJsonForTest = null;
        try {
            getJsonForTest = new ObjectMapper().readTree(new URL(url));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return getJsonForTest;
    }

}
