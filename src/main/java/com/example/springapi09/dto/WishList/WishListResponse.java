package com.example.springapi09.dto.WishList;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class WishListResponse {
    private Integer id;

    private Integer userId;
    private String userName;

    private Integer productId;
    private String productName;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
