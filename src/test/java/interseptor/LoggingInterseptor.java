package interseptor;

import org.apache.logging.log4j.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class LoggingInterseptor implements ClientHttpRequestInterceptor {

    static Logger LOGGER = (Logger) LoggerFactory.getLogger(LoggingInterseptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] reqBody, ClientHttpRequestExecution execution) throws IOException {
        LOGGER.debug("Request body: {}", new String(reqBody, StandardCharsets.UTF_8));
        ClientHttpResponse response = execution.execute(request, reqBody);
        InputStreamReader isr = new InputStreamReader(
                response.getBody(), StandardCharsets.UTF_8);
        String body = new BufferedReader(isr).lines()
                .collect(Collectors.joining("\n"));
        LOGGER.debug("Response body: {}", body);
        return response;
    }
}