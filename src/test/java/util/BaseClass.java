package util;

import client.WeatherClient;
import org.springframework.web.client.RestTemplate;

public class BaseClass {

    protected RestTemplate restTemplate = new RestTemplate();
    protected WeatherClient weatherClient = new WeatherClient(restTemplate);

}
