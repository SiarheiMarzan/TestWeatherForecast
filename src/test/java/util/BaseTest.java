package util;

import client.WeatherClient;
import context.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(Context.class)
public class BaseTest {

    @Autowired
    protected WeatherClient weatherClient;

}