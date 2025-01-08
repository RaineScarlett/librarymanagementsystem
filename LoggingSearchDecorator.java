public class LoggingSearchDecorator extends SearchStrategyDecorator{

    public LoggingSearchDecorator(SearchStrategy strategy) {
        super(strategy);
    }

    @Override
    public void searchMethod(String query) {
        System.out.println("[LOG] executing search for query: " + query);

        super.searchMethod(query);
    }
}
