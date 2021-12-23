package test;

import model.Forecast;
import org.jsoup.Jsoup;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import util.BaseClass;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DataCompareJsonXml extends BaseClass {

    @Test
    public void weatherDataTest() {

        //send get weather request in json
        ResponseEntity<Forecast> responseWeatherJson = weatherClient
                .requestApi("Brest,BLR");
        Assert.assertEquals(responseWeatherJson.getStatusCode().value(), 200);

        //send get weather request in xml
        ResponseEntity<String> responseWeatherXml = weatherClient.requestApiXml("Brest,BLR");
        Assert.assertEquals(responseWeatherXml.getStatusCode().value(), 200);

        validateCompareResponces(responseWeatherJson, responseWeatherXml);
    }

    private void validateCompareResponces(ResponseEntity<Forecast> responseWeatherJson,
                                          ResponseEntity<String> responseWeatherXml) {

        Forecast getInfoWeather = responseWeatherJson.getBody();
        org.jsoup.nodes.Document dataFromOpenweathermap = Jsoup.parse(responseWeatherXml.getBody());
        LocalDateTime dtRise = Instant.ofEpochSecond(getInfoWeather.getSys().getSunrise()).atZone(ZoneId.of("UTC"))
                .toLocalDateTime();
        LocalDateTime dtSet = Instant.ofEpochSecond(getInfoWeather.getSys().getSunset()).atZone(ZoneId.of("UTC"))
                .toLocalDateTime();

        String coordinateLatitudeFromJson = String.valueOf(getInfoWeather.getCoord().getLat());
        String coordinateLatitudeFromXml = dataFromOpenweathermap.select("coord").attr("lat");
        Assert.assertEquals(coordinateLatitudeFromJson, coordinateLatitudeFromXml);

        String coordinateLongitudeFromJson = String.valueOf(getInfoWeather.getCoord().getLon());
        String coordinateLongitudeFromXml = dataFromOpenweathermap.select("coord").attr("lon");
        Assert.assertEquals(coordinateLongitudeFromJson, coordinateLongitudeFromXml);

        String timeRiseSunFromJson = String.valueOf(dtRise);
        String timeRiseSunFromXml = dataFromOpenweathermap.select("sun").attr("rise");
        Assert.assertEquals(timeRiseSunFromJson, timeRiseSunFromXml);

        String timeSetSunFromJson = String.valueOf(dtSet);
        String timeSetSunFromXml = dataFromOpenweathermap.select("sun").attr("set");
        Assert.assertEquals(timeSetSunFromJson, timeSetSunFromXml);

        double valueSpeedWindFromJson = getInfoWeather.getWind().getSpeed();
        double valueSpeedWindFromXML= Double.parseDouble(dataFromOpenweathermap.select("speed").attr("value"));
        Assert.assertEquals(valueSpeedWindFromJson, valueSpeedWindFromXML, 0.00);

        String valuePressureAirFromJson = String.valueOf(getInfoWeather.getMain().getPressure());
        String valuePressureAirFromXML = dataFromOpenweathermap.select("pressure").attr("value");
        Assert.assertEquals(valuePressureAirFromJson, valuePressureAirFromXML);

        String maximumAirTemperatureFromJson = String.valueOf(getInfoWeather.getMain().getTemp_max());
        String maximumAirTemperatureFromXml = dataFromOpenweathermap.select("temperature").attr("max");
        Assert.assertEquals(maximumAirTemperatureFromJson, maximumAirTemperatureFromXml);

        String minimumAirTemperatureFromJson = String.valueOf(getInfoWeather.getMain().getTemp_min());
        String minimumAirTemperatureFromXml = dataFromOpenweathermap.select("temperature").attr("min");
        Assert.assertEquals(minimumAirTemperatureFromJson, minimumAirTemperatureFromXml);

        String valueFeelsLikeFromJson = String.valueOf(getInfoWeather.getMain().getFeels_like());
        String valueFeelsLikeFromXML = dataFromOpenweathermap.select("feels_like").attr("value");
        Assert.assertEquals(valueFeelsLikeFromJson, valueFeelsLikeFromXML);

    }
}
