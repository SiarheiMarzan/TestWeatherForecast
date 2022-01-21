package spring;

import interceptor.AddQueryParameterIntercept;
import interceptor.LoggingIntercept;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
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

import static util.TestDataReader.getTestData;

@Configuration
@ComponentScan(basePackages = {"client"})
@PropertySource("classpath:${env:qa}.properties")
public class RestTemplateGenerator {

    @Bean
    public RestTemplate createRestTemplate() {
        ClientHttpRequestFactory factory =
                new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());
        RestTemplate restTemplate = new RestTemplate(factory);
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }
        interceptors.add(new AddQueryParameterIntercept("appid", getTestData("weather.base.key")));
        interceptors.add(new LoggingIntercept());
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }
}
