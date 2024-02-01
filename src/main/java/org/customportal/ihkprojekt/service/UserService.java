package org.customportal.ihkprojekt.service;

import org.customportal.ihkprojekt.dto.UserDto;
import org.customportal.ihkprojekt.model.User;
import org.customportal.ihkprojekt.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepo, ModelMapper userMapper){
        modelMapper = userMapper;
        userRepository = userRepo;
    }



    public UserDto convertToDto(User user){
        return modelMapper.map(user, UserDto.class);
    }

    public User convertToEntity(UserDto userDto){
        return modelMapper.map(userDto, User.class);
    }
    public Optional<UserDto> getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::convertToDto);
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

    public UserDto addUser(User user) {
        User savedUser = userRepository.save(user);

        return modelMapper.map(user, UserDto.class);
    }

    public List<UserDto> getAllUser() {
        List<UserDto> userList = userRepository.findAll()
                .stream().map(this::convertToDto).collect(Collectors.toList());

        return userList;
    }
}
