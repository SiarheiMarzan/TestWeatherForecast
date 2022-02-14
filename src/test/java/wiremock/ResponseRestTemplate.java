package wiremock;

import org.junit.Assert;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import wiremock.com.fasterxml.jackson.core.JsonProcessingException;
import wiremock.com.fasterxml.jackson.databind.JsonNode;
import wiremock.com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ResponseRestTemplate {
    static RestTemplate restTemplate = new RestTemplate();
    static String fooResourceUrl
            = "http://localhost:8081/";

    public static void main(String[] args) throws JsonProcessingException {
        checkResponeJson();
        checkResponeXml();
        checkResponejpg();
        checkResponseNotNullValue();
        checkValueBody();
        checkWithHeaders();

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

    public static void checkWithHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/octet-stream");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate
                .exchange(fooResourceUrl + "binary", HttpMethod.GET, entity, String.class);
        Assert.assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
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
                restTemplate.getForEntity(fooResourceUrl + "json", String.class);
        Assert.assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

}
