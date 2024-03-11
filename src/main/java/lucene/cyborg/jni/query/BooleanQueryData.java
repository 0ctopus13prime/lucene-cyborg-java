package lucene.cyborg.jni.query;

public class BooleanQueryData extends QueryData {
    public static final int BOOLEAN_CLAUSE_MUST = 0;
    public static final int BOOLEAN_CLAUSE_FILTER = 1;
    public static final int BOOLEAN_CLAUSE_SHOULD = 2;
    public static final int BOOLEAN_CLAUSE_MUST_NOT = 3;

    public BooleanQueryData() {
        super(QueryTypeIds.BOOLEAN_QUERY);
        queries = new QueryData[1];
        clauses = new int[1];
    }

    public void addQueryData(QueryData queryData, int booleanClause) {
        if (next >= queries.length) {
            int newLen = Math.max(1, queries.length * 2);
            QueryData newQueries[] = new QueryData[newLen];
            System.arraycopy(queries, 0, newQueries, 0, next);
            queries = newQueries;
            int newClauses[] = new int[newLen];
            System.arraycopy(clauses, 0, newClauses, 0, next);
            clauses = newClauses;
        }

        queries[next] = queryData;
        clauses[next++] = booleanClause;
    }

    public int getMinimumNumberShouldMatch() {
        return minimumNumberShouldMatch;
    }

    public void setMinimumNumberShouldMatch(int minimumNumberShouldMatch) {
        this.minimumNumberShouldMatch = minimumNumberShouldMatch;
    }

    int minimumNumberShouldMatch;
    int next;
    QueryData queries[];
    int clauses[];
}
