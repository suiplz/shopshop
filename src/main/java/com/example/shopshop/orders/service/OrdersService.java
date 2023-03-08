package com.example.shopshop.orders.service;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.Item.domain.ItemImage;
import com.example.shopshop.Item.dto.ItemImageDTO;
import com.example.shopshop.cart.domain.CartItem;
import com.example.shopshop.member.domain.Member;
import com.example.shopshop.orders.domain.Orders;
import com.example.shopshop.orders.domain.OrdersItem;
import com.example.shopshop.orders.dto.OrdersDTO;
import com.example.shopshop.orders.dto.OrdersItemDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface OrdersService {

    void register(Long cartId);

    List<Object[]> getListByMember(Long id);

    void cancel(Long id);


    default Map<String, Object> dtoToEntity(OrdersDTO ordersDTO) {

        Map<String, Object> entityMap = new HashMap<>();
        Member member = Member.builder()
                .id(ordersDTO.getMemberId())
                .build();

        Orders orders = Orders.builder()
                .delivery(ordersDTO.getDelivery())
                .buyer(member)
                .build();

        entityMap.put("orders", orders);

        List<OrdersItemDTO> ordersItemDTOList = ordersDTO.getOrdersItem();

        List<OrdersItem> orderItemList = ordersItemDTOList.stream().map(ordersItemDTO -> {
            Item item = Item.builder().id(ordersItemDTO.getItemId()).build();
            OrdersItem ordersItem = OrdersItem.builder()
                    .ordersCount(ordersItemDTO.getAmount())
                    .ordersPrice(ordersItemDTO.getItemPrice())
                    .item(item)
                    .build();
            return ordersItem;
        }).collect(Collectors.toList());

        entityMap.put("orderItemList", orderItemList);

        return entityMap;
    }

    default OrdersDTO entitiesToDTO(Long cartId, List<CartItem> cartItems, Long memberId, int totalPrice, ItemImage itemImage) {

        OrdersDTO ordersDTO = OrdersDTO.builder()
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
