package com.example.desafioDevMagroRef.Controller;

import com.example.desafioDevMagroRef.Respositories.UserRepository;
import com.example.desafioDevMagroRef.dto.UserDTO;
import com.example.desafioDevMagroRef.model.User;
import com.example.desafioDevMagroRef.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @Transactional
    public ResponseEntity<Void> postUser(@RequestBody UserDTO newUser) {
        userService.saveUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }
}
