package org.customportal.ihkprojekt.service;

import org.customportal.ihkprojekt.dto.CommentDto;
import org.customportal.ihkprojekt.dto.CustomizingDto;
import org.customportal.ihkprojekt.model.Comment;
import org.customportal.ihkprojekt.model.Customizing;
import org.customportal.ihkprojekt.model.Tag;
import org.customportal.ihkprojekt.model.User;
import org.customportal.ihkprojekt.repository.CustomizingRepository;
import org.customportal.ihkprojekt.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomizingService {

    private CustomizingRepository customizingRepository;

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Autowired
    public CustomizingService(CustomizingRepository customRepo, UserRepository userRepo, ModelMapper modelMapping) {
        customizingRepository = customRepo;
        userRepository = userRepo;
        modelMapper = modelMapping;
    }

    public CustomizingDto convertToDto(Customizing customizing){
        return modelMapper.map(customizing, CustomizingDto.class);
    }

    public Customizing convertDtoToEntity(CustomizingDto customizingDto){
        return modelMapper.map(customizingDto, Customizing.class);
    }

    public List<CustomizingDto> getAllCustomizings() {
        return customizingRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<CustomizingDto> getCustomizingById(long id){
        return customizingRepository.findById(id)
                .map(this::convertToDto);
    }

    public CustomizingDto addNewCustomizing(long id, String title, String content){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User not found"));

        Customizing customizing = new Customizing();
        customizing.setUser(user);
        customizing.setTitel(title);
        customizing.setContent(content);
        Customizing savedCustomizing = customizingRepository.save(customizing);
        return modelMapper.map(savedCustomizing, CustomizingDto.class);
    }

    public void deleteCustomizingById(Long id){
        customizingRepository.deleteById(id);
    }
}
