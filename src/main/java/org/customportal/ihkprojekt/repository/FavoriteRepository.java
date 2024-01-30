package org.customportal.ihkprojekt.repository;

import org.customportal.ihkprojekt.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Comment, Long> {
}
