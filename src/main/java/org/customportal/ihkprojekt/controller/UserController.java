package org.customportal.ihkprojekt.controller;

import org.customportal.ihkprojekt.dto.UserDto;
import org.customportal.ihkprojekt.model.User;
import org.customportal.ihkprojekt.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userServ){
        userService = userServ;
    }

    @GetMapping("/all")
    public List<UserDto> getAllUsers(){
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public UserDto createUser(@RequestBody User user) {
        return userService.addUser(user);
    }


    @DeleteMapping("/{userid}/delete")
    public void deleteUserById(@PathVariable Long userid){
        userService.deleteUserById(userid);
    }
}
