package com.example.springapi09.controller;

import com.example.springapi09.dto.Comment.CommentCreate;
import com.example.springapi09.dto.Comment.CommentRequest;
import com.example.springapi09.dto.Comment.CommentResponse;
import com.example.springapi09.dto.Comment.CommentUpdate;
import com.example.springapi09.response.ResponseHandle;
import com.example.springapi09.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/")
    public ResponseEntity<Object> getAll() {
        List<CommentResponse> commentResponses = commentService.getAllComments();
        return ResponseHandle.responseBuilder("Successfully fetch comments from the database", null, HttpStatus.OK, commentResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCommentById(@PathVariable("id") Integer id) {
        CommentResponse commentResponse = commentService.findById(id);
        return ResponseHandle.responseBuilder("Successfully fetch comment from the database", null, HttpStatus.OK, commentResponse);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createComment(@RequestBody CommentRequest commentRequest) {
        String message = commentService.create(commentRequest);
        return ResponseHandle.responseBuilder("Successfully created the comment", null, HttpStatus.OK, message);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateComment(@PathVariable("id") Integer id, @RequestBody CommentUpdate commentUpdate) {
        String message = commentService.update(commentUpdate, id);
        return ResponseHandle.responseBuilder("Successfully updated the comment", null, HttpStatus.OK, message);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteComment(@PathVariable("id") Integer id) {
        String message = commentService.delete(id);
        return ResponseHandle.responseBuilder("Successfully deleted the comment", null, HttpStatus.OK, message);
    }

    @PostMapping("/products/{productId}/comments")
    public ResponseEntity<Object> createComment(
            @PathVariable Integer productId,
            @RequestBody @Valid CommentCreate request) {

        return ResponseHandle.responseBuilder(
                "Create comment",
                null,
                HttpStatus.OK,
                commentService.createComment(productId, request)
        );
    }


}
