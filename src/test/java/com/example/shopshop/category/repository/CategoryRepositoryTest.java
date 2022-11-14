//package com.example.shopshop.category.repository;
//
//import com.example.shopshop.category.domain.Category;
//import com.example.shopshop.Item.domain.ClothType;
//import com.example.shopshop.Item.domain.Gender;
//import com.example.shopshop.Item.domain.Season;
//import lombok.RequiredArgsConstructor;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@SpringBootTest
//@RequiredArgsConstructor
//class CategoryRepositoryTest {
//
//    @Autowired
//    private CategoryRepository categoryRepository;
//
//    @Test
//    void insertCategories() {
//
//        List<String> categoryList = new ArrayList<>();
//
//        for(ClothType c : ClothType.values()) {
//            for (Season s : Season.values()) {
//                for (Gender g : Gender.values()) {
//                    Category category = Category.builder().clothType(c).season(s).gender(g).build();
//                    categoryRepository.save(category);
//                }
//
//            }
//        }
//
//    }
//
//    @Test
//    void findIdByComponents() {
//
//        List<Long> result = categoryRepository.getCategoriesByComponents(ClothType.OUTER, Season.AUTUMN, Gender.M);
//        System.out.println("result = " + result);
//    }
//}