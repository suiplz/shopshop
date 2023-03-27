package com.example.shopshop.orders.service;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.Item.domain.ItemImage;
import com.example.shopshop.Item.dto.ItemImageDTO;
import com.example.shopshop.cart.domain.CartItem;
import com.example.shopshop.member.domain.Member;
import com.example.shopshop.orders.domain.Orders;
import com.example.shopshop.orders.domain.OrdersItem;
import com.example.shopshop.orders.domain.OrdersStatus;
import com.example.shopshop.orders.dto.OrdersItemListDTO;
import com.example.shopshop.orders.dto.OrdersListDTO;
import com.example.shopshop.orders.dto.OrdersRegisterDTO;
import com.example.shopshop.orders.dto.OrdersItemDTO;
import com.example.shopshop.page.dto.PageRequestDTO;
import com.example.shopshop.page.dto.PageResultDTO;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface OrdersService {

    void register(Long cartId);

    PageResultDTO<OrdersItemListDTO, Object[]> getOrdersByMember(PageRequestDTO pageRequestDTO, Long id);

    PageResultDTO<OrdersItemListDTO, Object[]> getOrdersByProvider(PageRequestDTO pageRequestDTO, Long id);


    void cancelRequest(Long id);

    void manageOrdersStatus(Long id, String ordersStatus);

    void cancel(Long id);


    default Map<String, Object> dtoToEntity(OrdersRegisterDTO ordersRegisterDTO) {

        Map<String, Object> entityMap = new HashMap<>();
        Member member = Member.builder()
                .id(ordersRegisterDTO.getMemberId())
                .build();

        Orders orders = Orders.builder()
                .buyer(member)
                .build();

        entityMap.put("orders", orders);

        List<OrdersItemDTO> ordersItemDTOList = ordersRegisterDTO.getOrdersItem();


        List<OrdersItem> ordersItemList = ordersItemDTOList.stream().map(ordersItemDTO -> {
            Item item = Item.builder().id(ordersItemDTO.getItemId()).build();
            OrdersItem ordersItem = OrdersItem.builder()
                    .orders(orders)
                    .ordersCount(ordersItemDTO.getAmount())
                    .ordersPrice(ordersItemDTO.getItemPrice())
                    .ordersStatus(OrdersStatus.배송준비중)
                    .size(ordersItemDTO.getSize())
                    .totalPrice(ordersItemDTO.getTotalPrice())
                    .item(item)
                    .build();
            return ordersItem;
        }).collect(Collectors.toList());

        entityMap.put("ordersItem", ordersItemList);


        return entityMap;
    }

    default OrdersRegisterDTO entitiesToDTOForRegister(Long cartId, Long memberId, List<CartItem> cartItems, ItemImage itemImage) {

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
                    .itemPrice(cartItem.getItem().getPrice())
                    .size(cartItem.getSize())
                    .amount(cartItem.getAmount())
                    .totalPrice(cartItem.getAmount() * cartItem.getItem().getPrice())
                    .itemImage(itemImageDTO)
                    .ordersStatus(OrdersStatus.배송준비중)
                    .build();

        }).collect(Collectors.toList());

        ordersRegisterDTO.setOrdersItem(ordersItemDTOS);

        return ordersRegisterDTO;
    }

    default OrdersItemListDTO entitiesToDTOForList(Orders orders, OrdersItem ordersItem, Long itemId, String itemName, ItemImage itemImage, LocalDateTime regDate) {

//        OrdersListDTO ordersListDTO = OrdersListDTO.builder()
//                .memberId(memberId)
//                .grandTotal(grandTotal)
//                .build();

        ItemImageDTO itemImageDTO = new ItemImageDTO().builder()
                .uuid(itemImage.getUuid())
                .imgName(itemImage.getImgName())
                .path(itemImage.getPath())
                .build();

//        List<OrdersItemDTO> ordersItemDTOS = ordersItems.stream().map(ordersItem -> {
//            return OrdersItemDTO.builder()
//                    .itemId(itemId)
//                    .itemName(itemName)
//                    .size(ordersItem.getSize())
//                    .amount(ordersItem.getOrdersCount())
//                    .totalPrice(ordersItem.getTotalPrice())
//                    .itemImage(itemImageDTO)
//                    .build();
//
//        }).collect(Collectors.toList());

        OrdersItemListDTO ordersItemListDTO =
                OrdersItemListDTO.builder()
                        .id(ordersItem.getId())
                        .ordersId(orders.getId())
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


}
