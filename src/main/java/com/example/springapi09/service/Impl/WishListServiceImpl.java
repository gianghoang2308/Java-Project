package com.example.springapi09.service.Impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springapi09.dto.WishList.WishListRequest;
import com.example.springapi09.dto.WishList.WishListResponse;
import com.example.springapi09.entity.WishList;
import com.example.springapi09.exception.NotFoundException;
import com.example.springapi09.mapper.WishListMapper;
import com.example.springapi09.repository.WishListRepository;
import com.example.springapi09.service.WishListService;

@Service
public class WishListServiceImpl implements WishListService {

    @Autowired
    WishListRepository wishListRepository;

    @Autowired
    WishListMapper wishListMapper;

    @Override
    public List<WishListResponse> getAllWishLists() {
        List<WishList> wishLists = wishListRepository.findAll();
        if (wishLists.isEmpty()) {
            throw new NotFoundException("Data cannot be null");
        }
        return wishListMapper.toResponseList(wishLists);
    }

    @Override
    public WishListResponse findById(Integer id) {
        Optional<WishList> wishList = wishListRepository.findById(Objects.requireNonNull(id));
        if (wishList.isEmpty()) {
            throw new NotFoundException("Cannot find wish list with id: " + id);
        }
        return wishListMapper.toResponse(wishList.get());
    }

    @Override
    public String create(WishListRequest wishListRequest) {
        if (wishListRequest == null) {
            throw new NotFoundException("Data cannot be null");
        }
        WishList create = wishListMapper.toWishList(wishListRequest);
        wishListRepository.save(Objects.requireNonNull(create));
        return "";
    }

    @Override
    public String update(WishListRequest wishListRequest, Integer id) {
        if (wishListRequest == null) {
            throw new NotFoundException("Data cannot be null");
        }

        if (id == null) {
            throw new NotFoundException("Id cannot be null");
        }

        WishList existing = wishListRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cannot find wish list with id: " + id));

        wishListMapper.updateEntity(wishListRequest, existing);
        wishListRepository.save(Objects.requireNonNull(existing));
        return "Successfully update wish listh with id: " + id;
    }

    @Override
    public String delete(Integer id) {
        Optional<WishList> wishList = wishListRepository.findById(Objects.requireNonNull(id));
        if (wishList.isEmpty()) {
            throw new NotFoundException("Cannot find wish list with id: " + id);
        }
        wishListRepository.deleteById(id);
        return "Successfully delete with list with id: " + id;
    }
}
