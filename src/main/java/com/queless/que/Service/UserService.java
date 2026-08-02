package com.queless.que.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.queless.que.Entity.User;
import com.queless.que.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    // Save User
    public User saveUser(User user) {
        return repository.save(user);
    }

    // Get All Users
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    // Get User By ID
    public User getUserById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    // Update User
    public User updateUser(User user) {
        return repository.save(user);
    }

    // Delete User
    public void deleteUser(Integer id) {
        repository.deleteById(id);
    }
}