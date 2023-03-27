package com.example.shopshop.orders.service;

import com.example.shopshop.Item.domain.ItemImage;
import com.example.shopshop.Item.repository.ItemRepository;
import com.example.shopshop.cart.domain.CartItem;
import com.example.shopshop.cart.dto.CartItemDTO;
import com.example.shopshop.cart.repository.CartItemRepository;
import com.example.shopshop.cart.repository.CartRepository;
import com.example.shopshop.member.domain.Member;
import com.example.shopshop.orders.domain.Orders;
import com.example.shopshop.orders.domain.OrdersItem;
import com.example.shopshop.orders.domain.OrdersStatus;
import com.example.shopshop.orders.dto.OrdersItemDTO;
import com.example.shopshop.orders.dto.OrdersItemListDTO;
import com.example.shopshop.orders.dto.OrdersRegisterDTO;
import com.example.shopshop.orders.repository.OrdersItemRepository;
import com.example.shopshop.orders.repository.OrdersRepository;
import com.example.shopshop.page.dto.PageRequestDTO;
import com.example.shopshop.page.dto.PageResultDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;

    private final OrdersItemRepository ordersItemRepository;

    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;

    private final ItemRepository itemRepository;

    @Transactional
    @Override
    public void register(Long cartId) {
        List<Object[]> result = cartRepository.findCartByCartId(cartId);
        Long memberId = (Long) result.get(0)[1];

        List<CartItem> cartItemList = new ArrayList<>();
        result.forEach(arr -> {
            CartItem cartItem = (CartItem) arr[2];
            cartItemList.add(cartItem);
        });

        OrdersRegisterDTO ordersRegisterDTO = entitiesToDTOForRegister((Long) result.get(0)[0], (Long) result.get(0)[1], cartItemList, (ItemImage) result.get(0)[3]);
        Map<String, Object> entityMap = dtoToEntity(ordersRegisterDTO);
        Orders orders = (Orders) entityMap.get("orders");
        List<OrdersItem> ordersItemList = (List<OrdersItem>) entityMap.get("ordersItem");

        ordersRepository.save(orders);

        ordersItemList.forEach(ordersItem -> {
                    ordersItemRepository.save(ordersItem);
                });
        cartItemList.forEach(cartItem -> {
            cartItemRepository.deleteById(cartItem.getId());
        });
        Integer grandTotal = ordersItemRepository.getGrandTotalByOrdersId(orders.getId());
        orders.setGrandTotal(grandTotal);
        ordersRepository.save(orders);

    }

    @Override
    public PageResultDTO<OrdersItemListDTO, Object[]> getOrdersByMember(PageRequestDTO pageRequestDTO, Long memberId) {

        Pageable pageable = pageRequestDTO.getPageable(Sort.by("id").ascending());

        Page<Object[]> result = ordersRepository.getOrdersByMemberId(pageable, memberId);
        Function<Object[], OrdersItemListDTO> fn = (arr -> entitiesToDTOForList(
                (Orders) arr[0],
                (OrdersItem) arr[1],
                (Long) arr[2],
                (String) arr[3],
                (ItemImage) arr[4],
                (LocalDateTime) arr[5]));

        return new PageResultDTO<>(result, fn);


    }

    @Override
    public PageResultDTO<OrdersItemListDTO, Object[]> getOrdersByProvider(PageRequestDTO pageRequestDTO, Long memberId) {

        Pageable pageable = pageRequestDTO.getPageable(Sort.by("id").ascending());

        Page<Object[]> result = ordersRepository.getOrdersByProviderId(pageable, memberId);
        Function<Object[], OrdersItemListDTO> fn = (arr -> entitiesToDTOForManage(
                (OrdersItem) arr[0],
                (Long) arr[1],
                (String) arr[2],
                (ItemImage) arr[3],
                (Long) arr[4],
                (LocalDateTime) arr[5]));

        return new PageResultDTO<>(result, fn);


    }



    @Override
    public void cancelRequest(Long id) {
        Optional<OrdersItem> result = ordersItemRepository.findById(id);
        OrdersItem ordersItem = result.get();
        ordersItem.cancelRequestOrdersStatus();
        log.info("ordersItem result : " + ordersItem);
        ordersItemRepository.save(ordersItem);

    }


    @Override
    public void manageOrdersStatus(Long id, String ordersStatus) {

        Optional<OrdersItem> result = ordersItemRepository.findById(id);
        OrdersItem ordersItem = result.get();
        OrdersStatus newStatus = OrdersStatus.fromValue(ordersStatus);
        ordersItem.changeOrdersStatus(newStatus);
        ordersItemRepository.save(ordersItem);

    }

    @Override
    @Transactional
    public void cancel(Long id) {

        ordersItemRepository.deleteByOrdersId(id);
        ordersRepository.deleteById(id);
    }
}
