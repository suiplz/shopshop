package com.example.shopshop.category.service;

import com.example.shopshop.category.domain.Category;
import com.example.shopshop.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category getCategory(String gender, String season, String clothType) {
        Category category = categoryRepository.getCategoriesByComponents(gender, season, clothType);
        return category;

    }
}
