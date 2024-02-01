package org.customportal.ihkprojekt.controller;


import org.customportal.ihkprojekt.dto.TagDto;
import org.customportal.ihkprojekt.model.Customizing;
import org.customportal.ihkprojekt.model.Tag;
import org.customportal.ihkprojekt.service.CustomizingService;
import org.customportal.ihkprojekt.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tags")
public class TagController {

    private TagService tagService;
    private CustomizingService customizingService;

    @Autowired
    public TagController(TagService tagServ, CustomizingService customService) {
        tagService = tagServ;
        customizingService = customService;
    }

    @GetMapping("/all")
    public List<TagDto> getAllTags() {
        return tagService.getAllTags();
    }

    @GetMapping("/{tagid}")
    public Optional<TagDto> getTagById(@PathVariable long tagid){
        return tagService.getTagById(tagid);
    }

    @PostMapping("/add")
    public ResponseEntity<Tag> addNewTag(@RequestBody TagDto tagDto) {
        tagService.saveNewTag(tagDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{tagid}/{customizingId}")
    public TagDto addTagToCustomizing(@PathVariable long tagid, @PathVariable long customizingId){
       return tagService.addTagToCustomizing(tagid, customizingId);
    }

}
