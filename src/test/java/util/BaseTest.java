package util;

import client.WeatherClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import spring.RestTemplateGenerator;

@SpringJUnitConfig(RestTemplateGenerator.class)
public class BaseTest {

    @Autowired
    protected WeatherClient weatherClient;

}