public class TimingSearchDecorator  extends SearchStrategyDecorator{


    public TimingSearchDecorator(SearchStrategy Strategy) {
        super(Strategy);
    }

    @Override
    public void searchMethod(String query) {
        long startTime = System.currentTimeMillis();
        super.searchMethod(query);
        long endTime = System.currentTimeMillis();
        System.out.println("Time elapsed: " + (endTime - startTime));
    }
}
