# Lucene Cyborg Java
This package is a JNI wrapper for [lucene-cyborg-cpp](https://github.com/0ctopus13prime/lucene-cyborg-cpp). <br/>
It enables users to indirectly mmap and load Lucene segments and perform term search and boolean term search.

Current alpha version is compatible with Lucene 9.8.0 version.


# Example
```java
final String indexDir = ...;  // Location of Lucene segment.
final String luceneCyborgCppLibraryPath = ...;  // SO file path.

// 1. Load SO library first.
System.load(luceneCyborgCppLibraryPath);

// 2. MMap Lucene segment
try (StaticMMapDirectoryReader directoryReader = StaticMMapDirectoryReader.open(indexDir)) {
    // 3. Create searcher proxy. It just prepares required parameters to pass.
    IndexSearcherProxy searcher = new IndexSearcherProxy(directoryReader);
    
    // 4. Term query + doc match count collector.
    TermQueryData termQueryData = new TermQueryData();
    termQueryData.setField("text");
    termQueryData.setTerm("observatory");

    final MatchCountCollectorManagerData matchCountCollectorManagerData =
        new MatchCountCollectorManagerData();
    searcher.search(termQueryData, matchCountCollectorManagerData);
    
    // 5. Print #doc match count
    System.out.println(matchCountCollectorManagerData.getCount());

    // 6. Create searcher proxy with BM25 similarity.
    searcher = new IndexSearcherProxy(directoryReader);
    BM25SimilarityData bm25SimilarityData = new BM25SimilarityData();
    bm25SimilarityData.setK1(0.9f);
    bm25SimilarityData.setB(0.4f);
    bm25SimilarityData.setDiscountOverlaps(true);
    searcher.setSimilarityData(bm25SimilarityData);
    
    // 7. Boolean query
    final SimpleTopScoreDocsCollectorManagerData topScoreDocCollector =
        new SimpleTopScoreDocsCollectorManagerData(
                SimpleTopScoreDocsCollectorManagerData.TOP_SCORES, 100);
    searcher.search(query, topScoreDocCollector);
    
    // 8. Print matched docs with scores.
    for (ScoreDoc scoreDoc : topScoreDocCollector.getTopDocs().getScoreDocs()) {
        System.out.println("doc= " + scoreDoc.getDoc() + ", score=" + scoreDoc.getScore()); 
    }
}
```