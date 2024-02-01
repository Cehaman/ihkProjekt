package org.customportal.ihkprojekt.controller;

import org.customportal.ihkprojekt.dto.CustomizingDto;
import org.customportal.ihkprojekt.dto.FavoriteDto;
import org.customportal.ihkprojekt.model.Customizing;
import org.customportal.ihkprojekt.model.Favorite;
import org.customportal.ihkprojekt.service.CustomizingService;
import org.customportal.ihkprojekt.service.FavoriteService;
import org.customportal.ihkprojekt.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {

    private FavoriteService favoriteService;
    private UserService userService;

    private CustomizingService customizingService;



    @Autowired
    public FavoriteController(FavoriteService favservice, UserService useService, CustomizingService customService){
        favoriteService = favservice;
        userService = useService;
        customizingService = customService;
    };

    @GetMapping("/all/{userid}")
    public List<FavoriteDto> getAllFavoritesByUserId(@PathVariable Long userid){
        return favoriteService.getAllUsersFavorites(userid);
    }

    @GetMapping("/{userId}/{favoriteId}")
    public Optional<FavoriteDto> getFavoriteByUserId(@PathVariable Long userId, @PathVariable Long favoriteId){
        return favoriteService.getFavoriteByUserIdAndId(userId, favoriteId);

    }

    @DeleteMapping("/{userId}/{favoriteId}")
    public ResponseEntity<Void> deleteUserFavoriteByIds(@PathVariable long userId, @PathVariable Long favoriteId){
        favoriteService.getFavoriteByUserIdAndId(userId, favoriteId);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/{useriId}/add")
    public ResponseEntity<FavoriteDto> addFavorite(@PathVariable Long useriId, @RequestBody FavoriteDto favoriteDto){


        FavoriteDto saveFavorite = favoriteService.addFavorite(useriId, favoriteDto.getCustomizingId());
        return ResponseEntity.ok(saveFavorite);
    }
}

