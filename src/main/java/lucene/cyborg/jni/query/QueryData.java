package lucene.cyborg.jni.query;

public abstract class QueryData {
    protected QueryData(int typeId) {
        this.typeId = typeId;
    }

    public int getTypeId() {
        return typeId;
    }

    protected int typeId;
}
