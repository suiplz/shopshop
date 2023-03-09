package com.example.shopshop.orders.service;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.Item.domain.ItemImage;
import com.example.shopshop.Item.dto.ItemImageDTO;
import com.example.shopshop.cart.domain.CartItem;
import com.example.shopshop.member.domain.Member;
import com.example.shopshop.orders.domain.Orders;
import com.example.shopshop.orders.domain.OrdersItem;
import com.example.shopshop.orders.dto.OrdersListDTO;
import com.example.shopshop.orders.dto.OrdersRegisterDTO;
import com.example.shopshop.orders.dto.OrdersItemDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface OrdersService {

    void register(Long cartId);

    List<Object[]> getListByMember(Long id);

    void cancel(Long id);


    default Map<String, Object> dtoToEntity(OrdersRegisterDTO ordersRegisterDTO) {

        Map<String, Object> entityMap = new HashMap<>();
        Member member = Member.builder()
                .id(ordersRegisterDTO.getMemberId())
                .build();

        Orders orders = Orders.builder()
                .delivery(ordersRegisterDTO.getDelivery())
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
                    .size(ordersItemDTO.getSize())
                    .item(item)
                    .build();
            return ordersItem;
        }).collect(Collectors.toList());

        entityMap.put("ordersItem", ordersItemList);


        return entityMap;
    }

    default OrdersRegisterDTO entitiesToDTOForRegister(Long cartId, Long memberId, List<CartItem> cartItems, Long itemId, String itemName, int price, ItemImage itemImage) {

        int ordersTotalPrice = 0;
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
                    .itemId(itemId)
                    .itemName(itemName)
                    .itemPrice(cartItem.getItem().getPrice())
                    .size(cartItem.getSize())
                    .amount(cartItem.getAmount())
                    .totalPrice(cartItem.getAmount() * price)
                    .itemImage(itemImageDTO)
                    .build();

        }).collect(Collectors.toList());

        ordersRegisterDTO.setOrdersItem(ordersItemDTOS);

        return ordersRegisterDTO;
    }

    default OrdersListDTO entitiesToDTOForList(Long cartId, Long memberId, List<CartItem> cartItems, int totalPrice, ItemImage itemImage) {

        OrdersListDTO ordersDTO = OrdersListDTO.builder()
                .cartId(cartId)
                .memberId(memberId)
                .totalPrice(totalPrice)
                .build();

        ItemImageDTO itemImageDTO = new ItemImageDTO().builder()
                .uuid(itemImage.getUuid())
                .imgName(itemImage.getImgName())
                .path(itemImage.getPath())
                .build();

        List<OrdersItemDTO> ordersItemDTOS = cartItems.stream().map(cartItem -> {
            return OrdersItemDTO.builder()
                    .itemId(cartItem.getItem().getId())
                    .itemName(cartItem.getItem().getItemName())
                    .size(cartItem.getSize())
                    .amount(cartItem.getAmount())
                    .totalPrice(totalPrice)
                    .itemImage(itemImageDTO)
                    .build();

        }).collect(Collectors.toList());

        ordersDTO.setOrdersItem(ordersItemDTOS);

        return ordersDTO;
    }


}
