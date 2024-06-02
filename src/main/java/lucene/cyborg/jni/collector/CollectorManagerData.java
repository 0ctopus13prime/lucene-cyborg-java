package lucene.cyborg.jni.collector;

public abstract class CollectorManagerData {
    public static final int COMPLETE_NO_SCORE = 0;
    public static final int TOP_SCORES = 1;

    protected CollectorManagerData(int typeId, int scoreMode) {
        this.typeId = typeId;
        this.scoreMode = scoreMode;
    }

    public int getTypeId() {
        return typeId;
    }

    protected int typeId;
    protected int scoreMode;
}
