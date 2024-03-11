package lucene.cyborg.jni;

import lucene.cyborg.jni.aggregator.XorDocIdAggregatorManagerData;
import lucene.cyborg.jni.collector.CollectorManagerData;
import lucene.cyborg.jni.collector.SimpleTopScoreDocsCollectorManagerData;
import lucene.cyborg.jni.query.BooleanQueryData;
import lucene.cyborg.jni.query.TermQueryData;

import java.io.IOException;
import java.util.Arrays;

public class KdyTestMain {
    public static void main(String... args) throws IOException {
        System.load("C:/Users/ipxds/CLionProjects//lucene-cyborg-cpp/cmake-build-debug/libLuceneCyborg.dll");
        String path = "C:\\Users\\ipxds\\workspace\\lucene-lc-perf\\indices\\wikimedium10m\\index";
        try (StaticMMapDirectoryReader reader = StaticMMapDirectoryReader.open(path)) {
            IndexSearcherProxy searcher = new IndexSearcherProxy(reader);

            int warmUp = 500;
            int n = 1000;

            for (int i = 0; i < warmUp; ++i) {
                searchTermQuery(searcher);
                // searchBooleanQuery(searcher);
                // aggregateTermQuery(searcher);
            }

            double tookSum = 0;
            long tooks[] = new long[n];
            for (int i = 0; i < n; ++i) {
                long s = System.nanoTime();
                searchTermQuery(searcher);
                // searchBooleanQuery(searcher);
                // aggregateTermQuery(searcher);
                long e = System.nanoTime();
                long took = e - s;
                tookSum += took;
                tooks[i] = took;
            }

            System.out.println("Avg: " + (tookSum / n) + "nano");
            Arrays.sort(tooks);
            System.out.println("0%: " + tooks[0] + "nano");
            System.out.println("25%: " + tooks[(int) (0.2 * n)] + "nano");
            System.out.println("50%: " + tooks[(int) (0.5 * n)] + "nano");
            System.out.println("75%: " + tooks[(int) (0.75 * n)] + "nano");
            System.out.println("90%: " + tooks[(int) (0.9 * n)] + "nano");
            System.out.println("99%: " + tooks[(int) (0.99 * n)] + "nano");
            System.out.println("99.99%: " + tooks[(int) (0.9999 * n)] + "nano");
        }  // End try
    }

    private static void searchBooleanQuery(IndexSearcherProxy searcherProxy) {
        BooleanQueryData booleanQueryData = new BooleanQueryData();

        TermQueryData termQueryData1 = new TermQueryData();
        termQueryData1.setField("body");
        termQueryData1.setTerm("ref");
        booleanQueryData.addQueryData(termQueryData1, BooleanQueryData.BOOLEAN_CLAUSE_MUST);

        TermQueryData termQueryData2 = new TermQueryData();
        termQueryData2.setField("body");
        termQueryData2.setTerm("there");
        booleanQueryData.addQueryData(termQueryData2, BooleanQueryData.BOOLEAN_CLAUSE_MUST);

        CollectorManagerData collectorManagerData =
                new SimpleTopScoreDocsCollectorManagerData(CollectorManagerData.TOP_SCORES, 30);
        searcherProxy.search(booleanQueryData, collectorManagerData);
    }

    private static void searchTermQuery(IndexSearcherProxy searcherProxy) {
        TermQueryData termQueryData = new TermQueryData();
        termQueryData.setTerm("spring");
        termQueryData.setField("body");

        CollectorManagerData collectorManagerData =
                new SimpleTopScoreDocsCollectorManagerData(CollectorManagerData.TOP_SCORES, 30);
        searcherProxy.search(termQueryData, collectorManagerData);
    }

    private static void aggregateTermQuery(IndexSearcherProxy searcherProxy) {
        XorDocIdAggregatorManagerData xorDocIdAggregatorManagerData = new XorDocIdAggregatorManagerData();
        TermQueryData termQueryData = new TermQueryData();
        termQueryData.setField("body");
        termQueryData.setTerm("spring");
        searcherProxy.aggregate(termQueryData, xorDocIdAggregatorManagerData);
    }
}
