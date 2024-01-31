package org.customportal.ihkprojekt.service;

import org.customportal.ihkprojekt.model.Customizing;
import org.customportal.ihkprojekt.model.Tag;
import org.customportal.ihkprojekt.repository.CustomizingRepository;
import org.customportal.ihkprojekt.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    private TagRepository tagRepository;

    private CustomizingRepository customizingRepository;


    @Autowired
    public TagService(TagRepository tagRepo, CustomizingRepository customRepo){
        tagRepository = tagRepo;
        customizingRepository = customRepo;
    }

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public Optional<Tag> getTagById(long id){
        return tagRepository.findById(id);
    }

    public Tag createTag(Tag tag){
       return tagRepository.save(tag);
    };

    public Tag addTagToCustomizing(long tagId, long customizingId){
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(()-> new RuntimeException("tag not found"));

        Customizing customizing = customizingRepository.findById(customizingId)
                .orElseThrow(()-> new RuntimeException("Customizing not found"));

        List<Tag> tags = customizing.getTags();
        if (tags == null){
            tags = new ArrayList<>();
            customizing.setTags(tags);
        }
        if (!tags.contains(tag)) {
            tags.add(tag);
        }
        customizingRepository.save(customizing);
        return tag;
    }

    public Tag saveNewTag(Tag tag) {
       return tagRepository.save(tag);
    }

    public ResponseEntity<Void> deleteTagById(long id){
        tagRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
