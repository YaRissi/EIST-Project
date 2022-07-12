package de.tum.in.ase.insertteamnamehere.rest;

import de.tum.in.ase.insertteamnamehere.service.UserService;
import de.tum.in.ase.insertteamnamehere.user.User;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam("secret") String secret) {
        if (!"SecretKey".equals(secret)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if (user.getUserID() != null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @PutMapping("users/{userID}")
    public ResponseEntity<User> updateNote(@RequestBody User updatedUser, @PathVariable("userID") UUID userID) {
        if (!updatedUser.getUserID().equals(userID)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(userService.saveUser(updatedUser));
    }

    @DeleteMapping("users/{userID}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userID") UUID userID) {
        userService.deleteUser(userID);
        return ResponseEntity.noContent().build();
    }
}
