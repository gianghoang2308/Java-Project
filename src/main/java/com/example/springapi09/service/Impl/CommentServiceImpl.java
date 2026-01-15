package com.example.springapi09.service.Impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.example.springapi09.dto.Comment.CommentCreate;
import com.example.springapi09.dto.Comment.CommentUpdate;
import com.example.springapi09.entity.Product;
import com.example.springapi09.entity.User;
import com.example.springapi09.repository.ProductRepository;
import com.example.springapi09.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springapi09.dto.Comment.CommentRequest;
import com.example.springapi09.dto.Comment.CommentResponse;
import com.example.springapi09.entity.Comment;
import com.example.springapi09.exception.NotFoundException;
import com.example.springapi09.mapper.CommentMapper;
import com.example.springapi09.repository.CommentRepository;
import com.example.springapi09.service.CommentService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<CommentResponse> getAllComments() {
        List<Comment> comments = commentRepository.findAll();

        if (comments.isEmpty()) {
            throw new NotFoundException("Data is not null!");
        }

        return commentMapper.toResponseList(comments);
    }

    @Override
    public CommentResponse findById(Integer id) {
        Optional<Comment> comment = commentRepository.findById(Objects.requireNonNull(id));
        if (comment.isEmpty()) {
            throw new NotFoundException("Data cannot be null!");
        }
        return commentMapper.toResponse(comment.get());
    }

    @Override
    public String create(CommentRequest commentRequest) {
        if (commentRequest == null) {
            throw new NotFoundException("Data cannot be null!");
        }

        Product product = productRepository.findById(commentRequest.getProductId())
                .orElseThrow(() -> new NotFoundException("Product not found"));
        Comment create = commentMapper.toComment(commentRequest);

        User user = userRepository.findById(commentRequest.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found"));
        Comment comment = commentMapper.toComment(commentRequest);

        comment.setProduct(product);
        comment.setUser(user);

        commentRepository.save(comment);
        return "successfully create new comment!";
    }

    @Override
    public String update(CommentUpdate commentUpdate, Integer id) {
        if (commentUpdate == null) {
            throw new IllegalArgumentException("Update data must not be null");
        }

        if (id == null) {
            throw new NotFoundException("Id cannot be null");
        }
        Comment exisiting = commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cannot find comment with id: " + id));

        commentMapper.updateEntity(commentUpdate, exisiting);
        return "Successfully update comment with id: " + id;
    }

    @Override
    public String delete(Integer id) {
        Optional<Comment> comment = commentRepository.findById(Objects.requireNonNull(id));
        if (comment.isEmpty()) {
            throw new NotFoundException("Data cannot be null!");
        }
        commentRepository.deleteById(id);
        return "successfully delete comment with id: " + id;
    }

    private User getCurrentUser() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new NotFoundException("User not authenticated");
        }

        String username = authentication.getName(); // name khi login

        return userRepository.findByName(username)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }


    @Override
    public String createComment(Integer productId, CommentCreate request) {

        // 1. Lấy user đang login
        User user = getCurrentUser();

        // 2. Lấy product theo link
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found"));

        // 3. Tạo comment
        Comment comment = new Comment();
        comment.setComment(request.getComment());
        comment.setRate(request.getRate());

        // 4. Tự động điền từ user
        comment.setUser(user);
        comment.setFullName(user.getFullName());
        comment.setEmail(user.getEmail());

        // 5. Gán product
        comment.setProduct(product);

        commentRepository.save(comment);

        return "Create comment successfully";
    }

}
