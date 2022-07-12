package de.tum.in.ase.insertteamnamehere.service;

import de.tum.in.ase.insertteamnamehere.user.User;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final List<User> userList;

    public UserService() {
        this.userList = new ArrayList<>();
    }

    public User saveUser(User user) {
        var optionalUser = userList.stream().filter(existingUser -> existingUser.getUserID().equals(user.getUserID())).findFirst();
        if (optionalUser.isEmpty()) {
            user.setUserID(UUID.randomUUID());
            userList.add(user);
            return user;
        } else {
            var existingUser = optionalUser.get();

            return existingUser;
        }
    }

    public void deleteUser(UUID userID) {
        userList.removeIf(user -> user.getUserID().equals(userID));

    }

    public List<User> getAllUsers() {
        return Collections.unmodifiableList(userList);
    }
}
