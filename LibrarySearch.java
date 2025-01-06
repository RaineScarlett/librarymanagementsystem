public class LibrarySearch {
    private SearchStrategy strategy;

    public void setSearchStrategy(SearchStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeSearch(String query) {
        if(strategy == null) {
            System.out.println("Search strategy is not set");
            return;
        }
        strategy.searchMethod(query);
    }
}
