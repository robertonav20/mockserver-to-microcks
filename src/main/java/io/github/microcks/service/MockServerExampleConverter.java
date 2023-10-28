package io.github.microcks.service;

import java.util.List;
import java.util.Map;
import io.github.microcks.dto.MockServerExample;
import io.github.microcks.mapper.MockServerExampleMapper;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.SpecVersion;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import jakarta.annotation.Resource;
import jakarta.inject.Singleton;
import jakarta.ws.rs.HttpMethod;
import jakarta.ws.rs.core.HttpHeaders;

@Singleton
public class MockServerExampleConverter {

    @Resource
    private MockServerExampleMapper mapper;

    public OpenAPI convert(MockServerExample mock) {
        String endpointUrl = mock.getHttpRequest().getPath();
        String httpMethod = mock.getHttpRequest().getMethod();
        String exampleName = "test";

        String contentType = jakarta.ws.rs.core.MediaType.TEXT_PLAIN;
        List<String> values = mock.getHttpResponse().getHeaders().get(HttpHeaders.CONTENT_TYPE);
        if (values != null && !values.isEmpty()) {
            contentType = values.get(0);
        }

        MediaType mediaType = new MediaType();
        mediaType.schema(new ObjectSchema());
        mediaType.addExamples(exampleName, new Example().value(mock.getHttpResponse().getBody()));

        Content content = new Content();
        content.addMediaType(contentType, mediaType);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.content(content);
        apiResponse.description(httpMethod.concat(" ").concat(endpointUrl).concat(" description"));

        Operation operation = new Operation();
        Map<String, List<String>> requestHeaders = mock.getHttpRequest().getHeaders();
        if (requestHeaders != null && !requestHeaders.isEmpty()) {
            requestHeaders.entrySet().stream().forEach(e -> {
                HeaderParameter headerParameter = new HeaderParameter();
                headerParameter.setName(e.getKey());
                headerParameter.schema(new StringSchema());
                headerParameter.addExample(exampleName, new Example().value(e.getValue().get(0)));

                operation.addParametersItem(headerParameter);
            });
        }

        PathItem pathItem = new PathItem();
        if (HttpMethod.GET.equalsIgnoreCase(httpMethod)) {
            pathItem.get(operation.responses(new ApiResponses().addApiResponse(
                    String.valueOf(mock.getHttpResponse().getStatusCode()), apiResponse)));
        } else if (HttpMethod.PUT.equalsIgnoreCase(httpMethod)) {
            pathItem.put(operation.responses(new ApiResponses().addApiResponse(
                    String.valueOf(mock.getHttpResponse().getStatusCode()), apiResponse)));
        } else if (HttpMethod.DELETE.equalsIgnoreCase(httpMethod)) {
            pathItem.delete(operation.responses(new ApiResponses().addApiResponse(
                    String.valueOf(mock.getHttpResponse().getStatusCode()), apiResponse)));
        } else if (HttpMethod.POST.equalsIgnoreCase(httpMethod)) {
            pathItem.post(operation.responses(new ApiResponses().addApiResponse(
                    String.valueOf(mock.getHttpResponse().getStatusCode()), apiResponse)));
        } else if (HttpMethod.PATCH.equalsIgnoreCase(httpMethod)) {
            pathItem.post(operation.responses(new ApiResponses().addApiResponse(
                    String.valueOf(mock.getHttpResponse().getStatusCode()), apiResponse)));
        }

        Info info = new Info();
        info.setTitle("API Title");
        info.setDescription("API Description");
        info.setVersion("1.0.0");

        Paths paths = new Paths();
        paths.addPathItem(endpointUrl, pathItem);

        OpenAPI openAPI = new OpenAPI();
        openAPI.setInfo(info);
        openAPI.setPaths(paths);
        openAPI.setSpecVersion(SpecVersion.V31);

        return openAPI;
    }
}
