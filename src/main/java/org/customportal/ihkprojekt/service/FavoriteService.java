package org.customportal.ihkprojekt.service;

import org.customportal.ihkprojekt.model.Customizing;
import org.customportal.ihkprojekt.model.Favorite;
import org.customportal.ihkprojekt.model.User;
import org.customportal.ihkprojekt.repository.CustomizingRepository;
import org.customportal.ihkprojekt.repository.FavoriteRepository;
import org.customportal.ihkprojekt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteService {
    private FavoriteRepository favoriteRepository;
    private UserRepository userRepository;

    private CustomizingRepository customizingRepository;

    @Autowired
    public FavoriteService( FavoriteRepository favoriteRepo, UserRepository userRepo, CustomizingRepository customRepo){
        favoriteRepository = favoriteRepo;
        userRepository = userRepo;
        customizingRepository = customRepo;
    }

    public List<Favorite> getAllUsersFavorites(long userid){
        return favoriteRepository.findAllByUserId(userid);
    }

    public Optional<Favorite> getFavoriteByUserIdAndId(long userId, long favoriteId){
        return favoriteRepository.findByIdAndUserId(userId, favoriteId);
    }

    public Favorite addFavorite(long userId, long customizingId){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found"));

        Customizing customizing = customizingRepository.findById(customizingId)
                .orElseThrow(() -> new RuntimeException("Customizing not found"));

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setCustomizing(customizing);
        return favoriteRepository.save(favorite);
    }

}
