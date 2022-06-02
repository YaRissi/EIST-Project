package model;

public class Table {
    private boolean reserved;

    private int MaxNumberOfPeople;

    public int getMaxNumberOfPeople() {
        return MaxNumberOfPeople;
    }

    public void setMaxNumberOfPeople(int maxNumberOfPeople) {
        MaxNumberOfPeople = maxNumberOfPeople;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }
}
