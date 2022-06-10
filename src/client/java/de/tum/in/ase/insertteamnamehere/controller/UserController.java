package de.tum.in.ase.insertteamnamehere.controller;

import de.tum.in.ase.insertteamnamehere.user.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

public class UserController {

    private final WebClient webClient;
    private final List<User> userList;

    public UserController() {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8080/")
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        this.userList = new ArrayList<>();
    }

    public void addUser(){

    }
    public void updateUser(){

    }
    public void deleteUser(){

    }
    public void getAllUsers(){

    }
}
