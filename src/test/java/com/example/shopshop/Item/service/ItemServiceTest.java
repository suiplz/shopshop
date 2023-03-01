package com.example.shopshop.Item.service;

import com.example.shopshop.Item.dto.ItemDTO;
import com.example.shopshop.page.dto.PageRequestDTO;
import com.example.shopshop.page.dto.PageResultDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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

    @Test
    void getItemList() {

        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        PageResultDTO<ItemDTO, Object[]> list = itemService.getList(pageRequestDTO);

        System.out.println("list = " + list);
    }


}