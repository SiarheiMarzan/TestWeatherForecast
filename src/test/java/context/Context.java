package context;

import client.WeatherClient;
import interceptor.AddQueryParameterIntercept;
import interceptor.LoggingIntercept;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@PropertySource("classpath:${env:qa}.properties")
@Configuration
public class Context {

    @Value("${weather.base.key}")
    private String key;

    @Value("${weather.base.url}")
    private String url;

    @Bean
    public RestTemplate createRestTemplate() {
        ClientHttpRequestFactory factory =
                new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());
        RestTemplate restTemplate = new RestTemplate(factory);
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }
        interceptors.add(new AddQueryParameterIntercept("appid", key));
        interceptors.add(new LoggingIntercept());
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }

    @Bean
    public WeatherClient weatherClient() {
         WeatherClient weatherClient = new WeatherClient(createRestTemplate(), url);
         return weatherClient;

    }
}
