package com.example.shopshop.orders.service;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.Item.domain.ItemImage;
import com.example.shopshop.Item.dto.ItemImageDTO;
import com.example.shopshop.cart.domain.CartItem;
import com.example.shopshop.member.domain.Member;
import com.example.shopshop.orders.domain.OrdersHistory;
import com.example.shopshop.orders.domain.OrdersItem;
import com.example.shopshop.orders.domain.OrdersStatus;
import com.example.shopshop.orders.dto.*;
import com.example.shopshop.page.dto.PageRequestDTO;
import com.example.shopshop.page.dto.PageResultDTO;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface OrdersItemService {

    void register(Long cartId, String impUid, int point);

    PageResultDTO<OrdersItemListDTO, Object[]> getOrdersByMember(PageRequestDTO pageRequestDTO, Long id);

    PageResultDTO<OrdersItemListDTO, Object[]> getOrdersByProvider(PageRequestDTO pageRequestDTO, Long id);

    PageResultDTO<OrdersHistoryListDTO, Object[]> getOrdersHistoryByMember(PageRequestDTO pageRequestDTO, Long id);

    PageResultDTO<OrdersHistoryListDTO, Object[]> getOrdersHistoryByProvider(PageRequestDTO pageRequestDTO, Long id);

    void cancelRequest(Long id);

    void manageOrdersStatus(Long id, String ordersStatus);

    void complete(Long ordersItemId, String ordersStatus) throws IOException;

    void cancel(Long ordersItemId, String ordersStatus) throws IOException;


    default Map<String, Object> dtoToEntity(OrdersRegisterDTO ordersRegisterDTO) {

        Map<String, Object> entityMap = new HashMap<>();
        Member member = Member.builder()
                .id(ordersRegisterDTO.getMemberId())
                .build();


        List<OrdersItemDTO> ordersItemDTOList = ordersRegisterDTO.getOrdersItem();


        List<OrdersItem> ordersItemList = ordersItemDTOList.stream().map(ordersItemDTO -> {
            Item item = Item.builder().id(ordersItemDTO.getItemId()).build();
            OrdersItem ordersItem = OrdersItem.builder()
                    .ordersCount(ordersItemDTO.getAmount())
                    .ordersPrice(ordersItemDTO.getItemPrice())
                    .ordersStatus(OrdersStatus.배송준비중)
                    .size(ordersItemDTO.getSize())
                    .buyer(member)
                    .totalPrice(ordersItemDTO.getTotalPrice())
                    .item(item)
                    .impUid(ordersItemDTO.getImpUid())
                    .build();
            return ordersItem;
        }).collect(Collectors.toList());

        entityMap.put("ordersItem", ordersItemList);


        return entityMap;
    }

    default OrdersRegisterDTO entitiesToDTOForRegister(Long cartId, Long memberId, List<CartItem> cartItems, ItemImage itemImage, String impUid) {

        ItemImageDTO itemImageDTO = new ItemImageDTO().builder()
                .uuid(itemImage.getUuid())
                .imgName(itemImage.getImgName())
                .path(itemImage.getPath())
                .build();

        OrdersRegisterDTO ordersRegisterDTO = OrdersRegisterDTO.builder()
                .cartId(cartId)
                .memberId(memberId)
                .build();

        List<OrdersItemDTO> ordersItemDTOS = cartItems.stream().map(cartItem -> {
            return OrdersItemDTO.builder()
                    .itemId(cartItem.getItem().getId())
                    .itemName(cartItem.getItem().getItemName())
                    .itemPrice(cartItem.getItem().getSalePrice())
                    .size(cartItem.getSize())
                    .amount(cartItem.getAmount())
                    .memberId(memberId)
                    .totalPrice(cartItem.getAmount() * cartItem.getItem().getSalePrice())
                    .itemImage(itemImageDTO)
                    .impUid(impUid)
                    .ordersStatus(OrdersStatus.배송준비중)
                    .build();

        }).collect(Collectors.toList());

        ordersRegisterDTO.setOrdersItem(ordersItemDTOS);

        return ordersRegisterDTO;
    }

    default OrdersItemListDTO entitiesToDTOForList(OrdersItem ordersItem, Long itemId, String itemName, ItemImage itemImage, LocalDateTime regDate) {

        ItemImageDTO itemImageDTO = new ItemImageDTO().builder()
                .uuid(itemImage.getUuid())
                .imgName(itemImage.getImgName())
                .path(itemImage.getPath())
                .build();


        OrdersItemListDTO ordersItemListDTO =
                OrdersItemListDTO.builder()
                        .id(ordersItem.getId())
                        .itemId(itemId)
                        .itemName(itemName)
                        .ordersPrice(ordersItem.getOrdersPrice())
                        .ordersCount(ordersItem.getOrdersCount())
                        .totalPrice(ordersItem.getTotalPrice())
                        .size(ordersItem.getSize())
                        .itemImage(itemImageDTO)
                        .ordersStatus(ordersItem.getOrdersStatus())
                        .regDate(regDate)
                        .build();



//        ordersListDTO.setOrdersItem(ordersItemDTOS);

        return ordersItemListDTO;
    }

    default OrdersItemListDTO entitiesToDTOForManage(OrdersItem ordersItem, Long itemId, String itemName, ItemImage itemImage, Long memberId, LocalDateTime regDate) {


        ItemImageDTO itemImageDTO = new ItemImageDTO().builder()
                .uuid(itemImage.getUuid())
                .imgName(itemImage.getImgName())
                .path(itemImage.getPath())
                .build();

        OrdersItemListDTO ordersItemListDTO =
                OrdersItemListDTO.builder()
                        .id(ordersItem.getId())
                        .itemId(itemId)
                        .itemName(itemName)
                        .ordersPrice(ordersItem.getOrdersPrice())
                        .ordersCount(ordersItem.getOrdersCount())
                        .totalPrice(ordersItem.getTotalPrice())
                        .size(ordersItem.getSize())
                        .itemImage(itemImageDTO)
                        .ordersStatus(ordersItem.getOrdersStatus())
                        .regDate(regDate)
                        .build();


        return ordersItemListDTO;
    }

    default OrdersHistoryListDTO entitiesToDTOForHistory(OrdersHistory ordersHistory, Long itemId, String itemName, ItemImage itemImage, Long memberId, LocalDateTime regDate) {


        ItemImageDTO itemImageDTO = new ItemImageDTO().builder()
                .uuid(itemImage.getUuid())
                .imgName(itemImage.getImgName())
                .path(itemImage.getPath())
                .build();

        OrdersHistoryListDTO ordersHistoryListDTO = OrdersHistoryListDTO.builder()
                .id(ordersHistory.getId())
                .memberId(memberId)
                .itemId(itemId)
                .itemName(itemName)
                .ordersPrice(ordersHistory.getOrdersPrice())
                .ordersCount(ordersHistory.getOrdersCount())
                .totalPrice(ordersHistory.getTotalPrice())
                .size(ordersHistory.getSize())
                .itemImage(itemImageDTO)
                .ordersStatus(ordersHistory.getOrdersStatus())
                .regDate(regDate)
                .build();


        return ordersHistoryListDTO;
    }


}
