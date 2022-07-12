package de.tum.in.ase.insertteamnamehere.model;

import java.util.UUID;

public class Table {
    private boolean reserved;
    private UUID tableID;

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


    public UUID getTableID() {
        return tableID;
    }

    public void setTableID(UUID tableID) {
        this.tableID = tableID;
    }

    public String getTableId() {
        return tableId;

    }
}
