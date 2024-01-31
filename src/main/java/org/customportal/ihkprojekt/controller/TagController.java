package org.customportal.ihkprojekt.controller;


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
    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }

    @GetMapping("/{tagid}")
    public Optional<Tag> getTagById(@PathVariable long id){
        return tagService.getTagById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Tag> addNewTag(@RequestBody Tag tag) {
        tagService.saveNewTag(tag);
        return ResponseEntity.ok(tag);
    }

    @PostMapping("/{tagid}/{customizingId}")
    public Tag addTagToCustomizing(@PathVariable long tagid, @PathVariable long customizingId){
       return tagService.addTagToCustomizing(tagid, customizingId);
    }

}
