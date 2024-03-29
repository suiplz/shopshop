package com.example.shopshop.cart.service;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.Item.domain.ItemImage;
import com.example.shopshop.Item.repository.ItemRepository;
import com.example.shopshop.cart.domain.Cart;
import com.example.shopshop.cart.domain.CartItem;
import com.example.shopshop.cart.dto.CartItemDTO;
import com.example.shopshop.cart.dto.CartItemListDTO;
import com.example.shopshop.cart.dto.CartItemModifyDTO;
import com.example.shopshop.cart.repository.CartItemRepository;
import com.example.shopshop.cart.repository.CartRepository;
import com.example.shopshop.member.domain.Member;
import com.example.shopshop.page.dto.PageRequestDTO;
import com.example.shopshop.page.dto.PageResultDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;

    private final ItemRepository itemRepository;

    @Override
    public void register(Member member, Long itemId, CartItemDTO cartItemDTO) throws Exception {

        Cart cart = cartRepository.findCartByMemberId(member.getId());
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new IllegalArgumentException());


        if (cart == null) {
            cart = Cart.builder()
                    .buyer(member)
                    .build();
            cartRepository.save(cart);
        }

        boolean stockCondition = item.stockCondition(cartItemDTO.getSize(), cartItemDTO.getAmount());

        if (stockCondition == true) {

            CartItem cartItem = cartItemRepository.findCartItemByComp(cart.getId(), item.getId(), cartItemDTO.getSize());

            if (cartItem == null) {
                cartItem = CartItem.builder()
                        .cart(cart)
                        .item(item)
                        .amount(cartItemDTO.getAmount())
                        .size(cartItemDTO.getSize())
                        .build();
                item.removeStock(cartItemDTO.getSize(), cartItemDTO.getAmount());
                cartItemRepository.save(cartItem);
            } else {
                throw new Exception("이미 장바구니에 담겨있는 상품입니다.");
                //이미 장바구니에 담겨있는 상품입니다.
            }
        } else {
            throw new Exception("수량이 부족하여 카트에 담을 수 없습니다.");
        }


    }


    @Transactional
    @Override
    public void modify(Long cartItemId, CartItemModifyDTO dto) throws Exception {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new IllegalArgumentException("없는 카트 정보입니다."));

        Item item = itemRepository.findById(cartItem.getItem().getId()).orElseThrow(() -> new IllegalArgumentException());

        if (!item.stockCondition(dto.getSize(), dto.getAmount())) {
            throw new Exception("재고 수량이 부족합니다.");
        }
        item.addStock(cartItem.getSize(), cartItem.getAmount());

        cartItem.changeSize(dto.getSize());
        cartItem.changeAmount(dto.getAmount());

        item.removeStock(dto.getSize(), dto.getAmount());
        itemRepository.save(item);

        cartItemRepository.save(cartItem);

    }

    @Override
    public PageResultDTO<CartItemListDTO, Object[]> getCartByMember(PageRequestDTO requestDTO, Long memberId) {

        Pageable pageable = requestDTO.getPageable(Sort.by("id").descending());

        Page<Object[]> result = cartRepository.getCartByMemberId(pageable, memberId);
        Function<Object[], CartItemListDTO> fn = (arr -> entitiesToDTO(
                (Cart) arr[0],
                (CartItem) arr[1],
                (Long) arr[2],
                (String) arr[3],
                (Integer) arr[4],
                (Integer) arr[5],
                (ItemImage) arr[6])
        );

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public Cart findByMemberId(Long memberId) {
        Cart cart = cartRepository.findCartByMemberId(memberId);
        return cart;
    }

    @Transactional
    @Override
    public void remove(Long cartItemId) {

        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new IllegalArgumentException());

        Item item = cartItem.getItem();
        item.addStock(cartItem.getSize(), cartItem.getAmount());
        cartItemRepository.deleteById(cartItemId);

    }

    @Override
    public int grandTotalOfCart(List<CartItemListDTO> dtoList) {
        int sumTotalPrice = 0;
        for (CartItemListDTO cartItemListDTO : dtoList) {
            sumTotalPrice += cartItemListDTO.totalPrice();
        }
        return sumTotalPrice;
    }
}
