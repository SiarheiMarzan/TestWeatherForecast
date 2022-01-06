package interceptor;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

public class AddQueryParameterIntercept implements ClientHttpRequestInterceptor {

    private String appId;
    private String apiKey;

    public AddQueryParameterIntercept(String appId, String apiKey) {
        this.appId = appId;
        this.apiKey = apiKey;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body
            , ClientHttpRequestExecution execution) throws IOException {
        URI uri = UriComponentsBuilder.fromHttpRequest(request)
                .queryParam(appId, apiKey).build().toUri();
        HttpRequest modifiedRequest = new HttpRequestWrapper(request) {
            @Override
            public URI getURI() {
                return uri;
            }
        };
        return execution.execute(modifiedRequest, body);
    }
}
