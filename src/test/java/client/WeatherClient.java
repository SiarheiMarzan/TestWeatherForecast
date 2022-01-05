package client;

import model.Forecast;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class WeatherClient {

    private String baseUrl;
    private RestTemplate restTemplate;

    public WeatherClient(RestTemplate restTemplate, String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    public <T> ResponseEntity<T> requestCurrentWeather(String city, Class<T> typeClass) {
        return restTemplate.getForEntity(baseUrl
                + "/data/2.5/weather?q=" + city, typeClass);
    }

    public <T> ResponseEntity<T> requestCurrentWeather(String city, String format, Class<T> typeClass) {
        return restTemplate.getForEntity(baseUrl
                + "/data/2.5/weather?q=" + city + "&mode=" + format, typeClass);
    }

    public ResponseEntity<Forecast> getCurrentWeather(String city, String unit) {
        String urlWeather = baseUrl + "/data/2.5/weather?q=" + city
                + "&units=" + unit;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Forecast> entity = new HttpEntity<>(headers);
        ResponseEntity<Forecast> responseCurrentWeather = restTemplate
                .exchange(urlWeather, HttpMethod.GET, entity, Forecast.class);
        return responseCurrentWeather;
    }

    public ResponseEntity<Forecast> getCurrentWeather(String city) {
        String urlWeather = baseUrl + "/data/2.5/weather?q=" + city;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Forecast> entity = new HttpEntity<>(headers);
        ResponseEntity<Forecast> responseCurrentWeather = restTemplate
                .exchange(urlWeather, HttpMethod.GET, entity, Forecast.class);
        return responseCurrentWeather;
    }

    public ResponseEntity<String> getForecast(String nameCity) {
        String city = baseUrl + "/data/2.5/forecast?q=" + nameCity
                + "&units=metric";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Forecast> entity = new HttpEntity<>(headers);
        ResponseEntity<String> responseForecast = restTemplate
                .exchange(city, HttpMethod.GET, entity, String.class);
        return responseForecast;
    }
}
