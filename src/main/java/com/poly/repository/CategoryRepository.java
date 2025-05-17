package com.poly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.model.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
