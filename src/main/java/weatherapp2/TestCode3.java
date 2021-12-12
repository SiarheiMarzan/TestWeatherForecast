package weatherapp2;

import model_json.Root;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;

public class TestCode3 {

    static final String URL_CELCIUM = "https://api.openweathermap.org/data/2.5/weather?q=Brest,BLR&units=metric&appid=443625ff5854abe232f09b68419c89a3";
    static final String URL_KELVIN = "https://api.openweathermap.org/data/2.5/weather?q=Brest,BLR&appid=443625ff5854abe232f09b68419c89a3";
    static final String URL_KELVIN1 = "https://api.openweathermap.org/data/2.5/weather?q=Brest" +
            ",BLR&mode=xml&appid=443625ff5854abe232f09b68419c89a3";

    static final String APPLICATION_J = "APPLICATION_JSON";
    static final String APPLICATION_X = "APPLICATION_XML";

    public static void main(String[] args) {

//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<Root> entity = new HttpEntity<Root>(headers);
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<Root> response = restTemplate.exchange(URL_KELVIN, HttpMethod.GET, entity, Root.class);
//        Root list = response.getBody();
//        HttpStatus statusCode = response.getStatusCode();
//        System.out.println("Response Satus Code: " + statusCode);

        SimpleDateFormat format = new SimpleDateFormat();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Root> response = restTemplate.getForEntity(URL_CELCIUM, Root.class);
        Root infoWeather = response.getBody();
        double coor1 = infoWeather.getCoord().getLat();
        double coor2 = infoWeather.getCoord().getLon();
        double sunRise = infoWeather.getSys().getSunrise();
        double sunSet = infoWeather.getSys().getSunset();
        double wind = infoWeather.getWind().getSpeed();
        double pressure = infoWeather.getMain().getPressure();
        double tempMax = infoWeather.getMain().getTemp_max();
        double tempMin = infoWeather.getMain().getTemp_min();
        double feels_like = infoWeather.getMain().getFeels_like();
        double[] testAssert = {coor1, coor2, sunRise, sunSet, wind, pressure, tempMax, tempMin, feels_like};
        for(double x : testAssert) {
            System.out.print(x + " ");
        }
//        Assert.assertEquals(coor1, en);

    }
}
