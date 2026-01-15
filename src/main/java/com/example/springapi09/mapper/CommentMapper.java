package com.example.springapi09.mapper;

import java.util.List;

import com.example.springapi09.dto.Comment.CommentUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.springapi09.dto.Comment.CommentRequest;
import com.example.springapi09.dto.Comment.CommentResponse;
import com.example.springapi09.entity.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "user", ignore = true)
    Comment toComment(CommentRequest request);

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.name", target = "name")
    CommentResponse toResponse(Comment comment);

    List<CommentResponse> toResponseList(List<Comment> entities);

    // Update Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateEntity(CommentUpdate request, @MappingTarget Comment entity);
}
