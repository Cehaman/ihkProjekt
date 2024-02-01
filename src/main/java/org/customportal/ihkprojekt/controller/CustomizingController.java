package org.customportal.ihkprojekt.controller;


import org.apache.coyote.Response;
import org.customportal.ihkprojekt.dto.CustomizingDto;
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
    public List<CustomizingDto> getAllCustomizings(){
        return customizingService.getAllCustomizings();
    }

    @GetMapping("/{id}")
    public Optional<CustomizingDto> getCustomizingById(@PathVariable Long id){
        return customizingService.getCustomizingById(id);
    }

    @PostMapping("/{id}/add")
    public ResponseEntity<CustomizingDto> addCustomizing(@PathVariable long id, @RequestBody CustomizingDto customizingDto){

        CustomizingDto savedCustomDto =  customizingService.addNewCustomizing(id, customizingDto.getTitle(), customizingDto.getContent());
        return ResponseEntity.ok(savedCustomDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCustomizing(@PathVariable Long id){
        customizingService.deleteCustomizingById(id);
        return ResponseEntity.ok().build();
    }
}
