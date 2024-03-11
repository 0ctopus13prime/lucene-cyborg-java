package lucene.cyborg.jni.collector;

public class ScoreDoc {
    public void setDocAndScore(int doc, float score) {
        this.doc = doc;
        this.score = score;
    }

    public int getDoc() {
        return doc;
    }

    public float getScore() {
        return score;
    }

    private int doc;
    private float score;
}
