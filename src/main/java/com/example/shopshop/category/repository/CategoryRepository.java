//package com.example.shopshop.category.repository;
//
//import com.example.shopshop.category.domain.Category;
//import com.example.shopshop.Item.domain.ClothType;
//import com.example.shopshop.Item.domain.Gender;
//import com.example.shopshop.Item.domain.Season;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.List;
//
//public interface CategoryRepository extends JpaRepository<Category, Long> {
//
//
//    @Query("SELECT id FROM Category " +
//            "WHERE clothType= :c AND season = :s AND gender = :g")
//    List<Long> getCategoriesByComponents(@Param("c") ClothType c, @Param("s") Season s, @Param("g")Gender g);
//}
