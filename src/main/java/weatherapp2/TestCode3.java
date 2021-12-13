package weatherapp2;

import model_json.Forecast;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TestCode3 {

    private Document document;

    static final String URL_CELCIUM = "https://api.openweathermap.org/data/2.5/weather?q=Brest,BLR&units=metric&appid=443625ff5854abe232f09b68419c89a3";
    static final String URL_KELVIN = "https://api.openweathermap.org/data/2.5/weather?q=Brest,BLR&appid=443625ff5854abe232f09b68419c89a3";
    static final String URL_KELVIN_XML = "https://api.openweathermap.org/data/2.5/weather?q=Brest" +
            ",BLR&mode=xml&appid=443625ff5854abe232f09b68419c89a3";

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Forecast> response = restTemplate.getForEntity(URL_KELVIN, Forecast.class);
        Forecast infoWeather = response.getBody();
        var coordinateLatCityJson = infoWeather.getCoord().getLat();
        String coordinateLatCityJsonConv = String.valueOf(coordinateLatCityJson);
        var coordinateLonCityJson = infoWeather.getCoord().getLon();
        String coordinateLonCityJsonConv = String.valueOf(coordinateLonCityJson);
        var riseSunJson = infoWeather.getSys().getSunrise();
        LocalDateTime dtRise = Instant.ofEpochSecond(riseSunJson).atZone(ZoneId.of("UTC")).toLocalDateTime();
        String riseSunJsonConv = String.valueOf(dtRise);
        var setSunJson = infoWeather.getSys().getSunset();
        LocalDateTime dtSet = Instant.ofEpochSecond(setSunJson).atZone(ZoneId.of("UTC")).toLocalDateTime();
        String setSunJsonConv = String.valueOf(dtSet);
        var speedWindJson = infoWeather.getWind().getSpeed();
        var pressureAirJson = infoWeather.getMain().getPressure();
        String pressureAirJsonConv = String.valueOf(pressureAirJson);
        var tempAirMaxJson = infoWeather.getMain().getTemp_max();
        String tempAirMaxConv = String.valueOf(tempAirMaxJson);
        var tempAirMinJson = infoWeather.getMain().getTemp_min();
        String tempAirMinConv = String.valueOf(tempAirMinJson);
        var feelsLikeJson = infoWeather.getMain().getFeels_like();
        String feelsLikeConv = String.valueOf(feelsLikeJson);

        org.jsoup.nodes.Document dataFromOpenweathermap = Jsoup.connect(URL_KELVIN_XML).get();
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
