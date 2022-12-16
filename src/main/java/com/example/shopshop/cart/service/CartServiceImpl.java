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

        Cart cart = cartRepository.findByBuyerId(member.getId());


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

        if (cartItem == null || (cartItem != null && !cartItem.getSize().equals(size))) {
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

//    @Override
//    public void register(Member member, CartItemDTO cartItemDTO) throws Exception{
//
//        Cart cart = cartRepository.findByBuyerId(member.getId());
//
//        //Item page에서 받는 정보 item? dto?
//
//        Item newItem = cartItemDTO.getItem();
//        String size = cartItemDTO.getSize();
//        Integer amount = cartItemDTO.getAmount();
//
//        if (!newItem.stockCondition(size, amount)) {
//            throw new Exception("재고 수량이 부족합니다.");
//        }
//
//        if (cart == null) {
//            cart = Cart.builder()
//                    .buyer(member)
//                    .build();
//            cartRepository.save(cart);
//        }
//
//        Optional<Item> result = itemRepository.findById(newItem.getId());
//        Item item = result.get();
//
//        CartItem cartItem = cartItemRepository.findByCartIdAndItemId(cart.getId(), item.getId());
//
//        if (cartItem == null) {
//            cartItem = CartItem.builder()
//                    .cart(cart)
//                    .item(item)
//                    .amount(amount)
//                    .size(size)
//                    .build();
//            item.changeStock(size, amount);
//            cartItemRepository.save(cartItem);
//        }
//
//        else {
//            throw new Exception("이미 장바구니에 담겨있는 상품입니다.");
//            //이미 장바구니에 담겨있는 상품입니다.
//        }
//    }

    @Override
    public void modify(CartItemModifyDTO dto) throws Exception{
        Optional<CartItem> result = cartItemRepository.findById(dto.getId());
        CartItem cartItem = result.get();
        Optional<Item> findItem = itemRepository.findById(cartItem.getItem().getId());
        Item item = findItem.get();

        if (!item.stockCondition(dto.getSize(), dto.getAmount())) {
            throw new Exception("재고 수량이 부족합니다.");
        }

        String size = cartItem.getSize();
        int amount = cartItem.getAmount();

        log.info("Before : " + item.getSizeS());
        log.info("getAmount : " + amount + " " + size + " " + dto.getSize());

        log.info("result : " + dto.getSize().equals(size));
        item.addStock(size, amount); //???????????????
        log.info("CHECK 1 : " + item.getSizeS());

        cartItem.changeSize(dto.getSize());
        cartItem.changeAmount(dto.getAmount());

        item.removeStock(dto.getSize(), dto.getAmount());
        log.info("CHECK 2 : " + item.getSizeS());
        itemRepository.save(item);
        log.info("CHECK 3 : " + item.getSizeS());

        cartItemRepository.save(cartItem);

    }

    @Override
    public List<Object[]> getCartByMember(Long id) {
        List<Object[]> carts = cartRepository.getCartByMemberId(id);
        return carts;
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
