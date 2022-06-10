package de.tum.in.ase.insertteamnamehere.controller;

import de.tum.in.ase.insertteamnamehere.model.Table;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

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

    public void addTable(){

    }
    public void deleteTable(){

    }
    public void updateTable(){

    }
    public void getAllTables(){

    }
}
