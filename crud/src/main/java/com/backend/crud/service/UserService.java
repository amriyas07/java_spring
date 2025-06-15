package com.backend.crud.service;

import com.backend.crud.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<User> getAllUsers();
    public User createUser(User user);
    public User updateUserById(User user , String id);
    public Optional<User> getUserById(String id);
    public boolean deleteUserById(String id);
}
