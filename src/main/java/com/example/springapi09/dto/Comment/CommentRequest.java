package com.example.springapi09.dto.Comment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {

    @NotNull(message = "Ho va ten khong duoc de trong")
    private String fullName;

    @NotNull(message = "Email khong duoc de trong")
    @Email(message = "Nhap dung dinh dang email")
    private String email;

    @NotNull(message = "Phan binh luan khong duoc de trong")
    private String comment;

    private Integer rate;

    private Integer productId;

    private Integer userId;
}
