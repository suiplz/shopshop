package com.example.shopshop.cart.service;

import com.example.shopshop.cart.domain.Cart;
import com.example.shopshop.cart.dto.CartDTO;
import com.example.shopshop.cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;

    @Override
    public Long Register(CartDTO dto) {
        Cart cart = dtoToEntity(dto);
        cartRepository.save(cart);
        return cart.getId();
    }

    @Override
    public List<Object[]> getCartByMember(Long id) {
        List<Object[]> carts = cartRepository.getCartByMemberId(id);
        return carts;
    }
}
