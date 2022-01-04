package util;

import client.WeatherClient;
import org.springframework.web.client.RestTemplate;

import static util.DataReader.getTestData;

public class BaseTest {
    protected RestTemplate restTemplate = new RestTemplate();
    protected WeatherClient weatherClient = new WeatherClient(restTemplate,
            getTestData("weather.base.url"), getTestData("weather.api.key"));


//    List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
//        if(CollectionUtils.isEmpty(interceptors)) {
//        interceptors = new ArrayList<>();
//    }
//        interceptors.add(new RestTemplateInterseptor());
//        restTemplate.setInterceptors(interceptors);
//        return restTemplate;
}
