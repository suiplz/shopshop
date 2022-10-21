package com.example.shopshop.Item.service;


import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.Item.dto.ItemDTO;
import com.example.shopshop.page.dto.PageRequestDTO;

import java.util.List;

public interface ItemService {

    Long register(ItemDTO dto);

    List<Item> getList(PageRequestDTO requestDTO);

    void remove(Long id);


    default Item dtoToEntity(ItemDTO dto) {
        Item item = Item.builder()
                .itemName(dto.getItemName())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .provider(dto.getProvider())
                .build();
        return item;
    }
}
