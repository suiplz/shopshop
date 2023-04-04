package com.example.shopshop.Item.service;

import com.example.shopshop.Item.dto.ItemDTO;
import com.example.shopshop.Item.dto.ItemModifyDTO;
import com.example.shopshop.page.dto.PageRequestDTO;
import com.example.shopshop.page.dto.PageResultDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
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

    @Test
    void itemModify() {
        ItemDTO dto = itemService.getItem(1L);
        ItemModifyDTO item = ItemModifyDTO.builder()
                .id(1L)
                .itemName(dto.getItemName())
                .price(dto.getPrice())
                .sizeS(dto.getSizeS())
                .sizeM(dto.getSizeM())
                .sizeL(100)
                .build();


        itemService.modify(item);
    }

    @Test
    void categoryListTest() {

        PageRequestDTO requestDTO = new PageRequestDTO();
        Pageable pageable = requestDTO.getPageable(Sort.by("id").descending());


//        PageResultDTO<ItemDTO, Object[]> list = itemService.getList(requestDTO, "MALE", "SPRING", "TOP");
//        System.out.println("list = " + list);
        PageResultDTO<ItemDTO, Object[]> list2 = itemService.getList(requestDTO, "MALE", "SPRING", null);
        System.out.println("list = " + list2);
    }


}