package de.tum.in.ase.insertteamnamehere.model;

import java.util.UUID;

public class Table {
    private boolean reserved;
    private final UUID tableID;

    private final int maxNumberOfPeople;

    public Table(int maxNumberOfPeople, UUID tableID) {
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


    public UUID getTableID() {
        return tableID;
    }

}
