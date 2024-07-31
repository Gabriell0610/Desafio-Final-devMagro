package com.example.desafioDevMagroRef.model;

import com.example.desafioDevMagroRef.dto.UserDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private String email;
    private double heigth;

    public User() {}

    public User(UserDTO newUser) {
        this.name = newUser.name();
        this.age = newUser.age();
        this.email = newUser.email();
        this.heigth = newUser.heigth();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getHeigth() {
        return heigth;
    }

    public void setHeigth(double heigth) {
        this.heigth = heigth;
    }

    @Override
    public String toString() {
        return this.name + "\n" + this.age + "\n" + this.email + "\n" + this.heigth;
    }
}
