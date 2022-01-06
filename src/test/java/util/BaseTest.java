package util;

import client.WeatherClient;
import interceptor.AddQueryParameterIntercept;
import interceptor.LoggingIntercept;
import org.springframework.http.client.*;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static util.DataReader.getTestData;

public class BaseTest {

    protected RestTemplate createRestTemplate() {
        ClientHttpRequestFactory factory =
                new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());
        RestTemplate restTemplate = new RestTemplate(factory);
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }
        interceptors.add(new AddQueryParameterIntercept("appid",getTestData("weather.api.key")));
        interceptors.add(new LoggingIntercept());
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }

    protected WeatherClient weatherClient = new WeatherClient(createRestTemplate(),
            getTestData("weather.base.url"));
}