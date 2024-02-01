package org.customportal.ihkprojekt.controller;


import org.customportal.ihkprojekt.dto.CommentDto;
import org.customportal.ihkprojekt.model.Comment;
import org.customportal.ihkprojekt.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService comservice){
        commentService = comservice;
    }

    @GetMapping("/customizings/{customizingid}")
    public List<CommentDto> getAllCommentsByCustomizingId(@PathVariable Long customizingid){
        return commentService.getAllCommentsByCustomizingId(customizingid);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable long id){
        return commentService.getCommandById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{userid}/{customizingid}/add")
    public CommentDto createComment(@PathVariable long userid,@PathVariable long customizingid, @RequestBody CommentDto commentDto){
        return commentService.createComment(userid, customizingid, commentDto.getTitle(), commentDto.getContent());
    }

    @DeleteMapping("/{commentid}")
    public ResponseEntity<Void> deleteComment(@PathVariable long commentid) {
        commentService.deleteCommentById(commentid);
        return ResponseEntity.ok().build();
    }

}
