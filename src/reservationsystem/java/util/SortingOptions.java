package util;

public class SortingOptions {
    private SortingOrder sortingOrder;
    private SortField sortField;

    public SortingOptions(SortingOrder sortingOrder, SortField sortField) {
        this.sortingOrder = sortingOrder;
        this.sortField = sortField;
    }

    public SortingOrder getSortingOrder() {
        return sortingOrder;
    }

    public void setSortingOrder(SortingOrder sortingOrder) {
        this.sortingOrder = sortingOrder;
    }

    public SortField getSortField() {
        return sortField;
    }

    public void setSortField(SortField sortField) {
        this.sortField = sortField;
    }

    public enum SortingOrder {
        ASCENDING, DESCENDING
    }

    public enum SortField {
        RESTAURANT_TYPE, DISTANCE, PRIZE_CATEGORY, AVERAGE_RATING, FREE_TIME_SLOTS
    }
}
