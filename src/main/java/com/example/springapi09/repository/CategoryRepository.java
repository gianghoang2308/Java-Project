package com.example.springapi09.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springapi09.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
    
}
