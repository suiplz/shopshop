package com.example.shopshop.orders.service;

import com.example.shopshop.orders.domain.Orders;
import com.example.shopshop.orders.dto.OrdersDTO;
import com.example.shopshop.orders.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService{

    private final OrdersRepository ordersRepository;

    @Override
    public Long register(OrdersDTO dto) {
        Orders orders = dtoToEntity(dto);
        ordersRepository.save(orders);
        return dto.getId();
    }

    @Override
    public List<Orders> getListByMember(Long id) {
        List<Orders> getOrders = ordersRepository.getOrdersByMemberId(id);
        return getOrders;
    }

    @Override
    public void cancel(Long id) {
        ordersRepository.deleteById(id);
    }
}
