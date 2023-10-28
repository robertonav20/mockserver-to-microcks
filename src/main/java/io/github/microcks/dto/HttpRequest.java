package io.github.microcks.dto;

import java.util.List;
import java.util.Map;

public class HttpRequest {
    private String method;
    private String path;
    private Map<String, List<String>> pathParameters;
    private Map<String, List<String>> queryStringParameters;
    private Map<String, List<String>> headers;
    private Cookies cookies;
    private Object body;

    /**
     * @return the method
     */
    public String getMethod() {
        return method;
    }

    /**
     * @param method the method to set
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return the pathParameters
     */
    public Map<String, List<String>> getPathParameters() {
        return pathParameters;
    }

    /**
     * @param pathParameters the pathParameters to set
     */
    public void setPathParameters(Map<String, List<String>> pathParameters) {
        this.pathParameters = pathParameters;
    }

    /**
     * @return the queryStringParameters
     */
    public Map<String, List<String>> getQueryStringParameters() {
        return queryStringParameters;
    }

    /**
     * @param queryStringParameters the queryStringParameters to set
     */
    public void setQueryStringParameters(Map<String, List<String>> queryStringParameters) {
        this.queryStringParameters = queryStringParameters;
    }

    /**
     * @return the headers
     */
    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    /**
     * @param headers the headers to set
     */
    public void setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }

    /**
     * @return the cookies
     */
    public Cookies getCookies() {
        return cookies;
    }

    /**
     * @param cookies the cookies to set
     */
    public void setCookies(Cookies cookies) {
        this.cookies = cookies;
    }

    /**
     * @return the body
     */
    public Object getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(Object body) {
        this.body = body;
    }
}
