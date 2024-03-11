package lucene.cyborg.jni.collector;

public class SimpleTopScoreDocsCollectorManagerData extends CollectorManagerData {
    public SimpleTopScoreDocsCollectorManagerData(int scoreMode, int numHits) {
        super(CollectorManagerTypeIds.SIMPLE_TOP_SCORE_DOCS_COLLECTOR_MANAGER, scoreMode);
        this.numHits = numHits;
        if (numHits <= 0) {
            throw new RuntimeException(String.format("numHits(=%d) <= 0", numHits));
        }
        topDocs = new TopDocs();
        topDocs.populateEmptyScoreDocs(numHits);
    }

    public int getNumHits() {
        return numHits;
    }

    public TopDocs getTopDocs() {
        return topDocs;
    }

    int numHits;
    TopDocs topDocs;
}
