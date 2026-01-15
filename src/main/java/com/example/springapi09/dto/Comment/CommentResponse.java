package com.example.springapi09.dto.Comment;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponse {

    private String fullName;

    private String email;

    private String comment;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer rate;

    private Integer productId;
    private String productName;

    private Integer userId;

    private String name;
}
