package lucene.cyborg.jni.aggregator;

public class XorDocIdAggregatorManagerData extends AggregatorManagerData {
    public XorDocIdAggregatorManagerData() {
        super(AggregatorTypeIds.XOR_DOC_IDS_AGGREGATOR_MANAGER);
    }

    public long getXorResult() {
        return xorResult;
    }

    public int getCount() {
        return count;
    }

    public int getLastDocId() {
        return lastDocId;
    }

    private long xorResult;
    private int count;
    private int lastDocId;
}
