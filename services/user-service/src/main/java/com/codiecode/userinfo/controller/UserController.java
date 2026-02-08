package com.codiecode.userinfo.controller;

import com.codiecode.common.dto.UserDTO;
import com.codiecode.userinfo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/fetchAllUsers")
    public ResponseEntity<List<UserDTO>> fetchAllUsers() {
        List<UserDTO> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/addUser")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO user) {
        UserDTO savedUser = userService.addUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/fetchById/{userId}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable Long userId) {
        UserDTO user = userService.fetchUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
