package com.example.demo.Service;


import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public void saveUser(User user) {userRepo.save(user);}

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserByName(String name) {
        return userRepo.findByName(name);
    }

    public void addMarks(String name, Integer marks) {
        User user = userRepo.findByName(name);
        if (user != null) {
            user.setMarks(marks);
            userRepo.save(user);
        }
    }

}
