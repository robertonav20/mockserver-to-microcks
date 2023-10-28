package io.github.microcks.dto;

public class MockServerExample {
    private HttpRequest httpRequest;
    private HttpResponse httpResponse;

    /**
     * @return the httpRequest
     */
    public HttpRequest getHttpRequest() {
        return httpRequest;
    }
    /**
     * @param httpRequest the httpRequest to set
     */
    public void setHttpRequest(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }
    /**
     * @return the httpResponse
     */
    public HttpResponse getHttpResponse() {
        return httpResponse;
    }
    /**
     * @param httpResponse the httpResponse to set
     */
    public void setHttpResponse(HttpResponse httpResponse) {
        this.httpResponse = httpResponse;
    }
}
