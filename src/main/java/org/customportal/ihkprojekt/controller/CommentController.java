package org.customportal.ihkprojekt.controller;


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

    @GetMapping("/all")
    public List<Comment> getAllComments(){
        return commentService.getAllComments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable long id){
        return commentService.getCommandById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/{userid}/{customizingid}/add", consumes = "application/json")
    public Comment createComment(@PathVariable long userid,@PathVariable long customizingid, @RequestBody Comment comment){
        return commentService.createComment(userid, customizingid, comment.getTitle(), comment.getContent());
    }

    @DeleteMapping("/{commentid}")
    public ResponseEntity<Void> deleteComment(@PathVariable long id) {
        commentService.deleteCommentById(id);
        return ResponseEntity.ok().build();
    }

}
