package lucene.cyborg.jni;

public class JniTest {
//    public static void main(String... args) {
//        System.load("C:\\Users\\ipxds\\CLionProjects\\lucene-cyborg-cpp\\cmake-build-debug\\libLuceneCyborg.dll");
//
//        int warmUp = 500;
//        int n = 2000;
//
//        for (int i = 0 ; i < warmUp ; ++i) {
//             searchTermQuery();
////            aggregateTermQuery();
//        }
//
//        double tookSum = 0;
//        long tooks[] = new long[n];
//        for (int i = 0 ; i < n ; ++i) {
//            long s = System.nanoTime();
//             searchTermQuery();
////            aggregateTermQuery();
//            long e = System.nanoTime();
//            long took = e - s;
//            tookSum += took;
//            tooks[i] = took;
//        }
//
//        System.out.println("Avg: " + (tookSum / n) + "nano");
//        Arrays.sort(tooks);
//        System.out.println("0%: " + tooks[0] + "nano");
//        System.out.println("25%: " + tooks[(int) (0.2 * n)] + "nano");
//        System.out.println("50%: " + tooks[(int) (0.5 * n)] + "nano");
//        System.out.println("75%: " + tooks[(int) (0.75 * n)] + "nano");
//        System.out.println("90%: " + tooks[(int) (0.9 * n)] + "nano");
//        System.out.println("99%: " + tooks[(int) (0.99 * n)] + "nano");
//        System.out.println("99.99%: " + tooks[(int) (0.9999 * n)] + "nano");
//    }

//    private static void searchTermQuery() {
//        TermQueryData termQueryData = new TermQueryData();
//        termQueryData.setTerm("term");
//        termQueryData.setField("field");
//
//        IndexSearcher indexSearcher = new IndexSearcher();
//
//        CollectorManagerData collectorManagerData = new SimpleTopScoreDocsCollectorManagerData();
//        indexSearcher.search(termQueryData, collectorManagerData);
//    }

//    private static void aggregateTermQuery() {
//        TermQueryData termQueryData = new TermQueryData();
//        termQueryData.setTerm("term");
//        termQueryData.setField("field");
//
//        IndexSearcher indexSearcher = new IndexSearcher();
//
//        AggregatorManagerData aggregatorManagerData = new XorDocIdAggregatorManagerData();
//        indexSearcher.aggregate(termQueryData, aggregatorManagerData);
//    }
}
