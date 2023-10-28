package io.github.microcks.dto.body;

import java.util.List;
import java.util.Map;

public class ParametersBody extends AbstractBody {
    private BodyType type = BodyType.PARAMETERS;
    private Map<String, List<String>> body;

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
    public Map<String, List<String>> getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(Map<String, List<String>> body) {
        this.body = body;
    }

}
