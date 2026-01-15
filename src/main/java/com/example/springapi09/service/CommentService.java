package com.example.springapi09.service;

import java.util.List;

import com.example.springapi09.dto.Comment.CommentCreate;
import com.example.springapi09.dto.Comment.CommentRequest;
import com.example.springapi09.dto.Comment.CommentResponse;
import com.example.springapi09.dto.Comment.CommentUpdate;

public interface CommentService {
    List<CommentResponse> getAllComments();

    CommentResponse findById(Integer id);

    String create(CommentRequest commentRequest);

    String update(CommentUpdate commentUpdate, Integer id);

    String delete(Integer id);

    String createComment(Integer productId, CommentCreate request);

}
