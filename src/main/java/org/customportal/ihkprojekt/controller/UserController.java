package org.customportal.ihkprojekt.controller;

import org.customportal.ihkprojekt.model.User;
import org.customportal.ihkprojekt.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userServ){
        userService = userServ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public User createUser(@RequestBody User user) {
        return userService.addUser(user);
    }
}
