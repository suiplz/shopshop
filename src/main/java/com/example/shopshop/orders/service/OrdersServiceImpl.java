package com.example.shopshop.orders.service;

import com.example.shopshop.Item.domain.ItemImage;
import com.example.shopshop.Item.repository.ItemRepository;
import com.example.shopshop.cart.domain.CartItem;
import com.example.shopshop.cart.dto.CartItemDTO;
import com.example.shopshop.cart.repository.CartRepository;
import com.example.shopshop.orders.domain.Orders;
import com.example.shopshop.orders.domain.OrdersItem;
import com.example.shopshop.orders.dto.OrdersItemDTO;
import com.example.shopshop.orders.dto.OrdersRegisterDTO;
import com.example.shopshop.orders.repository.OrdersItemRepository;
import com.example.shopshop.orders.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrdersServiceImpl implements OrdersService{

    private final OrdersRepository ordersRepository;

    private final OrdersItemRepository ordersItemRepository;

    private final CartRepository cartRepository;

    private final ItemRepository itemRepository;

    @Transactional
    @Override
    public void register(Long cartId) {
        List<Object[]> result = cartRepository.findCartByCartId(cartId);
        Long memberId = (Long) result.get(0)[1];

        List<CartItem> cartItemList = new ArrayList<>();
        result.forEach(arr -> {
            log.info("result : " + arr[2]);
            log.info("price : " + arr[5]);
            CartItem cartItem = (CartItem) arr[2];
            cartItemList.add(cartItem);
        });

        log.info("result : {}, {}, {}, {}, {}, {}, {}", result.get(0)[1], result.get(1), result.get(2)[2]);
        OrdersRegisterDTO ordersRegisterDTO = entitiesToDTOForRegister((Long) result.get(0)[0], (Long) result.get(0)[1], cartItemList, (Long) result.get(0)[3], (String) result.get(0)[4], (int) result.get(0)[5], (ItemImage) result.get(0)[6]);
        Map<String, Object> entityMap = dtoToEntity(ordersRegisterDTO);
        Orders orders = (Orders) entityMap.get("orders");
        List<OrdersItem> ordersItemList = (List<OrdersItem>) entityMap.get("ordersItem");
        ordersRepository.save(orders);

        ordersItemList.forEach(ordersItem -> {
                    ordersItemRepository.save(ordersItem);
                });

//        for (Object[] objects : result) {
//            OrdersRegisterDTO ordersDTO = entitiesToDTOForRegister((Long) objects[0], (Long) objects[1], (CartItem) objects[2], (Long) objects[3], (String) objects[4], (Integer) objects[5], (ItemImage) objects[6]);
//            log.info("result : {}, {}, {}, {}, {}, {}, {}", (Long) objects[0], (Long) objects[1], (CartItem) objects[2], (Long) objects[3], (String) objects[4], (Integer) objects[5], (ItemImage) objects[6]);
//            Map<String, Object> entity = dtoToEntity(ordersDTO);
//            Orders orders = (Orders) entity.get("orders");
//            log.info("orders" + orders);
//            if (ordersRepository.findById(orders.getId()) != null) {
//                ordersRepository.save(orders);
//            }
//            OrdersItem ordersItem = (OrdersItem) entity.get("ordersItem");
//            ordersItemRepository.save(ordersItem);
//    }



    }

    @Override
    public List<Object[]> getListByMember(Long id) {
        List<Object[]> result = ordersRepository.getOrdersItemByMemberId(id);
        return result;
    }

    @Override
    public void cancel(Long id) {
        ordersRepository.deleteById(id);
    }
}
