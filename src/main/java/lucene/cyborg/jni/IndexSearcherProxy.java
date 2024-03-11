package lucene.cyborg.jni;

import lucene.cyborg.jni.aggregator.AggregatorManagerData;
import lucene.cyborg.jni.collector.CollectorManagerData;
import lucene.cyborg.jni.query.QueryData;
import lucene.cyborg.jni.similarity.BM25SimilarityData;
import lucene.cyborg.jni.similarity.SimilarityData;

import java.util.Objects;

public class IndexSearcherProxy {
    private static SimilarityData DEFAULT_SIMILARITY = new BM25SimilarityData();

    public IndexSearcherProxy(StaticMMapDirectoryReader staticMMapDirectoryReader) {
        Objects.requireNonNull(staticMMapDirectoryReader);
        if (staticMMapDirectoryReader.getCyborgIndexReaderPointer() != StaticMMapDirectoryReader.NULL_POINTER) {
            this.staticMMapDirectoryReader = staticMMapDirectoryReader;
        }
        // TODO : throw
    }

    public void search(QueryData queryData, CollectorManagerData collectorManagerData) {
        search(staticMMapDirectoryReader.getCyborgIndexReaderPointer(),
                queryData,
                collectorManagerData);
    }

    private native void search(long mmapDirectoryReaderPointer,
                               QueryData queryData,
                               CollectorManagerData collectorManagerData);

    public void aggregate(QueryData queryData, AggregatorManagerData aggregatorManagerData) {
        aggregate(staticMMapDirectoryReader.getCyborgIndexReaderPointer(),
                queryData,
                aggregatorManagerData);
    }

    private native void aggregate(long mmapDirectoryReaderPointer,
                                  QueryData queryData,
                                  AggregatorManagerData aggregatorManagerData);

    public void setSimilarityData(SimilarityData similarityData) {
        Objects.requireNonNull(similarityData, "similarityData");
        this.similarityData = similarityData;
    }

    private StaticMMapDirectoryReader staticMMapDirectoryReader;
    private SimilarityData similarityData = DEFAULT_SIMILARITY;
}
