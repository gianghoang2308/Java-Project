package com.example.springapi09.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springapi09.entity.WishList;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Integer>{
    
}
