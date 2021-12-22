package test;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;

import java.text.DecimalFormat;

import static client.WeatherClient.getDataWeather;
import static util.DataReader.getTestData;

public class CompareCelsiusKelvinValues {

    @Test
    public void temperatureTest() {

        double temperatureCelsium = getDataWeather(getTestData("weather.base.url")
                        + "/data/2.5/weather?q=Brest" +
                        ",BLR&units=metric&appid=" + getTestData("weather.api.key")
                , MediaType.APPLICATION_JSON);

        double temperatureKelvin = getDataWeather(getTestData("weather.base.url")
                        + "/data/2.5/weather?q=Brest" +
                        ",BLR&appid=" + getTestData("weather.api.key")
                , MediaType.APPLICATION_JSON);

        double valueConversionCelsiusToKelvin = temperatureCelsium + 273.15;

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formatCelsium = decimalFormat.format(valueConversionCelsiusToKelvin);
        String formatKelvin = decimalFormat.format(temperatureKelvin);

        Assert.assertEquals(formatCelsium, formatKelvin);

    }
}