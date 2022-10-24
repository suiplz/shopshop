package com.example.shopshop.category.repository;

import com.example.shopshop.category.domain.Category;
import com.example.shopshop.category.domain.Gender;
import com.example.shopshop.category.domain.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {


//    @Query("SELECT id FROM Category " +
//            "WHERE season = :s AND gender = :g")
//    List<Long> getCategoriesBySeasonAndGender(@Param("s") Season s, @Param("g")Gender g);
}
