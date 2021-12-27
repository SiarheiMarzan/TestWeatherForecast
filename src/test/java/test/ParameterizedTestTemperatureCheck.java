package test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.http.ResponseEntity;
import util.BaseTest;

import java.net.URL;

import static util.DataReader.getTestData;

public class ParameterizedTestTemperatureCheck extends BaseTest {

    private SoftAssertions softAssertions = new SoftAssertions();

    @ParameterizedTest
    @ValueSource(strings = {"Minsk,BLR",
            "Brest,BLR",
            "Vitebsk,BLR",
            "Mogilev,BLR",
            "Gomel,BLR",
            "Grodno,BLR"})
    public void temperatureCitiesTest(String nameCity) {
        String city = getTestData("weather.base.url") + "/data/2.5/forecast?q=" + nameCity
                + "&units=metric&appid=" + getTestData("weather.api.key");

        //send get weather request in json for city
        JsonNode getForecast = weatherClient.getDataTreeJson(city);

        //get the size of the checked blocks in the list
        int getSizeList = getForecast.get("list").size();
        double controlTemperature = 35.0;

        //check each block of the list for a condition and perform a check
        for (int i = 0; i <= getSizeList - 1; i++) {
            String dataFromDtTxt = getForecast.get("list").get(i).path("dt_txt").toString();

            if (dataFromDtTxt.contains("15:00:00")) {
                double valueFeelsLike = getForecast.get("list").get(i).get("main").path("feels_like").asDouble();
                softAssertions.assertThat(valueFeelsLike < controlTemperature).isEqualTo(true);
            }
        }
        softAssertions.assertAll();
    }
}
