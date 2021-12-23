package test;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;

import static client.WeatherClient.getDataWeather;

public class CompareCelsiusKelvinValues {

    @Test
    public void temperatureTest() {

        //get the temperature value in the metric system
        double temperatureCelsium = getDataWeather("Brest,BLR&units=metric");

        double valueConversionCelsiusToKelvin = temperatureCelsium + 273.15;

        //get the temperature value in kelvins
        double temperatureKelvin = getDataWeather("Brest,BLR");

        //rounding up the values for an accurate comparison
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formatCelsium = decimalFormat.format(valueConversionCelsiusToKelvin);
        String formatKelvin = decimalFormat.format(temperatureKelvin);

        Assert.assertEquals(formatCelsium, formatKelvin);

    }
}