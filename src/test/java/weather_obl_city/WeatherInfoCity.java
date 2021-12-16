package weather_obl_city;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.net.URL;

@RunWith(DataProviderRunner.class)
public class WeatherInfoCity {

    private SoftAssertions softAssertions = new SoftAssertions();

    @Test
    @UseDataProvider("sumTestData")
    public void temperatureCitiesTest(String city) {
        String urlJson = "https://api.openweathermap.org/data/2.5/forecast?q=" + city
                + "&units=metric&appid=443625ff5854abe232f09b68419c89a3";

        JsonNode getJsonForTest = null;
        try {
            getJsonForTest = new ObjectMapper().readTree(new URL(urlJson));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int sizeValueList = getJsonForTest.get("list").size();
        double controlTemperature = 35.0;

        for (int i = 0; i <= sizeValueList - 1; i++) {
            String dataFromDtTxt = getJsonForTest.get("list").get(i).path("dt_txt").toString();

            if (dataFromDtTxt.contains("15:00:00")) {
                double valueFeelsLike = getJsonForTest.get("list").get(i).get("main").path("feels_like").asDouble();
                softAssertions.assertThat(valueFeelsLike < controlTemperature).isEqualTo(true);
            }
        }
        softAssertions.assertAll();
    }

    @DataProvider
    public static Object[] dataNameCityForTest() {
        return new Object[]{
                "Minsk,BLR",
                "Brest,BLR",
                "Vitebsk,BLR",
                "Mogilev,BLR",
                "Gomel,BLR",
                "Grodno,BLR"
        };
    }
}
