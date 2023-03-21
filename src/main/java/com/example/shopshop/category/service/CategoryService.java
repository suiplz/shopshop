package com.example.shopshop.category.service;

import com.example.shopshop.category.domain.Category;

public interface CategoryService {

    Category getCategory(String gender, String season, String clothType);
}
