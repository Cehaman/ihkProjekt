package org.customportal.ihkprojekt.repository;

import org.customportal.ihkprojekt.model.Comment;
import org.customportal.ihkprojekt.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    public List<Favorite> findAllByUserId(long userid);
    public Optional<Favorite> findByIdAndUserId(Long favoriteId, Long userId);


}
