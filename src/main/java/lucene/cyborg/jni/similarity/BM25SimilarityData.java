package lucene.cyborg.jni.similarity;

public class BM25SimilarityData extends SimilarityData {
    public BM25SimilarityData() {
        super(SimilarityTypeIds.BM25_SIMILARITY);
    }

    public float getK1() {
        return k1;
    }

    public void setK1(float k1) {
        this.k1 = k1;
    }

    public float getB() {
        return b;
    }

    public void setB(float b) {
        this.b = b;
    }

    public boolean isDiscountOverlaps() {
        return discountOverlaps;
    }

    public void setDiscountOverlaps(boolean discountOverlaps) {
        this.discountOverlaps = discountOverlaps;
    }

    private float k1 = 1.2F;
    private float b = 0.75F;
    private boolean discountOverlaps = true;
}
