package test;

import model.Forecast;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import util.BaseTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ComparisonCurrentTemperatureWeather extends BaseTest {

    @Test
    public void compareTemperatureDifferentMetrics() {
        //send get weather request get temperature value in the metric system and check response
        ResponseEntity<Forecast> responseCurrentWeatherInMetricUnits = weatherClient.
                getCurrentWeatherInMetricUnit("Brest,BLR", "metric");
        Assert.assertEquals(responseCurrentWeatherInMetricUnits.getStatusCode().value(), 200);

        //send get weather request get the temperature value in kelvins and check response
        ResponseEntity<Forecast> responseCurrentWeatherInStandartUnits = weatherClient
                .getCurrentWeatherInStandardUnits("Brest,BLR");
        Assert.assertEquals(responseCurrentWeatherInStandartUnits.getStatusCode().value(), 200);

        //Cheking the received value in Celsium with Kelvin
        assertThat(getTemperatureInKelvin(), equalTo(responseCurrentWeatherInStandartUnits.getBody()
                .getMain().getTemp()));
    }

    private double getTemperatureInKelvin() {
        return weatherClient.getCurrentWeatherInMetricUnit("Brest,BLR", "metric")
                .getBody().getMain().getTemp() + 273.15;
    }
}