package client;

import model.Forecast;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class WeatherClient {

    private RestTemplate restTemplate;

    public WeatherClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public ResponseEntity<Forecast> requestApi(String url) {
        return restTemplate.getForEntity(url, Forecast.class);
    }

    public static double getDataWeather(String url, MediaType content) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[]{content}));
        headers.setContentType(content);
        HttpEntity<Forecast> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Forecast> response = restTemplate.exchange(url, HttpMethod.GET, entity, Forecast.class);
        return response.getBody().getMain().getTemp();
    }
}
