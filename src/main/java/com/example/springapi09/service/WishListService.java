package com.example.springapi09.service;

import java.util.List;

import com.example.springapi09.dto.WishList.WishListRequest;
import com.example.springapi09.dto.WishList.WishListResponse;

public interface WishListService {
    List<WishListResponse> getAllWishLists();
    WishListResponse findById(Integer id);
    String create(WishListRequest wishListRequest);
    String update(WishListRequest wishListRequest, Integer id);
    String delete(Integer id);
}
