package com.example.shopshop.orders.service;

import com.example.shopshop.Item.domain.ItemImage;
import com.example.shopshop.Item.repository.ItemRepository;
import com.example.shopshop.cart.domain.CartItem;
import com.example.shopshop.cart.repository.CartRepository;
import com.example.shopshop.orders.domain.Orders;
import com.example.shopshop.orders.domain.OrdersItem;
import com.example.shopshop.orders.dto.OrdersDTO;
import com.example.shopshop.orders.repository.OrdersItemRepository;
import com.example.shopshop.orders.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService{

    private final OrdersRepository ordersRepository;

    private final OrdersItemRepository ordersItemRepository;

    private final CartRepository cartRepository;

    private final ItemRepository itemRepository;

    @Override
    public void register(Long cartId) {
        List<Object[]> result = cartRepository.findCartByCartId(cartId);

        for (Object[] objects : result) {
            OrdersDTO ordersDTO = entitiesToDTO((Long) objects[0], (List<CartItem>) objects[1], (Long) objects[2], (Integer) objects[3], (ItemImage) objects[4]);
            Map<String, Object> entity = dtoToEntity(ordersDTO);
            Orders orders = (Orders) entity.get("orders");
            if (ordersRepository.findById(orders.getId()) != null) {
                ordersRepository.save(orders);
            }
            OrdersItem ordersItem = (OrdersItem) entity.get("ordersItem");
            ordersItemRepository.save(ordersItem);
        }

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
