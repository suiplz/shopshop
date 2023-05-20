package com.example.shopshop.orders.service;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.Item.domain.ItemImage;
import com.example.shopshop.Item.repository.ItemRepository;
import com.example.shopshop.cart.domain.CartItem;
import com.example.shopshop.cart.repository.CartItemRepository;
import com.example.shopshop.cart.repository.CartRepository;
import com.example.shopshop.member.domain.Member;
import com.example.shopshop.member.repository.MemberRepository;
import com.example.shopshop.orders.domain.OrdersHistory;
import com.example.shopshop.orders.domain.OrdersItem;
import com.example.shopshop.orders.domain.OrdersStatus;
import com.example.shopshop.orders.dto.OrdersHistoryListDTO;
import com.example.shopshop.orders.dto.OrdersItemListDTO;
import com.example.shopshop.orders.dto.OrdersRegisterDTO;
import com.example.shopshop.orders.repository.OrdersHistoryRepository;
import com.example.shopshop.orders.repository.OrdersItemRepository;
import com.example.shopshop.page.dto.PageRequestDTO;
import com.example.shopshop.page.dto.PageResultDTO;
import com.example.shopshop.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrdersItemServiceImpl implements OrdersItemService {


    private final OrdersItemRepository ordersItemRepository;

    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;

    private final OrdersHistoryRepository ordersHistoryRepository;

    private final MemberRepository memberRepository;

    private final ItemRepository itemRepository;

    private final PaymentService paymentService;

    @Transactional
    @Override
    public void register(Long cartId, String impUid, int point, int grandTotal) {

        List<Object[]> result = cartRepository.findCartByCartId(cartId);
        Long memberId = (Long) result.get(0)[1];

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException());
        member.reducePoint(point);
        memberRepository.save(member);


        List<CartItem> cartItemList = new ArrayList<>();
        result.forEach(arr -> {
            CartItem cartItem = (CartItem) arr[2];
            cartItemList.add(cartItem);
        });

        OrdersRegisterDTO ordersRegisterDTO = entitiesToDTOForRegister((Long) result.get(0)[0], (Long) result.get(0)[1], cartItemList, (ItemImage) result.get(0)[3], (String) impUid, (int) point, (int) grandTotal);
        Map<String, Object> entityMap = dtoToEntity(ordersRegisterDTO);
        List<OrdersItem> ordersItemList = (List<OrdersItem>) entityMap.get("ordersItem");

        ordersItemList.forEach(ordersItem -> {
            ordersItemRepository.save(ordersItem);
        });
        cartItemList.forEach(cartItem -> {
            cartItemRepository.deleteById(cartItem.getId());
        });


    }

    @Override
    public PageResultDTO<OrdersItemListDTO, Object[]> getOrdersByMember(PageRequestDTO pageRequestDTO, Long memberId) {

        Pageable pageable = pageRequestDTO.getPageable(Sort.by("id").ascending());

        Page<Object[]> result = ordersItemRepository.getOrdersByMemberId(pageable, memberId);
        Function<Object[], OrdersItemListDTO> fn = (arr -> entitiesToDTOForList(
                (OrdersItem) arr[0],
                (Long) arr[1],
                (String) arr[2],
                (ItemImage) arr[3],
                (LocalDateTime) arr[4]));

        return new PageResultDTO<>(result, fn);


    }

    @Override
    public PageResultDTO<OrdersItemListDTO, Object[]> getOrdersByProvider(PageRequestDTO pageRequestDTO, Long memberId) {

        Pageable pageable = pageRequestDTO.getPageable(Sort.by("id").ascending());

        Page<Object[]> result = ordersItemRepository.getOrdersByProviderId(pageable, memberId);
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
    public PageResultDTO<OrdersHistoryListDTO, Object[]> getOrdersHistoryByMember(PageRequestDTO pageRequestDTO, Long memberId) {
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("id").descending());

        Page<Object[]> result = ordersHistoryRepository.getOrdersHistoryByMemberId(pageable, memberId);

        Function<Object[], OrdersHistoryListDTO> fn = (arr -> entitiesToDTOForHistory(
                (OrdersHistory) arr[0],
                (Long) arr[1],
                (String) arr[2],
                (ItemImage) arr[3],
                (Long) arr[4],
                (LocalDateTime) arr[5]));

        return new PageResultDTO<>(result, fn);

    }

    @Override
    public PageResultDTO<OrdersHistoryListDTO, Object[]> getOrdersHistoryByProvider(PageRequestDTO pageRequestDTO, Long memberId) {
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("id").descending());

        Page<Object[]> result = ordersHistoryRepository.getOrdersHistoryByProviderId(pageable, memberId);

        Function<Object[], OrdersHistoryListDTO> fn = (arr -> entitiesToDTOForHistory(
                (OrdersHistory) arr[0],
                (Long) arr[1],
                (String) arr[2],
                (ItemImage) arr[3],
                (Long) arr[4],
                (LocalDateTime) arr[5]));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public void cancelRequest(Long id) {
        OrdersItem ordersItem = ordersItemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
        ordersItem.cancelRequestOrdersStatus();
        log.info("ordersItem result : " + ordersItem);
        ordersItemRepository.save(ordersItem);

    }

    @Override
    public boolean previousOrderedStatus(Long memberId, Long itemId) {
        return ordersHistoryRepository.previousOrderedStatus(memberId, itemId);
    }

    @Override
    public void manageOrdersStatus(Long id, String ordersStatus) {

        OrdersItem ordersItem = ordersItemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());

        OrdersStatus newStatus = OrdersStatus.fromValue(ordersStatus);
        ordersItem.changeOrdersStatus(newStatus);
        ordersItemRepository.save(ordersItem);

    }

    @Transactional
    @Override
    public void complete(Long ordersItemId, String ordersStatus) throws IOException {

        OrdersItem ordersItem = ordersItemRepository.findById(ordersItemId).orElseThrow(() -> new IllegalArgumentException());

        Item item = Item.builder().id(ordersItem.getItem().getId()).build();
//        Member member = Member.builder().id(ordersItem.getBuyer().getId()).build();
        Member member = memberRepository.findById(ordersItem.getBuyer().getId()).orElseThrow(() -> new IllegalArgumentException("찾을 수 없습니다."));
        OrdersStatus ordersStatusValue = OrdersStatus.fromValue(ordersStatus);

        OrdersHistory ordersHistory = OrdersHistory.builder()
                .id(ordersItemId)
                .member(member)
                .item(item)
                .ordersPrice(ordersItem.getOrdersPrice())
                .ordersCount(ordersItem.getOrdersCount())
                .size(ordersItem.getSize())
                .totalPrice(ordersItem.getTotalPrice())
                .ordersStatus(ordersStatusValue)
                .build();

        member.addPoint(Math.round((ordersItem.getTotalPrice()) / 20));


        memberRepository.save(member);
        ordersHistoryRepository.save(ordersHistory);

        ordersItemRepository.deleteById(ordersItem.getId());

    }

    @Transactional
    @Override
    public void cancel(Long ordersItemId, String ordersStatus) throws IOException {

        OrdersItem ordersItem = ordersItemRepository.findById(ordersItemId).orElseThrow(() -> new IllegalArgumentException());


        Item item = itemRepository.findById(ordersItem.getItem().getId()).orElseThrow(() -> new IllegalArgumentException());
//        Member member = Member.builder().id(ordersItem.getBuyer().getId()).build();
        Member member = memberRepository.findById(ordersItem.getBuyer().getId()).orElseThrow(() -> new IllegalArgumentException());
        OrdersStatus ordersStatusValue = OrdersStatus.fromValue(ordersStatus);

        OrdersHistory ordersHistory = OrdersHistory.builder()
                .id(ordersItemId)
                .member(member)
                .item(item)
                .ordersPrice(ordersItem.getOrdersPrice())
                .ordersCount(ordersItem.getOrdersCount())
                .size(ordersItem.getSize())
                .totalPrice(ordersItem.getTotalPrice())
                .ordersStatus(ordersStatusValue)
                .build();


        if (!"".equals(ordersItem.getImpUid())) {
            String token = paymentService.getToken();
            log.info("payment result : " + token + " & " + ordersItem.getImpUid());

            int amount = ordersItem.getGrandTotal();
            log.info("repository amount : " + amount);
//                log.info("paymentInfo amount : " + paymentInfoAmount);
            int ordersPrice = ordersItem.getTotalPrice();

            ordersItemRepository.updateGranTotalByImpUid(ordersItem.getImpUid(), amount - ordersPrice);
            paymentService.paymentCancel(token, ordersItem.getImpUid(), amount, ordersPrice);
        }

        item.addStock(ordersItem.getSize(),ordersItem.getOrdersCount());
        itemRepository.save(item);
        ordersHistoryRepository.save(ordersHistory);
        ordersItemRepository.deleteById(ordersItem.getId());

    }

}
