package com.example.shopshop.category.repository;

import com.example.shopshop.category.domain.Category;
import com.example.shopshop.category.domain.Gender;
import com.example.shopshop.category.domain.Season;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RequiredArgsConstructor
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void insertCategories() {

        List<String> categoryList = new ArrayList<>();
        categoryList.add("Top");
        categoryList.add("Pants");
        categoryList.add("Jacket");
        categoryList.add("Suit");
        categoryList.add("Shoes");

        for(String n : categoryList) {


            for (Season s : Season.values()) {
                for (Gender g : Gender.values()) {
                    Category category = Category.builder().name(n).season(s).gender(g).build();
                    categoryRepository.save(category);
                }

            }
        }

    }

//    @Test
//    void findIdByComponents() {
//
//        List<Long> result = categoryRepository.getCategoriesBySeasonAndGender(Season.AUTUMN, Gender.M);
//        System.out.println("result = " + result);
//    }
}