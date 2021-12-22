package test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.net.URL;

import static util.DataReader.getTestData;

public class ParameterizedTestTemperatureCheck {

    private SoftAssertions softAssertions = new SoftAssertions();

    @ParameterizedTest
    @ValueSource(strings = {"Minsk,BLR",
            "Brest,BLR",
            "Vitebsk,BLR",
            "Mogilev,BLR",
            "Gomel,BLR",
            "Grodno,BLR"})
    public void temperatureCitiesTest(String city) {
        String urlJson = getTestData("weather.base.url") + "/data/2.5/forecast?q=" + city
                + "&units=metric&appid=" + getTestData("weather.api.key");

        JsonNode getJsonForTest = null;
        try {
            getJsonForTest = new ObjectMapper().readTree(new URL(urlJson));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int getSizeList = getJsonForTest.get("list").size();
        double controlTemperature = 35.0;

        for (int i = 0; i <= getSizeList - 1; i++) {
            String dataFromDtTxt = getJsonForTest.get("list").get(i).path("dt_txt").toString();

            if (dataFromDtTxt.contains("15:00:00")) {
                double valueFeelsLike = getJsonForTest.get("list").get(i).get("main").path("feels_like").asDouble();
                softAssertions.assertThat(valueFeelsLike < controlTemperature).isEqualTo(true);
            }
        }
        softAssertions.assertAll();
    }

}
