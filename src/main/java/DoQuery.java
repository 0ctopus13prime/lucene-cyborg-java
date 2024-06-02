import lucene.cyborg.jni.IndexSearcherProxy;
import lucene.cyborg.jni.StaticMMapDirectoryReader;
import lucene.cyborg.jni.collector.MatchCountCollectorManagerData;
import lucene.cyborg.jni.collector.SimpleTopScoreDocsCollectorManagerData;
import lucene.cyborg.jni.query.BooleanQueryData;
import lucene.cyborg.jni.query.QueryData;
import lucene.cyborg.jni.query.TermQueryData;
import lucene.cyborg.jni.similarity.BM25SimilarityData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DoQuery {
    public static void main(String[] args) throws IOException {
        final String indexDir = args[0];
        final String libraryPath = args[1];

        System.load(libraryPath);

        try (StaticMMapDirectoryReader directoryReader =
                     StaticMMapDirectoryReader.open(indexDir);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))
        ) {
            final IndexSearcherProxy searcher = new IndexSearcherProxy(directoryReader);
            BM25SimilarityData bm25SimilarityData = new BM25SimilarityData();
            bm25SimilarityData.setK1(0.9f);
            bm25SimilarityData.setB(0.4f);
            bm25SimilarityData.setDiscountOverlaps(true);
            searcher.setSimilarityData(bm25SimilarityData);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final String[] fields = line.trim().split("\t");
                assert fields.length == 2;
                final String command = fields[0];
                final String query_str = fields[1];
                QueryData query = parse(query_str);
                final int count;
                switch (command) {
                    case "TOP_100": {
                        final SimpleTopScoreDocsCollectorManagerData topScoreDocCollector =
                                new SimpleTopScoreDocsCollectorManagerData(
                                        SimpleTopScoreDocsCollectorManagerData.TOP_SCORES,
                                        100, 100);
                        searcher.search(query, topScoreDocCollector);
                        count = 1;
                        System.out.println(count);
                    }
                    break;
                    case "TOP_100_COUNT": {
                        final SimpleTopScoreDocsCollectorManagerData topScoreDocCollector =
                                new SimpleTopScoreDocsCollectorManagerData(
                                        SimpleTopScoreDocsCollectorManagerData.TOP_SCORES,
                                        100, Integer.MAX_VALUE);
                        searcher.search(query, topScoreDocCollector);
                        count = 1;
                        System.out.println(count);
                    }
                    break;
                    case "COUNT": {
                        final MatchCountCollectorManagerData matchCountCollectorManagerData =
                                new MatchCountCollectorManagerData();
                        searcher.search(query, matchCountCollectorManagerData);
                        System.out.println(matchCountCollectorManagerData.getCount());
                    }
                    break;
                    default:
                        System.out.println("UNSUPPORTED");
                }
            }
        }
    }

    private static QueryData parse(String query_str) {
        String[] queries = query_str.split(" ");
        BooleanQueryData booleanQueryData = new BooleanQueryData();
        for (String q : queries) {
            TermQueryData termQueryData = new TermQueryData();
            termQueryData.setField("text");
            if (q.charAt(0) == '+') {
                termQueryData.setTerm(q.substring(1));
                booleanQueryData.addQueryData(termQueryData, BooleanQueryData.BOOLEAN_CLAUSE_MUST);
            } else {
                termQueryData.setTerm(q);
                booleanQueryData.addQueryData(termQueryData, BooleanQueryData.BOOLEAN_CLAUSE_SHOULD);
            }
        }
        return booleanQueryData;
    }
}
