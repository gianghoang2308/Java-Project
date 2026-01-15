package com.example.springapi09.dto.Comment;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentCreate {


    @NotNull(message="Comment khong duoc de trong")
    private String comment;

    private Integer rate;
}
