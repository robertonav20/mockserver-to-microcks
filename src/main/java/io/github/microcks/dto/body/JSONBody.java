package io.github.microcks.dto.body;

import java.util.Map;

public class JSONBody extends AbstractBody {
    private BodyType type = BodyType.JSON;
    private Map<String, Object> body;

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
    public Map<String, Object> getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(Map<String, Object> body) {
        this.body = body;
    }

}
