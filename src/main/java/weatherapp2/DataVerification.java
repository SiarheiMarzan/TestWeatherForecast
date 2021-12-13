package weatherapp2;

import model_json.Forecast;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DataVerification {

    private Document document;

    static final String URL_CELCIUM = "https://api.openweathermap.org/data/2.5/weather?q=Brest,BLR&units=metric&appid=443625ff5854abe232f09b68419c89a3";
    static final String URL_KELVIN = "https://api.openweathermap.org/data/2.5/weather?q=Brest,BLR&appid=443625ff5854abe232f09b68419c89a3";
    static final String URL_KELVIN_XML = "https://api.openweathermap.org/data/2.5/weather?q=Brest" +
            ",BLR&mode=xml&appid=443625ff5854abe232f09b68419c89a3";

    public static void main(String[] args){

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Forecast> response = restTemplate.getForEntity(URL_KELVIN, Forecast.class);
        Assert.assertEquals(response.getStatusCode().value(), 200);
        Forecast infoWeather = response.getBody();

        String coordinateLatCityJsonConv = String.valueOf(infoWeather.getCoord().getLat());
        String coordinateLonCityJsonConv = String.valueOf(infoWeather.getCoord().getLon());
        LocalDateTime dtRise = Instant.ofEpochSecond(infoWeather.getSys().getSunrise()).atZone(ZoneId.of("UTC")).toLocalDateTime();
        String riseSunJsonConv = String.valueOf(dtRise);
        LocalDateTime dtSet = Instant.ofEpochSecond(infoWeather.getSys().getSunset()).atZone(ZoneId.of("UTC")).toLocalDateTime();
        String setSunJsonConv = String.valueOf(dtSet);
        double speedWindJson = infoWeather.getWind().getSpeed();
        String pressureAirJsonConv = String.valueOf(infoWeather.getMain().getPressure());
        String tempAirMaxConv = String.valueOf(infoWeather.getMain().getTemp_max());
        String tempAirMinConv = String.valueOf(infoWeather.getMain().getTemp_min());
        String feelsLikeConv = String.valueOf(infoWeather.getMain().getFeels_like());

        org.jsoup.nodes.Document dataFromOpenweathermap = null;
        try {
            dataFromOpenweathermap = Jsoup.connect(URL_KELVIN_XML).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements coordinates = dataFromOpenweathermap.select("coord");
        Elements sunTimeLive = dataFromOpenweathermap.select("sun");
        Elements speedWind = dataFromOpenweathermap.select("speed");
        Elements pressureAir = dataFromOpenweathermap.select("pressure");
        Elements temperatureAir = dataFromOpenweathermap.select("temperature");
        Elements feelsLikeCity = dataFromOpenweathermap.select("feels_like");
        var coordinateLatCityXML = coordinates.attr("lat");
        var coordinateLonCityXML = coordinates.attr("lon");
        var riseSunXML = sunTimeLive.attr("rise");
        var setSunXML = sunTimeLive.attr("set");
        var speedWindXML = speedWind.attr("value");
        double speedWindXMLConv = Double.parseDouble(speedWindXML.strip());
        var pressureAirXML = pressureAir.attr("value");
        var tempAirMinXML = temperatureAir.attr("min");
        var tempAirMaxXML = temperatureAir.attr("max");
        var feelsLikeXML = feelsLikeCity.attr("value");

        Assert.assertEquals(coordinateLatCityJsonConv, coordinateLatCityXML);
        Assert.assertEquals(coordinateLonCityJsonConv, coordinateLonCityXML);
        Assert.assertEquals(riseSunJsonConv, riseSunXML);
        Assert.assertEquals(setSunJsonConv, setSunXML);
        Assert.assertEquals(speedWindJson, speedWindXMLConv, 0.0);
        Assert.assertEquals(pressureAirJsonConv, pressureAirXML);
        Assert.assertEquals(tempAirMaxConv, tempAirMaxXML);
        Assert.assertEquals(tempAirMinConv, tempAirMinXML);
        Assert.assertEquals(feelsLikeConv, feelsLikeXML);

    }

}
