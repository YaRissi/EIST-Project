package de.tum.in.ase.insertteamnamehere.service;

import de.tum.in.ase.insertteamnamehere.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserService {
    private final List<User> userList;

    public UserService() {
        this.userList = new ArrayList<>();
    }
    public User saveUser(User user){
        return null;

    }
    public void deleteUser(UUID userID){

    }
    public List<User > getAllUsers(){
        return null;
    }
}
