package io.github.microcks.dto.body;

public class XPathBody extends AbstractBody {
    private BodyType type = BodyType.XPATH;
    private String body;
    private boolean not;

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
     * @return the jsonPath
     */
    public String getBody() {
        return body;
    }

    /**
     * @param jsonPath the jsonPath to set
     */
    public void setBody(String jsonPath) {
        this.body = jsonPath;
    }

    /**
     * @return the not
     */
    public boolean isNot() {
        return not;
    }

    /**
     * @param not the not to set
     */
    public void setNot(boolean not) {
        this.not = not;
    }
}
