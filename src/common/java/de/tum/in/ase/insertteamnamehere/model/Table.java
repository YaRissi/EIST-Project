package de.tum.in.ase.insertteamnamehere.model;

import java.util.UUID;

public class Table {
    private boolean reserved;
    private final String tableID;

    private final int maxNumberOfPeople;

    public Table(int maxNumberOfPeople, String tableID) {
        this.maxNumberOfPeople = maxNumberOfPeople;
        this.reserved = false;
        this.tableID = tableID;
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


    public String getTableID() {
        return tableID;
    }

}
