package com.example.shopshop.Item.service;

import com.example.shopshop.Item.dto.ItemDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Test
    void getItem() {

        ItemDTO item = itemService.getItem(1L);
        System.out.println("item = " + item);
    }

}