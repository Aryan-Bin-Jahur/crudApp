package com.mine.crudApp.repository;

import com.mine.crudApp.entity.User;

import java.util.List;

public interface UserRepository {
     User saveUser(User user);

     User updateUser(User user);

     User getById(int id);

     String deleteByID(int id);

     List<User> allUsers();


}
