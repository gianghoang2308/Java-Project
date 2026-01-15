package com.example.springapi09.controller;

import com.example.springapi09.dto.WishList.WishListRequest;
import com.example.springapi09.dto.WishList.WishListResponse;
import com.example.springapi09.response.ResponseHandle;
import com.example.springapi09.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishListController {

    @Autowired
    private WishListService wishListService;

    @GetMapping("/")
    public ResponseEntity<Object> getAll() {
        List<WishListResponse> wishListResponseList = wishListService.getAllWishLists();
        return ResponseHandle.responseBuilder("Successfully fetch wishlists from the database!", null, HttpStatus.OK, wishListResponseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getWishListById(@PathVariable Integer id) {
        WishListResponse wishListResponse = wishListService.findById(id);
        return ResponseHandle.responseBuilder("Successfully fetch a wish list detail from the database!", null, HttpStatus.OK, wishListResponse);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody  WishListRequest wishListRequest) {
        String message = wishListService.create(wishListRequest);
        return ResponseHandle.responseBuilder("Successfully created a wishlist!", null, HttpStatus.OK, message);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@RequestBody WishListRequest wishListRequest, @PathVariable Integer id) {
        String message = wishListService.update(wishListRequest, id);
        return ResponseHandle.responseBuilder("Successfully updated the wishlist!", null, HttpStatus.OK, message);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        String message = wishListService.delete(id);
        return ResponseHandle.responseBuilder("Successfully deleted wishlist!", null, HttpStatus.OK, message);
    }
}
