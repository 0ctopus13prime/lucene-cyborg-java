package lucene.cyborg.jni.query;

public class TermQueryData extends QueryData {
    public TermQueryData() {
        super(QueryTypeIds.TERM_QUERY);
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    private String field;
    private String term;
}
