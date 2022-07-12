package de.tum.in.ase.insertteamnamehere.controller;

import de.tum.in.ase.insertteamnamehere.user.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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

    public void addUser(User user, Consumer<List<User>> userConsumer) {
        webClient.post()
                .uri("users")
                .bodyValue(user)
                .retrieve()
                .bodyToMono(User.class)
                .onErrorStop()
                .subscribe(newUser -> {
                    userList.add(newUser);
                    userConsumer.accept(userList);
                });

    }

    public void updateUser(User user, Consumer<List<User>> userConsumer) {
        webClient.put()
                .uri("users/" + user.getUserID())
                .bodyValue(user)
                .retrieve()
                .bodyToMono(User.class)
                .onErrorStop()
                .subscribe(newUser -> {
                    userList.replaceAll(oldUser -> oldUser.getUserID().equals(newUser.getUserID()) ? newUser : oldUser);
                    userConsumer.accept(userList);
                });

    }

    public void deleteUser(User user, Consumer<List<User>> userConsumer) {
        webClient.delete()
                .uri("users/" + user.getUserID())
                .retrieve()
                .toBodilessEntity()
                .onErrorStop()
                .subscribe(v -> {
                    userList.remove(user);
                    userConsumer.accept(userList);
                });

    }

    public void getAllUsers(Consumer<List<User>> userConsumer) {
        webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("users")
                        .queryParam("secret", "SecretKey")
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<User>>() {
                })
                .onErrorStop()
                .subscribe(newNotes -> {
                    userList.clear();
                    userList.addAll(newNotes);
                    userConsumer.accept(userList);
                });

    }
}
