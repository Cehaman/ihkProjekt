package org.customportal.ihkprojekt.repository;

import org.customportal.ihkprojekt.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    public List<Comment> getCommentsByCustomizingId(Long id);
}
