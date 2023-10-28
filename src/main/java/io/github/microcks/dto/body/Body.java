package io.github.microcks.dto.body;

public interface Body {
    public BodyType getType();
    public MatchType getMatchType();
    public Object getBody();
    public String getContentType();
}
