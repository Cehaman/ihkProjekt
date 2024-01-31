package org.customportal.ihkprojekt.controller;

import org.customportal.ihkprojekt.model.Favorite;
import org.customportal.ihkprojekt.service.CustomizingService;
import org.customportal.ihkprojekt.service.FavoriteService;
import org.customportal.ihkprojekt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Favorite> getAllFavoritesByUserId(@PathVariable Long userId){
        return favoriteService.getAllUsersFavorites(userId);
    }

    @GetMapping("/{userId}/{favoriteId}")
    public Optional<Favorite> getFavoriteByUserId(@PathVariable Long userId, @PathVariable Long favoriteId){
        return favoriteService.getFavoriteByUserIdAndId(userId, favoriteId);

    }


}
