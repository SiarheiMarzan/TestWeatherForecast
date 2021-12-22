package test;

import client.WeatherClient;
import model.Forecast;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static util.DataReader.getTestData;

public class DataCompareJsonXml {

    @Test
    public void weatherDataTest() {

        RestTemplate restTemplate = new RestTemplate();

        WeatherClient weatherClient = new WeatherClient(restTemplate);

        ResponseEntity<Forecast> responseWeatherWebsite = weatherClient
                .requestApi(getTestData("weather.base.url") +
                        "/data/2.5/weather?q=Brest,BLR&appid=" + getTestData("weather.api.key"));

        Assert.assertEquals(responseWeatherWebsite.getStatusCode().value(), 200);

        Forecast getInfoWeather = responseWeatherWebsite.getBody();
        String coordinateLatCityJsonConv = String.valueOf(getInfoWeather.getCoord().getLat());
        String coordinateLonCityJsonConv = String.valueOf(getInfoWeather.getCoord().getLon());
        LocalDateTime dtRise = Instant.ofEpochSecond(getInfoWeather.getSys().getSunrise()).atZone(ZoneId.of("UTC"))
                .toLocalDateTime();
        String riseSunJsonConv = String.valueOf(dtRise);
        LocalDateTime dtSet = Instant.ofEpochSecond(getInfoWeather.getSys().getSunset()).atZone(ZoneId.of("UTC"))
                .toLocalDateTime();
        String setSunJsonConv = String.valueOf(dtSet);
        double speedWindJson = getInfoWeather.getWind().getSpeed();
        String pressureAirJsonConv = String.valueOf(getInfoWeather.getMain().getPressure());
        String tempAirMaxConv = String.valueOf(getInfoWeather.getMain().getTemp_max());
        String tempAirMinConv = String.valueOf(getInfoWeather.getMain().getTemp_min());
        String feelsLikeConv = String.valueOf(getInfoWeather.getMain().getFeels_like());

        org.jsoup.nodes.Document dataFromOpenweathermap = null;
        try {
            dataFromOpenweathermap = Jsoup.connect(getTestData("weather.base.url")
                    + "/data/2.5/weather?q=Brest" +
                    ",BLR&mode=xml&appid=" + getTestData("weather.api.key")).get();
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
        double speedWindXMLConv = Double.parseDouble(speedWindXML);
        var pressureAirXML = pressureAir.attr("value");
        var tempAirMinXML = temperatureAir.attr("min");
        var tempAirMaxXML = temperatureAir.attr("max");
        var feelsLikeXML = feelsLikeCity.attr("value");

        Assert.assertEquals(coordinateLatCityJsonConv, coordinateLatCityXML);
        Assert.assertEquals(coordinateLonCityJsonConv, coordinateLonCityXML);
        Assert.assertEquals(riseSunJsonConv, riseSunXML);
        Assert.assertEquals(setSunJsonConv, setSunXML);
        Assert.assertEquals(speedWindJson, speedWindXMLConv, 0.00);
        Assert.assertEquals(pressureAirJsonConv, pressureAirXML);
        Assert.assertEquals(tempAirMaxConv, tempAirMaxXML);
        Assert.assertEquals(tempAirMinConv, tempAirMinXML);
        Assert.assertEquals(feelsLikeConv, feelsLikeXML);

    }

}
