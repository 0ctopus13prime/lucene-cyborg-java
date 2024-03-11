package lucene.cyborg.jni.aggregator;

public abstract class AggregatorManagerData {
    protected AggregatorManagerData(int typeId) {
        this.typeId = typeId;
    }

    protected int typeId;
}
