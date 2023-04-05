package com.example.shopshop.category.repository;

import com.example.shopshop.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Long> {


    @Query("SELECT c FROM Category c " +
            "WHERE c.gender = :gender AND c.season = :season AND c.clothType = :clothType")
    Category getCategoriesByComponents(@Param("gender") String gender, @Param("season") String season, @Param("clothType") String clothType);
}
