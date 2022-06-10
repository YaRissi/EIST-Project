package de.tum.in.ase.insertteamnamehere.model;

public class Table {
    private boolean reserved;

    private final int maxNumberOfPeople;

    public Table(int maxNumberOfPeople) {
        this.maxNumberOfPeople = maxNumberOfPeople;
        this.reserved = false;
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
}
