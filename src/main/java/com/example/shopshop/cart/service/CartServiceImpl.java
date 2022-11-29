package com.example.shopshop.cart.service;

import com.example.shopshop.cart.domain.Cart;
import com.example.shopshop.cart.domain.CartItem;
import com.example.shopshop.cart.dto.CartDTO;
import com.example.shopshop.cart.repository.CartItemRepository;
import com.example.shopshop.cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Log4j2
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;

    @Override
    public Long register(CartDTO dto) {
        Map<String, Object> entityMap = dtoToEntity(dto);
        Cart cart = (Cart) entityMap.get("cart");
        log.info("entityMapCart = " + cart);
        List<CartItem> cartItemList = (List<CartItem>) entityMap.get("cartItemList");

        cartItemList.forEach(cartItem -> {
            log.info("cartItem : " + cartItem);
            cartItemRepository.save(cartItem);
        });
        cartRepository.save(cart);
        return cart.getId();
    }

    @Override
    public List<Object[]> getCartByMember(Long id) {
        List<Object[]> carts = cartRepository.getCartByMemberId(id);
        return carts;
    }
}
