package de.tum.in.ase.insertteamnamehere.service;

import de.tum.in.ase.insertteamnamehere.model.Table;

import javax.swing.text.TabExpander;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class TableService {

    private final List<Table> tableList;

    public TableService() {
        this.tableList = new ArrayList<>();

    }

    public Table saveTable(Table table) {
        var optionalTable = tableList.stream().filter(existingTable -> existingTable.getTableID().equals(table.getTableID())).findFirst();
        if (optionalTable.isEmpty()) {
            tableList.add(table);
            return table;
        } else {
            var existingTable = optionalTable.get();
            //auch hier müssen die attribute für den Tisch noch gesetzt werden
            return existingTable;
        }

    }

    public void deleteTable(UUID tableID) {
        this.tableList.removeIf(table -> table.getTableID().equals(tableID));

    }

    public List<Table> getAllTables() {
        return Collections.unmodifiableList(this.tableList);
    }
}
