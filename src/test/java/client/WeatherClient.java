package client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Forecast;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static util.DataReader.getTestData;

public class WeatherClient<T> {

    private T Forecast;
    private T String;

    private RestTemplate restTemplate;

    public WeatherClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<T> requestApi(String city, Class typeClass) {

        return restTemplate.getForEntity(getTestData("weather.base.url")
                + "/data/2.5/weather?q=" + city + "&appid=" + getTestData("weather.api.key"), typeClass);
    }

    public double getDataWeather(String city) {
        String urlWeather = getTestData("weather.base.url") + "/data/2.5/weather?q=" + city
                + "&appid=" + getTestData("weather.api.key");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[]{APPLICATION_JSON}));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Forecast> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Forecast> response = restTemplate.exchange(urlWeather, HttpMethod.GET, entity, Forecast.class);
        return response.getBody().getMain().getTemp();
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
