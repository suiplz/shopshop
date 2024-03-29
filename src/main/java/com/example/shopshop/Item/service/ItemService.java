package com.example.shopshop.Item.service;


import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.Item.domain.ItemImage;
import com.example.shopshop.Item.dto.ItemDTO;
import com.example.shopshop.Item.dto.ItemImageDTO;
import com.example.shopshop.Item.dto.ItemModifyDTO;
import com.example.shopshop.member.domain.Member;
import com.example.shopshop.page.dto.PageRequestDTO;
import com.example.shopshop.page.dto.PageResultDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface ItemService {

    Long register(ItemDTO dto);

    PageResultDTO<ItemDTO, Object[]> getList(PageRequestDTO requestDTO);

    PageResultDTO<ItemDTO, Object[]> getListByRating(PageRequestDTO requestDTO);

    PageResultDTO<ItemDTO, Object[]> getList(PageRequestDTO requestDTO, String itemName);

    PageResultDTO<ItemDTO, Object[]> getList(PageRequestDTO requestDTO, String gender, String season, String clothType);

    PageResultDTO<ItemDTO, Object[]> getListByProvider(PageRequestDTO pageRequestDTO, Long providerId);

    PageResultDTO<ItemDTO, Object[]> getListByMemberLikes(PageRequestDTO pageRequestDTO, Long memberId);

    ItemDTO getItem(Long id);

    Item findItemById(Long id);

    void modify(ItemModifyDTO dto);

    void remove(Long id);

    default Map<String, Object> dtoToEntity(ItemDTO itemDTO) {
        Map<String, Object> entityMap = new HashMap<>();

        Item item = Item.builder()
                .itemName(itemDTO.getItemName())
                .price(itemDTO.getPrice())
                .sizeS(itemDTO.getSizeS())
                .sizeM(itemDTO.getSizeM())
                .sizeL(itemDTO.getSizeL())
                .category(itemDTO.getCategory())
                .saleRate(itemDTO.getSaleRate())
                .salePrice(Math.round(itemDTO.getPrice() * (100 - itemDTO.getSaleRate()) / 100))
                .provider(Member.builder().id(itemDTO.getProviderId()).build())
                .build();
        entityMap.put("item", item);

        List<ItemImageDTO> imageDTOList = itemDTO.getImageDTOList();

        if (imageDTOList != null && imageDTOList.size() > 0) {
            List<ItemImage> itemImageList = imageDTOList.stream().map(itemImageDTO -> {

                ItemImage itemImage = ItemImage.builder()
                        .imgName(itemImageDTO.getImgName())
                        .path(itemImageDTO.getPath())
                        .uuid(itemImageDTO.getUuid())
                        .item(item)
                        .build();
                return itemImage;
            }).collect(Collectors.toList());

            entityMap.put("imgList", itemImageList);

        }
        return entityMap;
    }

    default ItemDTO entitiesToDTO(Item item, List<ItemImage> itemImages, Double avgRate, Long reviewCnt, Long likesCnt) {

        ItemDTO itemDTO = ItemDTO.builder()
                .id(item.getId())
                .itemName(item.getItemName())
                .price(item.getPrice())
                .sizeS(item.getSizeS())
                .sizeM(item.getSizeM())
                .sizeL(item.getSizeL())
                .saleRate(item.getSaleRate())
                .salePrice(item.getSalePrice())
                .providerId(item.getProvider().getId())
                .regDate(item.getRegDate())
                .modDate(item.getModDate())
                .build();

        List<ItemImageDTO> itemImageDTOList = itemImages.stream().map(itemImage -> {
            return ItemImageDTO.builder()
                    .imgName(itemImage.getImgName())
                    .path(itemImage.getPath())
                    .uuid(itemImage.getUuid())
                    .build();
        }).collect(Collectors.toList());

        itemDTO.setImageDTOList(itemImageDTOList);
        itemDTO.setAvgRate(avgRate);
        itemDTO.setReviewCnt(reviewCnt.intValue());
        itemDTO.setLikesCnt(likesCnt.intValue());

        return itemDTO;
    }
}
