package spring;

import interceptor.AddQueryParameterIntercept;
import interceptor.LoggingIntercept;
import org.springframework.beans.factory.annotation.Autowired;
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

@Configuration
@ComponentScan(basePackages = {"client", "interceptor"})
@PropertySource("classpath:${env:qa}.properties")
public class RestTemplateGenerator {

    @Autowired
    private AddQueryParameterIntercept addQueryParameterIntercept;

    @Autowired
    private LoggingIntercept loggingIntercept;

    @Bean
    public RestTemplate createRestTemplate() {
        ClientHttpRequestFactory factory =
                new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());
        RestTemplate restTemplate = new RestTemplate(factory);
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }
        interceptors.add(addQueryParameterIntercept);
        interceptors.add(loggingIntercept);
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }
}
