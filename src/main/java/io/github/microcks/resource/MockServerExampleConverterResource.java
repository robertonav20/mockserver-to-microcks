package io.github.microcks.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.github.microcks.dto.MockServerExample;
import io.github.microcks.service.MockServerExampleConverter;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.core.util.Yaml;
import io.swagger.v3.oas.models.OpenAPI;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/convert")
public class MockServerExampleConverterResource {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(MockServerExampleConverterResource.class);

    @Inject
    private MockServerExampleConverter converter;

    @POST
    @Path("json")
    @Produces(MediaType.TEXT_PLAIN)
    public String json(MockServerExample request) {
        LOGGER.info("Request {}", request);

        OpenAPI openAPI = converter.convert(request);
        String json = Json.pretty(openAPI);

        LOGGER.info("{}", json);

        return json;
    }

    @POST
    @Path("yaml")
    @Produces(MediaType.TEXT_PLAIN)
    public String yaml(MockServerExample request) {
        LOGGER.info("Request {}", request);

        OpenAPI openAPI = converter.convert(request);
        String yaml = Yaml.pretty(openAPI);

        LOGGER.info("{}", yaml);

        return yaml;
    }
}
