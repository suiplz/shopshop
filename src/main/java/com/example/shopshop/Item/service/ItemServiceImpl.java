package com.example.shopshop.Item.service;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.Item.domain.ItemImage;
import com.example.shopshop.Item.dto.ItemDTO;
import com.example.shopshop.Item.dto.ItemModifyDTO;
import com.example.shopshop.Item.repository.ItemImageRepository;
import com.example.shopshop.Item.repository.ItemRepository;
import com.example.shopshop.page.dto.PageRequestDTO;
import com.example.shopshop.page.dto.PageResultDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;
    private final ItemImageRepository itemImageRepository;

    @Transactional
    @Override
    public Long register(ItemDTO dto) {
        Map<String, Object> entityMap = dtoToEntity(dto);
        Item item = (Item) entityMap.get("item");
        log.info("entityMapItem = " + item);
        List<ItemImage> itemImageList = (List<ItemImage>) entityMap.get("imgList");

        itemRepository.save(item);

        itemImageList.forEach(itemImage -> {
            itemImageRepository.save(itemImage);
        });
        return item.getId();
    }

    @Override
    public PageResultDTO<ItemDTO, Object[]> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("id").descending());

        Page<Object[]> result = itemRepository.getListPage(pageable);


        Function<Object[], ItemDTO> fn = (arr -> entitiesToDTO(
                (Item)arr[0],
                (List<ItemImage>)(Arrays.asList((ItemImage)arr[1]))));


        return new PageResultDTO<>(result, fn);


    }

    @Override
    public ItemDTO getItem(Long id) {

        List<Object[]> result = itemRepository.getItemDetail(id);

        Item item = (Item) result.get(0)[0];

        List<ItemImage> itemImageList = new ArrayList<>();

        result.forEach(arr -> {
            ItemImage itemImage = (ItemImage) arr[1];
            itemImageList.add(itemImage);
        });

        return entitiesToDTO(item, itemImageList);


    }

    @Override
    public void modify(ItemModifyDTO dto) {
        Optional<Item> result = itemRepository.findById(dto.getId());
        if (result.isPresent()) {
            Item item = result.get();
            item.changeItem(dto.getItemName(), dto.getPrice(), dto.getQuantity());
        }

    }

    @Override
    public void remove(Long id) {
        itemRepository.deleteById(id);
    }



}
