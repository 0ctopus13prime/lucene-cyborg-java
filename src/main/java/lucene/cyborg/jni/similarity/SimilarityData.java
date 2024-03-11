package lucene.cyborg.jni.similarity;

public abstract class SimilarityData {
    protected SimilarityData(int typeId) {
        this.typeId = typeId;
    }

    protected int typeId;
}
