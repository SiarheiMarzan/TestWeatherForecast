package test;

import model.Forecast;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import util.BaseTest;

import java.text.DecimalFormat;

public class CompareCelsiusKelvinValues extends BaseTest {

    @Test
    public void temperatureTest() {

        //send get weather request get temperature value in the metric system and check response
        double valueTemperatureCelsium = weatherClient.getDataWeather("Brest,BLR&units=metric");
        ResponseEntity<Forecast> metricResponseForCheck = weatherClient.requestApi("Brest,BLR&units=metric", Forecast.class);
        Assert.assertEquals(metricResponseForCheck.getStatusCode().value(), 200);

        double valueConversionCelsiusToKelvin = valueTemperatureCelsium + 273.15;

        //send get weather request get the temperature value in kelvins and check response
        double valueTemperatureKelvin = weatherClient.getDataWeather("Brest,BLR");
        ResponseEntity<Forecast> responseForCheck = weatherClient.requestApi("Brest,BLR", Forecast.class);
        Assert.assertEquals(responseForCheck.getStatusCode().value(), 200);

        //rounding up the values for an accurate comparison
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String valueCelsium = decimalFormat.format(valueConversionCelsiusToKelvin);
        String valueKelvin = decimalFormat.format(valueTemperatureKelvin);

        Assert.assertEquals(valueCelsium, valueKelvin);

    }
}