package util;

import client.WeatherClient;
import org.springframework.web.client.RestTemplate;

public class BaseTest {

    protected RestTemplate restTemplate = new RestTemplate();
    protected WeatherClient weatherClient = new WeatherClient(restTemplate);

}
