package client;

import model.Forecast;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

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
                + "/data/2.5/weather?q=" + city + "&mode=" + value + "&appid=" + getTestData("weather.api.key"), typeClass);
    }

    public ResponseEntity<Forecast> getCurrentWeather(String city, String unit) {
        String urlWeather = getTestData("weather.base.url") + "/data/2.5/weather?q=" + city
                + "&units=" + unit + "&appid=" + getTestData("weather.api.key");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Forecast> entity = new HttpEntity<>(headers);
        ResponseEntity<Forecast> responseCurrentWeather = restTemplate
                .exchange(urlWeather, HttpMethod.GET, entity, Forecast.class);
        return responseCurrentWeather;
    }

    public ResponseEntity<Forecast> getCurrentWeather(String city) {
        String urlWeather = getTestData("weather.base.url") + "/data/2.5/weather?q=" + city
                + "&appid=" + getTestData("weather.api.key");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Forecast> entity = new HttpEntity<>(headers);
        ResponseEntity<Forecast> responseCurrentWeather = restTemplate
                .exchange(urlWeather, HttpMethod.GET, entity, Forecast.class);
        return responseCurrentWeather;
    }

    public ResponseEntity<String> getForecast(String nameCity) {
        String city = getTestData("weather.base.url") + "/data/2.5/forecast?q=" + nameCity
                + "&units=metric&appid=" + getTestData("weather.api.key");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Forecast> entity = new HttpEntity<>(headers);
        ResponseEntity<String> responseForecast = restTemplate
                .exchange(city, HttpMethod.GET, entity, String.class);
        return responseForecast;
    }
}
