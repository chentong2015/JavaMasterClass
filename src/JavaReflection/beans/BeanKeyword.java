package JavaReflection.beans;

// Java Bean Class
public class BeanKeyword {

    private int keywordId;
    private String keywordSourceId;
    private String keywordDescription;

    public BeanKeyword() {
    }

    public BeanKeyword(int keywordId, String keywordSourceId, String keywordDescription) {
        this.keywordId = keywordId;
        this.keywordSourceId = keywordSourceId;
        this.keywordDescription = keywordDescription;
    }

    public int getKeywordId() {
        return keywordId;
    }

    public void setKeywordId(int keywordId) {
        this.keywordId = keywordId;
    }

    public String getKeywordSourceId() {
        return keywordSourceId;
    }

    public void setKeywordSourceId(String keywordSourceId) {
        this.keywordSourceId = keywordSourceId;
    }

    public String getKeywordDescription() {
        return keywordDescription;
    }

    public void setKeywordDescription(String keywordDescription) {
        this.keywordDescription = keywordDescription;
    }
}
