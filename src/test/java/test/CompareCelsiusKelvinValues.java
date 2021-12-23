package test;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import util.BaseClass;

import java.text.DecimalFormat;

public class CompareCelsiusKelvinValues extends BaseClass {

    @Test
    public void temperatureTest() {

        //get the temperature value in the metric system
        double temperatureCelsium = weatherClient.getDataWeather("Brest,BLR&units=metric");

        double valueConversionCelsiusToKelvin = temperatureCelsium + 273.15;

        //get the temperature value in kelvins
        double temperatureKelvin = weatherClient.getDataWeather("Brest,BLR");

        //rounding up the values for an accurate comparison
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formatCelsium = decimalFormat.format(valueConversionCelsiusToKelvin);
        String formatKelvin = decimalFormat.format(temperatureKelvin);

        Assert.assertEquals(formatCelsium, formatKelvin);

    }
}