package de.tum.in.ase.insertteamnamehere.rest;

import de.tum.in.ase.insertteamnamehere.model.Table;
import de.tum.in.ase.insertteamnamehere.service.TableService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class TableResource {

    private final TableService tableService;



    public TableResource(TableService tableService){
        this.tableService = tableService;
   }

    @GetMapping("tables")
    public ResponseEntity<List<Table>> getAllTables() {

        return ResponseEntity.ok(tableService.getAllTables());
    }

    @PostMapping("tables")
    public ResponseEntity<Table> createTable(@RequestBody Table table) {
        if (table.getTableID() != null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tableService.saveTable(table));
    }

    @PutMapping("tables/{tableID}")
    public ResponseEntity<Table> updateTable(@RequestBody Table updatedTable, @PathVariable("tableID") UUID tableID) {
        if (!updatedTable.getTableID().equals(tableID)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tableService.saveTable(updatedTable));
    }

    @DeleteMapping("tables/{tableID}")
    public ResponseEntity<Void> deleteNote(@PathVariable("tableID") UUID tableID) {
        tableService.deleteTable(tableID);
        return ResponseEntity.noContent().build();
    }
}
