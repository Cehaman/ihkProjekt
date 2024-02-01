package org.customportal.ihkprojekt.service;

import org.customportal.ihkprojekt.dto.FavoriteDto;
import org.customportal.ihkprojekt.dto.TagDto;
import org.customportal.ihkprojekt.model.Customizing;
import org.customportal.ihkprojekt.model.Tag;
import org.customportal.ihkprojekt.repository.CustomizingRepository;
import org.customportal.ihkprojekt.repository.TagRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TagService {

    private TagRepository tagRepository;

    private CustomizingRepository customizingRepository;

    private ModelMapper modelMapper;


    @Autowired
    public TagService(TagRepository tagRepo, CustomizingRepository customRepo, ModelMapper favMapper){
        tagRepository = tagRepo;
        customizingRepository = customRepo;
        modelMapper = favMapper;
    }

    public TagDto convertToDto(Tag tag){
        return modelMapper.map(tag, TagDto.class);
    }

    public Tag convertToEntity(TagDto tag) {
        return modelMapper.map(tag, Tag.class);
    }


    public List<TagDto> getAllTags() {
        return tagRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<TagDto> getTagById(long id){
        return tagRepository.findById(id)
                .map(this::convertToDto);
    }


    public TagDto addTagToCustomizing(long tagId, long customizingId){
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
        return modelMapper.map(tag, TagDto.class);
    }

    public TagDto saveNewTag(TagDto tagDto) {
        Tag tag = modelMapper.map(tagDto, Tag.class);
        Tag newTag = tagRepository.save(tag);
       return modelMapper.map(newTag, TagDto.class);
    }

    public ResponseEntity<Void> deleteTagById(long id){
        tagRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
