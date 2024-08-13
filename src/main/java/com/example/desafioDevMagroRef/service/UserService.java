package com.example.desafioDevMagroRef.service;

import com.example.desafioDevMagroRef.Respositories.UserRepository;
import com.example.desafioDevMagroRef.dto.UserDTO;
import com.example.desafioDevMagroRef.exception.InvalidEmailException;
import com.example.desafioDevMagroRef.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User saveUser(UserDTO newUser) {
        if(userRepository.findByEmail(newUser.email()).isPresent()) {
            throw new InvalidEmailException("Esse usuário já foi criado");
        }
       return userRepository.save(new User(newUser));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void removeUser(String id) {
        var newId = Long.parseLong(id);
        userRepository.deleteById(newId);
    }
}
