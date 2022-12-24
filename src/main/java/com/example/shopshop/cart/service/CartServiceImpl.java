package com.example.shopshop.cart.service;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.Item.repository.ItemRepository;
import com.example.shopshop.cart.domain.Cart;
import com.example.shopshop.cart.domain.CartItem;
import com.example.shopshop.cart.dto.CartDTO;
import com.example.shopshop.cart.dto.CartItemDTO;
import com.example.shopshop.cart.dto.CartItemModifyDTO;
import com.example.shopshop.cart.repository.CartItemRepository;
import com.example.shopshop.cart.repository.CartRepository;
import com.example.shopshop.member.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;

    private final ItemRepository itemRepository;

    @Override
    public void register(Member member, Item newItem, String size, Integer amount) throws Exception{
        //CartRegisterDTO? principalID 고려해서 가능하면 DTO 별도 (cart 페이지 아닌 item 페이지에서 정보 받아옴)

        Cart cart = cartRepository.findCartByMemberId(member.getId());


        if (!newItem.stockCondition(size, amount)) {
            throw new Exception("재고 수량이 부족합니다.");
        }

        if (cart == null) {
            cart = Cart.builder()
                    .buyer(member)
                    .build();
            cartRepository.save(cart);
        }

        Optional<Item> result = itemRepository.findById(newItem.getId());
        Item item = result.get();

        CartItem cartItem = cartItemRepository.findByCartIdAndItemId(cart.getId(), item.getId());

        if (cartItem == null || (cartItem != null && !(cartItem.getSize().equals(size)))) {
            cartItem = CartItem.builder()
                    .cart(cart)
                    .item(item)
                    .amount(amount)
                    .size(size)
                    .build();
            item.removeStock(size, amount);
            cartItemRepository.save(cartItem);
        }


        else {
            throw new Exception("이미 장바구니에 담겨있는 상품입니다.");
            //이미 장바구니에 담겨있는 상품입니다.
        }
    }


    @Transactional
    @Override
    public void modify(CartItemModifyDTO dto) throws Exception{
        CartItem cartItem = cartItemRepository.findById(dto.getId()).orElseThrow(() -> new IllegalArgumentException("없는 카트 정보입니다."));


        Optional<Item> findItem = itemRepository.findById(cartItem.getItem().getId());
        Item item = findItem.get();

        if (!item.stockCondition(dto.getSize(), dto.getAmount())) {
            throw new Exception("재고 수량이 부족합니다.");
        }
        log.info("CHECK 1S : " + item.getSizeS());
        log.info("CHECK 1M : " + item.getSizeM());
        log.info("CHECK 1L : " + item.getSizeL());
        item.addStock(dto.getSize(), cartItem.getAmount()); // cartItem.getSize() ???????????????
        log.info("CHECK 2S : " + item.getSizeS());
        log.info("CHECK 2M : " + item.getSizeM());
        log.info("CHECK 2L : " + item.getSizeL());

        cartItem.changeSize(dto.getSize());
        cartItem.changeAmount(dto.getAmount());

        item.removeStock(dto.getSize(), dto.getAmount());
        itemRepository.save(item);
        log.info("CHECK 3S : " + item.getSizeS());
        log.info("CHECK 3M : " + item.getSizeM());
        log.info("CHECK 3L : " + item.getSizeL());

        cartItemRepository.save(cartItem);

    }

    @Override
    public List<Object[]> getCartByMember(Long id) {
        List<Object[]> carts = cartRepository.getCartByMemberId(id);
        return carts;
    }

    @Override
    public Cart findByMemberId(Long memberId) {
        Cart cart = cartRepository.findCartByMemberId(memberId);
        return cart;
    }

    @Transactional
    @Override
    public void remove(Long cartId, Long itemId) {

        CartItem cartItem = cartItemRepository.findByCartIdAndItemId(cartId, itemId);
        Item item = cartItem.getItem();
        item.addStock(cartItem.getSize(), cartItem.getAmount());
        cartItemRepository.deleteByCartIdAndItemId(cartId, itemId);

    }
}
