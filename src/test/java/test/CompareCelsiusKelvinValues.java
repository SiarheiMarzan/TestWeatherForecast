package test;

import model.Forecast;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import util.BaseTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CompareCelsiusKelvinValues extends BaseTest {

    @Test
    public void temperatureTest() {
        //send get weather request get temperature value in the metric system and check response
        ResponseEntity<Forecast> responseCurrentWeatherMetric = weatherClient.getCurrentWeather("Brest,BLR", "metric");
        Assert.assertEquals(responseCurrentWeatherMetric.getStatusCode().value(), 200);

        //send get weather request get the temperature value in kelvins and check response
        ResponseEntity<Forecast> responseCurrentWeatherStandart = weatherClient.getCurrentWeather("Brest,BLR");
        Assert.assertEquals(responseCurrentWeatherStandart.getStatusCode().value(), 200);

        //Cheking the received value in Celsium with Kelvin
        assertThat(convertKelvinToCelsium(), equalTo(responseCurrentWeatherStandart.getBody().getMain().getTemp()));
    }

    public double convertKelvinToCelsium() {
        return weatherClient.getCurrentWeather("Brest,BLR", "metric")
                .getBody().getMain().getTemp() + 273.15;
    }
}