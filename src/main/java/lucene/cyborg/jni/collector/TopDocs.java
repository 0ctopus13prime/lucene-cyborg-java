package lucene.cyborg.jni.collector;

public class TopDocs {
    public static final int TOTAL_HITS_RELATION_EQUAL_TO = 0;
    public static final int TOTAL_HITS_RELATION_GREATER_THAN_OR_EQUAL_TO = 1;

    public void populateEmptyScoreDocs(int numHits) {
        scoreDocs = new ScoreDoc[numHits];
        for (int i = 0 ; i < numHits ; ++i) {
            scoreDocs[i] = new ScoreDoc();
        }
    }

    public long getTotalHits() {
        return totalHits;
    }

    public int getTotalHitsRelation() {
        return totalHitsRelation;
    }

    public ScoreDoc[] getScoreDocs() {
        return scoreDocs;
    }

    long totalHits;
    int totalHitsRelation;
    ScoreDoc[] scoreDocs;
}
