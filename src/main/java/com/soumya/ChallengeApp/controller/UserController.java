package com.soumya.ChallengeApp.controller;

import com.soumya.ChallengeApp.entity.ChallengeEntity;
import com.soumya.ChallengeApp.entity.User;
import com.soumya.ChallengeApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/create")
    public boolean createUser(@RequestBody User user) {
        userService.saveUser(user);
        return true;
    }

    @PutMapping("/update/{username}")
    public ResponseEntity<User> updateChallengeEntry(@RequestBody User user, @PathVariable String username) {
        User userInDB = userService.findByUserName(username);
        if (userInDB != null) {
            userInDB.setUsername(user.getUsername());
            userInDB.setPassword(user.getPassword());
            User updatedUser = userService.saveUser(userInDB);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
