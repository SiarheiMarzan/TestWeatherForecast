package util;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RestClientConfig {

//    @Bean
//    public RestTemplate restTemplate() {
//        RestTemplate restTemplate = new RestTemplate();
//
//        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
//        if(CollectionUtils.isEmpty(interceptors)) {
//            interceptors = new ArrayList<>();
//        }
//        interceptors.add(new RestTemplateHeaderModifierInterseptor());
//        restTemplate.setInterceptors(interceptors);
//        return restTemplate;
//    }

    @Bean
    RestOperations rest(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.basicAuthentication("key", "value").build();
    }
}
