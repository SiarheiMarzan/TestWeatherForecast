package util;

import client.WeatherClient;
import interceptor.AddQueryParameterIntersept;
import interceptor.LoggingIntercept;
import org.springframework.http.client.*;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static util.DataReader.getTestData;

public class BaseTest {

    protected RestTemplate createRestTemplate() {

        /* We should use BufferingClientHttpRequestFactory: it buffers stream content into memory.
        This way, it can be read twice: once by our interceptor, and a second time by our client application */
        ClientHttpRequestFactory factory =
                new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());

        RestTemplate restTemplate = new RestTemplate(factory);
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }
        interceptors.add(new AddQueryParameterIntersept());
        interceptors.add(new LoggingIntercept());
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }

    protected WeatherClient weatherClient = new WeatherClient(createRestTemplate(),
            getTestData("weather.base.url"));
}