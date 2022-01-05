package util;

import client.WeatherClient;
import interseptor.AddQueryParameterInterseptor;
import interseptor.LoggingInterseptor;
import org.springframework.http.client.*;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static util.DataReader.getTestData;

public class BaseTest {

    protected RestTemplate restTemplate() {
        ClientHttpRequestFactory factory =
                new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());
        RestTemplate restTemplate = new RestTemplate(factory);
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }
        interceptors.add(new AddQueryParameterInterseptor());
        interceptors.add(new LoggingInterseptor());
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }

    protected WeatherClient weatherClient = new WeatherClient(restTemplate(),
            getTestData("weather.base.url"));
}