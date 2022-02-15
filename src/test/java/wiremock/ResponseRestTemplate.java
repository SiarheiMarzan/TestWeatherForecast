package wiremock;

import org.junit.Assert;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import wiremock.com.fasterxml.jackson.core.JsonProcessingException;
import wiremock.com.fasterxml.jackson.databind.JsonNode;
import wiremock.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class ResponseRestTemplate{

    static RestTemplate restTemplate = new RestTemplate();
    static String fooResourceUrl
            = "http://localhost:8081/";

    public static void main(String[] args) throws IOException {
//        checkResponeJson();
//        checkResponseNotNullValue();
//        checkValueBody();
//        retrieveHeaders();
//        getAllowedOperations();
//        submitFormData();

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

    public static void getAllowedOperations() {
        Set<HttpMethod> optionsForAllow = restTemplate.optionsForAllow(fooResourceUrl + "json");
        HttpMethod[] supportedMethods
                = {HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE};
        Assert.assertTrue(optionsForAllow.containsAll(Arrays.asList(supportedMethods)));
    }


    public static void submitFormData() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("id", "3");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(fooResourceUrl + "json", request, String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }

    public static void retrieveHeaders() {
        HttpHeaders httpHeaders = restTemplate.headForHeaders(fooResourceUrl);
        assertTrue(httpHeaders.getContentType().includes(MediaType.valueOf("text/plain")));
    }
}
