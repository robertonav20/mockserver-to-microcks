package io.github.microcks.service;

import io.github.microcks.dto.MockServerExample;
import io.github.microcks.mapper.MockServerExampleMapper;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.SpecVersion;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import jakarta.annotation.Resource;
import jakarta.inject.Singleton;
import jakarta.ws.rs.HttpMethod;

@Singleton
public class MockServerExampleConverter {

    @Resource
    private MockServerExampleMapper mapper;

    public OpenAPI convert(MockServerExample mock) {
        String endpointUrl = mock.getHttpRequest().getPath();
        String httpMethod = mock.getHttpRequest().getMethod();

        Content content = new Content();
        content.addMediaType(jakarta.ws.rs.core.MediaType.TEXT_PLAIN,
                new MediaType().schema(new StringSchema()));

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.content(content);
        apiResponse.description(httpMethod.concat(" ").concat(endpointUrl).concat(" description"));

        PathItem pathItem = new PathItem();
        if (HttpMethod.GET.equalsIgnoreCase(httpMethod)) {
            pathItem.get(new Operation().responses(new ApiResponses().addApiResponse(
                    String.valueOf(mock.getHttpResponse().getStatusCode()), apiResponse)));
        } else if (HttpMethod.PUT.equalsIgnoreCase(httpMethod)) {
            pathItem.put(new Operation().responses(new ApiResponses().addApiResponse(
                    String.valueOf(mock.getHttpResponse().getStatusCode()), apiResponse)));
        } else if (HttpMethod.DELETE.equalsIgnoreCase(httpMethod)) {
            pathItem.delete(new Operation().responses(new ApiResponses().addApiResponse(
                    String.valueOf(mock.getHttpResponse().getStatusCode()), apiResponse)));
        } else if (HttpMethod.POST.equalsIgnoreCase(httpMethod)) {
            pathItem.post(new Operation().responses(new ApiResponses().addApiResponse(
                    String.valueOf(mock.getHttpResponse().getStatusCode()), apiResponse)));
        } else if (HttpMethod.PATCH.equalsIgnoreCase(httpMethod)) {
            pathItem.post(new Operation().responses(new ApiResponses().addApiResponse(
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
