package io.github.microcks.dto.body;

public abstract class AbstractBody implements Body {

    private MatchType matchType;
    private String contentType;

    /**
     * @return the contentType
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * @param contentType the contentType to set
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * @return the matchType
     */
    public MatchType getMatchType() {
        return matchType;
    }

    /**
     * @param matchType the matchType to set
     */
    public void setMatchType(MatchType matchType) {
        this.matchType = matchType;
    }

}
