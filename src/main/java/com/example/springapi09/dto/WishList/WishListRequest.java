package com.example.springapi09.dto.WishList;

import com.example.springapi09.entity.Product;
import com.example.springapi09.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class WishListRequest {

    @JoinColumn(name = "user_id")
    private User user;

    @JoinColumn(name = "product_id")
    private Product product;


}
