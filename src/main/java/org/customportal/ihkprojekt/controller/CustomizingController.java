package org.customportal.ihkprojekt.controller;


import org.apache.coyote.Response;
import org.customportal.ihkprojekt.model.Customizing;
import org.customportal.ihkprojekt.service.CustomizingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customizings")
public class CustomizingController {

    private CustomizingService customizingService;

    @Autowired
    public CustomizingController(CustomizingService customservice){
        customizingService = customservice;
    }

    @GetMapping("/all")
    public List<Customizing> getAllCustomizings(){
        return customizingService.getAllCustomizings();
    }

    @GetMapping("/{id}")
    public Optional<Customizing> getCustomizingById(@PathVariable Long id){
        return customizingService.getCustomizingById(id);
    }

    @PostMapping("/{id}/add")
    public ResponseEntity<Customizing> addCustomizing(@PathVariable long id, @RequestBody Customizing customizing){
        Customizing custom = customizingService.addNewCustomizing(
                id,
                customizing.getTitel(),
                customizing.getContent(),
                customizing.getTags(),
                customizing.getComments()
        );
        return ResponseEntity.ok(custom);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCustomizing(@PathVariable Long id){
        customizingService.deleteCustomizingById(id);
        return ResponseEntity.ok().build();
    }
}
