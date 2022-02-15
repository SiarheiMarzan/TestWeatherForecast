package wiremock;

import org.junit.Assert;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import wiremock.com.fasterxml.jackson.core.JsonProcessingException;
import wiremock.com.fasterxml.jackson.databind.JsonNode;
import wiremock.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ResponseRestTemplate{

    static RestTemplate restTemplate = new RestTemplate();
    static String fooResourceUrl
            = "http://localhost:8081/";

    public static void main(String[] args) throws IOException {
//        checkResponeJson();
//        checkResponeXml();
//        checkResponejpg();
//        checkResponseNotNullValue();
//        checkValueBody();
//        checkWithHeaders();
        retrieveHeaders();

    }

    public static void checkResponseNotNullValue() {
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl + "json", String.class, HttpMethod.GET);
        Assert.assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = null;
        try {
            root = mapper.readTree(response.getBody());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        JsonNode name = root.findValue("balance");
        System.out.println(name);
        assertThat(name.asText(), notNullValue());
    }

    public static void checkValueBody() throws JsonProcessingException {
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl + "json", String.class, HttpMethod.GET);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode name = root.findValue("password");
        System.out.println(name);
        assertThat(name.asText(), equalToIgnoringCase("12345"));
    }

    public static void checkResponeJson() {
        ResponseEntity<String> response =
                restTemplate.getForEntity(fooResourceUrl + "json", String.class);
        Assert.assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    public static void checkResponeXml() {
        ResponseEntity<String> response =
                restTemplate.getForEntity(fooResourceUrl + "xml", String.class);
        Assert.assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }


    public static void checkResponejpg() {
        ResponseEntity<String> response =
                restTemplate.getForEntity(fooResourceUrl + "jpg", String.class);
        Assert.assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    public static void retrieveHeaders() {
        HttpHeaders httpHeaders = restTemplate.headForHeaders(fooResourceUrl);
        Assert.assertTrue(httpHeaders.getContentType().includes(MediaType.valueOf("text/plain")));
    }
}
