package JavaStream.base;

public class RecordComment {

    private final String oid;
    private final String content;

    public RecordComment(String oid, String content) {
        this.oid = oid;
        this.content = content;
    }

    public String getOid() {
        return oid;
    }

    public String getContent() {
        return content;
    }
}
