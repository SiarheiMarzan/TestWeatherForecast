package interceptor;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

public class AddQueryParameterIntersept implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        URI uri = UriComponentsBuilder.fromHttpRequest(request)
                .queryParam("appid", "443625ff5854abe232f09b68419c89a3").build().toUri();

        HttpRequest modifiedRequest = new HttpRequestWrapper(request) {
            @Override
            public URI getURI() {
                return uri;
            }
        };
        return execution.execute(modifiedRequest, body);
    }
}
