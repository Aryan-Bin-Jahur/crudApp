package com.mine.crudApp.controller;

import com.mine.crudApp.entity.User;
import com.mine.crudApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/user")
    public User addUser(@RequestBody User user){
        return userRepository.saveUser(user);
    }

    @PutMapping("/user")
    public User updateUser(@RequestBody User user){
        return userRepository.updateUser(user);
    }

    @GetMapping("/users")
//    public List<User> allUsers(@RequestBody List<User> users) was wrong cause no parameter was needed... LOL
    public List<User> allUsers(){
        return userRepository.allUsers();
    }

    @RequestMapping("/user/{id}")
    public String removeUser(@PathVariable("id") int id){
        return userRepository.deleteByID(id);
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") int id){
        return userRepository.getById(id);
    }


}
