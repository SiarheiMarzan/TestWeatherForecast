package weather_obl_city;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import model_json.Forecast;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@RunWith(DataProviderRunner.class)
public class WeatherInfoCity {

    @Test
    @UseDataProvider("sumTestData")
    public void temperatureCitiesTest(String city) {
        String URL = "https://api.openweathermap.org/data/2.5/forecast?q=" + city
                + "&units=metric&appid=443625ff5854abe232f09b68419c89a3";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Forecast> response = restTemplate.getForEntity(URL, Forecast.class);
        Forecast infoWeather = response.getBody();


    }

    @DataProvider
    public static Object[] sumTestData() {
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
