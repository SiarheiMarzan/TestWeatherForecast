package client;

import model.Forecast;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherClient {

    private RestTemplate restTemplate;
    private String url;

    public WeatherClient(RestTemplate restTemplate, String url) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    public <T> ResponseEntity<T> sendRequestCurrentWeatherJson(String city, Class<T> typeClass) {
        return restTemplate.getForEntity(url
                + "/data/2.5/weather?q=" + city, typeClass);
    }

    public <T> ResponseEntity<T> sendRequestCurrentWeatherXml(String city, String format, Class<T> typeClass) {
        return restTemplate.getForEntity(url
                + "/data/2.5/weather?q=" + city + "&mode=" + format, typeClass);
    }

    public ResponseEntity<Forecast> getCurrentWeatherInMetricUnit(String city, String unit) {
        String urlWeather = url + "/data/2.5/weather?q=" + city
                + "&units=" + unit;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Forecast> entity = new HttpEntity<>(headers);
        ResponseEntity<Forecast> responseCurrentWeather = restTemplate
                .exchange(urlWeather, HttpMethod.GET, entity, Forecast.class);
        return responseCurrentWeather;
    }

    public ResponseEntity<Forecast> getCurrentWeatherInStandardUnits(String city) {
        String urlWeather = url + "/data/2.5/weather?q=" + city;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Forecast> entity = new HttpEntity<>(headers);
        ResponseEntity<Forecast> responseCurrentWeather = restTemplate
                .exchange(urlWeather, HttpMethod.GET, entity, Forecast.class);
        return responseCurrentWeather;
    }

    public ResponseEntity<String> getWeatherForecastForFiveDays(String nameCity) {
        String city = url + "/data/2.5/forecast?q=" + nameCity
                + "&units=metric";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Forecast> entity = new HttpEntity<>(headers);
        ResponseEntity<String> responseForecast = restTemplate
                .exchange(city, HttpMethod.GET, entity, String.class);
        return responseForecast;
    }
}
