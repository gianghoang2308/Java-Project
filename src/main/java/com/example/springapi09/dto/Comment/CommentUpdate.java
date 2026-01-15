package com.example.springapi09.dto.Comment;

import jakarta.validation.constraints.Email;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentUpdate {
    private String fullName;

    @Email(message = "Nhap dung dinh dang email")
    private String email;

    private String comment;

    private Integer rate;

    private Integer productId;

    private Integer userId;
}
