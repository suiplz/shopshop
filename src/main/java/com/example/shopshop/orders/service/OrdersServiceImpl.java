package com.example.shopshop.orders.service;

import com.example.shopshop.orders.domain.Orders;
import com.example.shopshop.orders.domain.OrdersItem;
import com.example.shopshop.orders.dto.OrdersDTO;
import com.example.shopshop.orders.dto.OrdersItemDTO;
import com.example.shopshop.orders.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService{

    private final OrdersRepository ordersRepository;

    @Override
    public Long register(OrdersDTO ordersDTO, OrdersItemDTO ordersItemDTO) {
        Orders orders = dtoToOrders(ordersDTO);
        OrdersItem ordersItem = dtoToOrdersItem(ordersItemDTO);
        orders.addOrdersItem(ordersItem);
        ordersRepository.save(orders);
        return orders.getId();
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
