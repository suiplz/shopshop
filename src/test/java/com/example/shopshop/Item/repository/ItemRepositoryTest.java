package com.example.shopshop.Item.repository;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.member.domain.Member;
import com.example.shopshop.member.repository.MemberRepository;
import com.example.shopshop.page.dto.PageRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemImageRepository itemImageRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void insertItem() {

        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = memberRepository.findById((long) i).orElseThrow(() -> new IllegalArgumentException());
            Item item = Item.builder()
                    .itemName("itemName" + i)
                    .price(10 * i)
                    .sizeM(10 * i)
                    .provider(member)
                    .build();
            itemRepository.save(item);

        });
    }

    @Test
    void testListPage() {

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id"));
        PageRequestDTO requestDTO = new PageRequestDTO();
        Pageable pageable = requestDTO.getPageable(Sort.by("id").descending());


        Page<Object[]> result = itemRepository.getListPage(pageable);
        for (Object[] objects : result.getContent()) {
            System.out.println("LEFT OUTER JOIN : " +Arrays.toString(objects));
        }


//        Page<Object[]> result2 = itemRepository.getListPageFetch(pageable);
//        for (Object[] objects : result2.getContent()) {
//            System.out.println("JOIN FETCH : " + Arrays.toString(objects));
//
//        }

//        List<Object[]> listPage = itemRepository.getListPage2();
//        for (Object[] objects : listPage) {
//            System.out.println(Arrays.toString(objects));
//        }

    }

    @Test
    void getItem() {
        List<Object[]> item = itemRepository.getItemDetail(23L);
        for (Object[] objects : item) {

            System.out.println("objects = " + Arrays.toString(objects));
        }
    }

    @Test
    void deleteItem() {
        itemImageRepository.deleteById(77L);
        itemImageRepository.deleteById(78L);
        itemImageRepository.deleteById(79L);
        itemImageRepository.deleteById(80L);

        itemRepository.deleteById(24L);
    }

    @Test
    void getByMember() {

        IntStream.rangeClosed(1, 10).forEach(i -> {
            Member member = memberRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException());

            Item item = Item.builder()
                    .itemName("itemName" + i)
                    .price(10 * i)
                    .sizeM(10 * i)
                    .provider(member)
                    .build();
            itemRepository.save(item);

        });
        List<Item> itemByMemberId = itemRepository.getItemByMemberId(1L);

        for (Item item : itemByMemberId) {
            System.out.println("item = " + item);
        }

    }


    @Test
    void changeStockTest() {

        Item item = itemRepository.findById(2L).orElseThrow(() -> new IllegalArgumentException());


        log.info("Before : " + item.getSizeS());
        int stock = 1;
        item.addStock("S", stock);

        log.info("After : " + item.getSizeS());

        item.removeStock("S", 1);

        log.info("Final : " + item.getSizeS());

    }

    @Test
    void getItemByComponentsTest() {

        PageRequestDTO requestDTO = new PageRequestDTO();
        Pageable pageable = requestDTO.getPageable(Sort.by("id").descending());

        Page<Object[]> itemByComponents = itemRepository.getItemByComponents(pageable, "MALE", "SPRING", "TOP");
        Page<Object[]> itemByComponents1 = itemRepository.getItemByComponents(pageable, null, null, "OUTER");
        Page<Object[]> itemByComponents2 = itemRepository.getItemByComponents(pageable, null, null, null);
        Page<Object[]> itemByComponents3 = itemRepository.getItemByComponents(pageable, "MALE", "SPRING", null);

        for (Object[] objects : itemByComponents) {
            log.info("result1 : " + Arrays.toString(objects));
        }

        for (Object[] objects : itemByComponents1) {
            log.info("result2 : " + Arrays.toString(objects));
        }

        for (Object[] objects : itemByComponents2) {
            log.info("result3 : " + Arrays.toString(objects));
        }
        for (Object[] objects : itemByComponents3) {
            log.info("result3 : " + Arrays.toString(objects));
        }

    }

    @Test
    void getLiteByItemNameTest() {
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id"));
        PageRequestDTO requestDTO = new PageRequestDTO();
        Pageable pageable = requestDTO.getPageable(Sort.by("id").descending());
        Page<Object[]> result = itemRepository.getListByItemName(pageable, "a");

        for (Object[] objects : result) {
            log.info("result : " + Arrays.toString(objects));
        }

    }


}