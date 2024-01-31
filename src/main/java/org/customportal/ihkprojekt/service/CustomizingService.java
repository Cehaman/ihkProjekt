package org.customportal.ihkprojekt.service;

import org.customportal.ihkprojekt.model.Comment;
import org.customportal.ihkprojekt.model.Customizing;
import org.customportal.ihkprojekt.model.Tag;
import org.customportal.ihkprojekt.model.User;
import org.customportal.ihkprojekt.repository.CustomizingRepository;
import org.customportal.ihkprojekt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomizingService {

    private CustomizingRepository customizingRepository;

    private UserRepository userRepository;

    @Autowired
    public CustomizingService(CustomizingRepository customRepo, UserRepository userRepo) {
        customizingRepository = customRepo;
        userRepository = userRepo;
    }

    public List<Customizing> getAllCustomizings() {
        return customizingRepository.findAll();
    }

    public Optional<Customizing> getCustomizingById(long id){
        return customizingRepository.findById(id);
    }

    public Customizing addNewCustomizing(long id, String content, String title, List<Tag> tags, List<Comment> comments){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User not found"));

        Customizing customizing = new Customizing();
        customizing.setUser(user);
        customizing.setContent(content);
        customizing.setTitel(title);
        customizing.setTags(tags);
        customizing.setComments(comments);
        return customizingRepository.save(customizing);
    }

    public void deleteCustomizingById(Long id){
        customizingRepository.deleteById(id);
    }
}
