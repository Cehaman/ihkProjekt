package org.customportal.ihkprojekt.service;

import org.customportal.ihkprojekt.dto.CommentDto;
import org.customportal.ihkprojekt.model.Comment;
import org.customportal.ihkprojekt.model.Customizing;
import org.customportal.ihkprojekt.model.User;
import org.customportal.ihkprojekt.repository.CommentRepository;
import org.customportal.ihkprojekt.repository.CustomizingRepository;
import org.customportal.ihkprojekt.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private UserRepository userRepository;

    private CustomizingRepository customizingRepository;

    private ModelMapper modelMapper;

    @Autowired
    public CommentService(CommentRepository comRepo, UserRepository userRepo, CustomizingRepository customizingRepo, ModelMapper modelMapping){
        commentRepository = comRepo;
        userRepository = userRepo;
        customizingRepository = customizingRepo;
        modelMapper = modelMapping;
    }


    public CommentDto convertToDto(Comment comment){
        return modelMapper.map(comment, CommentDto.class);
    }

    public Comment convertToEntity(CommentDto commentDto){
        return modelMapper.map(commentDto, Comment.class);
    }


    public List<CommentDto> getAllComments() {
        return commentRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    };

    public List<CommentDto> getAllCommentsByCustomizingId(Long id){
        return commentRepository.getCommentsByCustomizingId(id)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<CommentDto> getCommandById(long id) {
        return commentRepository.findById(id)
                .map(this::convertToDto);
    }


    public CommentDto createComment(long userid, long customizingid, String title, String content) {
        User user = userRepository.findById(userid)
                .orElseThrow(()-> new RuntimeException("User not found"));

        Customizing customizing = customizingRepository.findById(customizingid)
                .orElseThrow(() -> new RuntimeException("Customizing not found"));

        Comment comment = new Comment();
        comment.setUser(user);
        comment.setTitle(title);
        comment.setContent(content);
        comment.setCustomizing(customizing);
        Comment saveCommend = commentRepository.save(comment);
        return modelMapper.map(saveCommend,CommentDto.class);
    }

    public ResponseEntity<Void> deleteCommentById(long id) {
        commentRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
