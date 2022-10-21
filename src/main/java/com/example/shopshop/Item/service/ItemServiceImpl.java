package com.example.shopshop.Item.service;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.Item.dto.ItemDTO;
import com.example.shopshop.Item.repository.ItemRepository;
import com.example.shopshop.page.dto.PageRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;

    @Override
    public Long register(ItemDTO dto) {
        Item item = dtoToEntity(dto);
        itemRepository.save(item);
        return dto.getId();
    }

    @Override
    public List<Item> getList(PageRequestDTO requestDTO) {
        return null;
    }

    @Override
    public void remove(Long id) {
        itemRepository.deleteById(id);
    }
}
