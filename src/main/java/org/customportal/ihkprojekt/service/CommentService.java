package org.customportal.ihkprojekt.service;

import org.customportal.ihkprojekt.model.Comment;
import org.customportal.ihkprojekt.model.Customizing;
import org.customportal.ihkprojekt.model.User;
import org.customportal.ihkprojekt.repository.CommentRepository;
import org.customportal.ihkprojekt.repository.CustomizingRepository;
import org.customportal.ihkprojekt.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private UserRepository userRepository;

    private CustomizingRepository customizingRepository;

    public CommentService(CommentRepository comRepo, UserRepository userRepo, CustomizingRepository customizingRepo){
        commentRepository = comRepo;
        userRepository = userRepo;
        customizingRepository = customizingRepo;
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    };

    public Optional<Comment> getCommandById(long id) {
        return commentRepository.findById(id);
    }
    public Comment createComment(long userid, long customizingid, String title, String content) {
        User user = userRepository.findById(userid)
                .orElseThrow(()-> new RuntimeException("User not found"));

        Customizing customizing = customizingRepository.findById(customizingid)
                .orElseThrow(() -> new RuntimeException("Customizing not found"));

        Comment comment = new Comment();
        comment.setUser(user);
        comment.setTitle(title);
        comment.setContent(content);
        comment.setCustomizing(customizing);
        return commentRepository.save(comment);
    }

    public ResponseEntity<Void> deleteCommentById(long id) {
        ;
        return ResponseEntity.ok().build();
    }
}
