package org.customportal.ihkprojekt.service;

import org.customportal.ihkprojekt.dto.FavoriteDto;
import org.customportal.ihkprojekt.model.Customizing;
import org.customportal.ihkprojekt.model.Favorite;
import org.customportal.ihkprojekt.model.User;
import org.customportal.ihkprojekt.repository.CustomizingRepository;
import org.customportal.ihkprojekt.repository.FavoriteRepository;
import org.customportal.ihkprojekt.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FavoriteService {
    private FavoriteRepository favoriteRepository;
    private UserRepository userRepository;

    private CustomizingRepository customizingRepository;

    private ModelMapper modelMapper;

    public FavoriteDto convertToDto(Favorite favorite){
        return modelMapper.map(favorite, FavoriteDto.class);
    }

    public Favorite convertToEntity(FavoriteDto favoriteDto){
        return modelMapper.map(favoriteDto, Favorite.class);
    }

    @Autowired
    public FavoriteService( FavoriteRepository favoriteRepo, UserRepository userRepo, CustomizingRepository customRepo, ModelMapper modelMap){
        favoriteRepository = favoriteRepo;
        userRepository = userRepo;
        customizingRepository = customRepo;
        modelMapper = modelMap;
    }

    public List<FavoriteDto> getAllUsersFavorites(long userid){
        return favoriteRepository.findAllByUserId(userid)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<FavoriteDto> getFavoriteByUserIdAndId(long userId, long favoriteId){
        return favoriteRepository.findByFavoriteIdAndUserId(userId, favoriteId)
                .map(this::convertToDto);
    }

    public FavoriteDto addFavorite(long userId, long customizingId){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found"));

        Customizing customizing = customizingRepository.findById(customizingId)
                .orElseThrow(() -> new RuntimeException("Customizing not found"));

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setCustomizing(customizing);
        Favorite savedFavorite = favoriteRepository.save(favorite);
        return modelMapper.map(favorite, FavoriteDto.class);
    }

    public void deleteFavoriteByIds(Long userId, Long favoriteId){
        favoriteRepository.deleteFavoriteByUserIdAndFavoriteId(userId, favoriteId);
    }

}
