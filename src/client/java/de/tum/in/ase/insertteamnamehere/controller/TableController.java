package de.tum.in.ase.insertteamnamehere.controller;

import de.tum.in.ase.insertteamnamehere.model.Table;
import javafx.scene.control.Tab;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class TableController {

    private final WebClient webClient;
    private final List<Table> tableList;

    public TableController() {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8080/")
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        this.tableList = new ArrayList<>();

    }

    public void addTable(Table table, Consumer<List<Table>> tableConsumer) {
        webClient.post()
                .uri("tables")
                .bodyValue(table)
                .retrieve()
                .bodyToMono(Table.class)
                .onErrorStop()
                .subscribe(newTable -> {
                    tableList.add(newTable);
                    tableConsumer.accept(tableList);
                });

    }

    public void updateTable(Table table, Consumer<List<Table>> tableConsumer) {
        webClient.put()
                .uri("tables/" + table.getTableID())
                .bodyValue(table)
                .retrieve()
                .bodyToMono(Table.class)
                .onErrorStop()
                .subscribe(newTable -> {
                    tableList.replaceAll(oldTable -> oldTable.getTableID().equals(newTable.getTableID()) ? newTable : oldTable);
                    tableConsumer.accept(tableList);
                });

    }

    public void deleteTable(Table table, Consumer<List<Table>> tableConsumer) {
        webClient.delete()
                .uri("tables/" + table.getTableID())
                .retrieve()
                .toBodilessEntity()
                .onErrorStop()
                .subscribe(v -> {
                    tableList.remove(table);
                    tableConsumer.accept(tableList);
                });

    }

    public void getAllTables(Consumer<List<Table>> tableConsumer) {
        webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("tables")
                        .queryParam("secret", "SecretKey")
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Table>>() {
                })
                .onErrorStop()
                .subscribe(newNotes -> {
                    tableList.clear();
                    tableList.addAll(newNotes);
                    tableConsumer.accept(tableList);
                });

    }
}
