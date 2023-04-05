package com.example.shopshop.category.repository;

import com.example.shopshop.Item.domain.ClothType;
import com.example.shopshop.Item.domain.Gender;
import com.example.shopshop.Item.domain.Season;
import com.example.shopshop.category.domain.Category;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;

@SpringBootTest
@Log4j2
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void insertCategories() {

        List<String> categoryList = new ArrayList<>();

        for (ClothType c : ClothType.values()) {
            for (Season s : Season.values()) {
                for (Gender g : Gender.values()) {
                    Category category = Category.builder().clothType(c.getValue()).season(s.getValue()).gender(g.getValue()).build();
                    categoryRepository.save(category);
                }

            }
        }

    }

    @Test
    void findIdByComponents() {

//        List<Long> result = categoryRepository.getCategoriesByComponents();
//        System.out.println("result = " + result);
    }

    @Test
    void deleteCategories() {
        LongStream.rangeClosed(147, 194).forEach(i -> {
            categoryRepository.deleteById(i);
        });
    }

    @Test
    void categoryTypeTest() {
        Optional<Category> result = categoryRepository.findById(1L);
        Category category = result.get();

        log.info("type : " + category.getClothType().getClass());
    }
}