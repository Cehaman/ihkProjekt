package org.customportal.ihkprojekt.service;

import org.customportal.ihkprojekt.model.User;
import org.customportal.ihkprojekt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepo){
        userRepository = userRepo;
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }
}
