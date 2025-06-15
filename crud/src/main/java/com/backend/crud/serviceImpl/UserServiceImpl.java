package com.backend.crud.serviceImpl;

import com.backend.crud.model.User;
import com.backend.crud.repository.UserRepository;
import com.backend.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        User usr = new User();
        usr.setName(user.getName());
        usr.setEmail(user.getEmail());
        usr.setMobile(user.getMobile());
        return userRepository.save(usr);
    }

    @Override
    public User updateUserById(User user, String id) {
        Optional<User> userIsFound = userRepository.findById(id);
        if(userIsFound.isPresent()){
            User usr = userIsFound.get();
            usr.setName(user.getName());
            usr.setEmail(user.getEmail());
            usr.setMobile(user.getMobile());
            return userRepository.save(usr);
        }
        return null;
    }

    @Override
    public Optional<User> getUserById(String id) {
        Optional<User> userIsFound = userRepository.findById(id);
        if(userIsFound.isPresent()){
            return userIsFound;
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteUserById(String id) {
        Optional<User> userIsFound = userRepository.findById(id);
        if(userIsFound.isPresent()){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
