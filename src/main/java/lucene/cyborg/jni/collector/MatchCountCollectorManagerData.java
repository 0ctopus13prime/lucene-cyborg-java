package lucene.cyborg.jni.collector;

public class MatchCountCollectorManagerData extends CollectorManagerData {
    public MatchCountCollectorManagerData() {
        super(CollectorManagerTypeIds.MATCH_COUNT_COLLECTOR_MANAGER,
                CollectorManagerData.COMPLETE_NO_SCORE);
    }

    private long count;

    public long getCount() {
        return count;
    }
}
