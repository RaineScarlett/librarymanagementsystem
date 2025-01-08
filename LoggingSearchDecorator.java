public class LoggingSearchDecorator implements SearchStrategy {
    private SearchStrategy strategy;

    public LoggingSearchDecorator(SearchStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void searchMethod(String query) {
        // Track the start time
        long startTime = System.nanoTime();

        // Perform the search
        System.out.println("[LOG] executing search for query: " + query);
        strategy.searchMethod(query); // Delegate to the actual search strategy

        // Track the end time
        long endTime = System.nanoTime();

        // Calculate the time elapsed in milliseconds
        long timeElapsed = (endTime - startTime) / 1_000_000;

        // Log the time elapsed
        System.out.println("[LOG] Time taken for search: " + timeElapsed + " ms");
    }
}