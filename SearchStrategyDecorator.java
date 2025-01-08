public class SearchStrategyDecorator implements SearchStrategy {
    protected SearchStrategy decoratedStrategy;

    public SearchStrategyDecorator(SearchStrategy Strategy) {
        this.decoratedStrategy = Strategy;
    }

    @Override
    public void searchMethod(String query) {
        decoratedStrategy.searchMethod(query);
    }
}
