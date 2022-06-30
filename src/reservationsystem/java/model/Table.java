package model;

public class Table {
    private boolean reserved;

    private final int maxNumberOfPeople;

    private final String tableId;

    public Table(int maxNumberOfPeople, String tableId) {
        this.maxNumberOfPeople = maxNumberOfPeople;
        this.reserved = false;
        this.tableId = tableId;
    }

    public int getMaxNumberOfPeople() {
        return maxNumberOfPeople;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public String getTableId() {
        return tableId;
    }
}
