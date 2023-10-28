package io.github.microcks.resource;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.v3.core.util.Yaml;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.SpecVersion;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/example/swagger")
public class ExampleSwaggerResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleSwaggerResource.class);

    @GET
    @Path("json")
    @Produces(MediaType.TEXT_PLAIN)
    public String json() {
        OpenAPI openAPI = createOpenApi();
        String json = Json.pretty(openAPI);
        LOGGER.info("{}", json);

        return json;
    }

    @GET
    @Path("yaml")
    @Produces(MediaType.TEXT_PLAIN)
    public String yaml() {
        OpenAPI openAPI = createOpenApi();
        String yaml = Yaml.pretty(openAPI);
        LOGGER.info("{}", yaml);

        return yaml;
    }

    private OpenAPI createOpenApi() {
        Paths paths = new Paths();
        String endpointUrl = "/api/resource";
        String httpMethod = "GET";
        PathItem pathItem = new PathItem();
        paths.addPathItem(endpointUrl, pathItem);

        if (httpMethod.equals("GET")) {
            ApiResponse apiResponse = new ApiResponse().description("OK")
                    .content(new Content().addMediaType("application/json",
                            new io.swagger.v3.oas.models.media.MediaType()
                                    .schema(new StringSchema())));

            pathItem.get(new Operation()
                    .responses(new ApiResponses().addApiResponse("200", apiResponse)));
        }

        Info info = new Info();
        info.setTitle("API Title");
        info.setDescription("API Description");
        info.setVersion("1.0.0");

        OpenAPI openAPI = new OpenAPI();
        openAPI.setInfo(info);
        openAPI.setPaths(paths);
        openAPI.setSpecVersion(SpecVersion.V31);
        return openAPI;
    }
}
