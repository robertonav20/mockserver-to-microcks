package io.github.microcks.dto.body;

public class XMLSchemaBody extends AbstractBody {
    private BodyType type = BodyType.XML_SCHEMA;
    private String body;

    /**
     * @return the type
     */
    public BodyType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(BodyType type) {
        this.type = type;
    }

    /**
     * @return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(String body) {
        this.body = body;
    }
}
