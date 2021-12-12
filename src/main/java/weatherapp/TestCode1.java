package weatherapp;

import model_json.Root;
import org.junit.Assert;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.util.Arrays;


public class TestCode1 {

    static final String URL_CELCIUM = "https://api.openweathermap.org/data/2.5/weather?q=Brest" +
            ",BLR&units=metric&appid=443625ff5854abe232f09b68419c89a3";

    static final String URL_KELVIN = "https://api.openweathermap.org/data/2.5/weather?q=Brest" +
            ",BLR&appid=443625ff5854abe232f09b68419c89a3";

    public static void main(String[] args) {

        double celsiumChangeToKelvin = getDataWeather(URL_CELCIUM, MediaType.APPLICATION_JSON) + 273.15;
        double kelvin = getDataWeather(URL_KELVIN, MediaType.APPLICATION_JSON);

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formatCelsium = decimalFormat.format(celsiumChangeToKelvin);
        String formatKelvin = decimalFormat.format(kelvin);

        Assert.assertEquals(formatCelsium, formatKelvin);

    }

    public static double getDataWeather(String url, MediaType content) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[]{content}));
        headers.setContentType(content);
        HttpEntity<Root> entity = new HttpEntity<Root>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Root> response = restTemplate.exchange(url, HttpMethod.GET, entity, Root.class);
        Root list = response.getBody();
        HttpStatus statusCode = response.getStatusCode();
        Assert.assertEquals(HttpStatus.OK, statusCode);
        if (statusCode == HttpStatus.OK) {
            if (list != null) {
                return list.getMain().getTemp();
            }
        }
        return list.getMain().getTemp();
    }

}