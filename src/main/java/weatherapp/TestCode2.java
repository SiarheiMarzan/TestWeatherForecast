package weatherapp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestCode2 {

    public static final String WEATHER_URL_C = "https://api.openweathermap.org/data/2.5/weather?q=Brest,BLR&units=metric&appid=443625ff5854abe232f09b68419c89a3";
    public static final String WEATHER_URL_K = "https://api.openweathermap.org/data/2.5/weather?q=Brest,BLR&appid=443625ff5854abe232f09b68419c89a3";

    public static void main(String[] args) throws IOException {

        double celsium = Celcium() + 273.15;
        double kelvin = Kelvin();

        String celsiumFormat = String.format("%.2f", celsium);
        String kelvinFormat = String.format("%.2f", kelvin);

        Assert.assertEquals(celsiumFormat, kelvinFormat);

    }

    public static double Celcium() throws IOException {
        URL url = new URL(WEATHER_URL_C);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new IllegalArgumentException();
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        System.out.println(response);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode mainNode;
        mainNode = objectMapper.readTree(String.valueOf(response)).get("main");
        double temp = mainNode.get("temp").asDouble();
        return temp;
    }

    public static double Kelvin() throws IOException {
        URL url = new URL(WEATHER_URL_K);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new IllegalArgumentException();
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode mainNode;
        mainNode = objectMapper.readTree(String.valueOf(response)).get("main");
        double temp = mainNode.get("temp").asDouble();
        return temp;
    }

}



