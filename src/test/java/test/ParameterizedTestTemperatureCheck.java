package test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.http.ResponseEntity;
import util.BaseTest;

public class ParameterizedTestTemperatureCheck extends BaseTest {

    private SoftAssertions softAssertions = new SoftAssertions();

    @ParameterizedTest
    @ValueSource(strings = {"Minsk,BLR",
            "Brest,BLR",
            "Vitebsk,BLR",
            "Mogilev,BLR",
            "Gomel,BLR",
            "Grodno,BLR"})
    public void temperatureCitiesTest(String nameCity) throws JsonProcessingException {
        double controlTemperature = 35.0;

        ResponseEntity<String> getRequestForecast = weatherClient.getForecast(nameCity);
        Assert.assertEquals(getRequestForecast.getStatusCode().value(), 200);

        //parsing request forecast
        JsonNode getForecast = new ObjectMapper().readTree(getRequestForecast.getBody());

        //get the size of the checked blocks in the list
        int getSizeList = getForecast.get("list").size();
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
