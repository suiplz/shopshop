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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void insertItem(){

        IntStream.rangeClosed(1, 100).forEach(i -> {
            Optional<Member> result = memberRepository.findById((long) i);
            Member member = result.get();
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
            System.out.println(Arrays.toString(objects));
        }

//        List<Object[]> listPage = itemRepository.getListPage2();
//        for (Object[] objects : listPage) {
//            System.out.println(Arrays.toString(objects));
//        }

    }

    @Test
    void getItem() {
        Long id = 1L;
        List<Object[]> item = itemRepository.getItemDetail(2L);
        for (Object[] objects : item) {

            System.out.println("objects = " + Arrays.toString(objects));
        }
    }

    @Test
    void getByMember() {

        IntStream.rangeClosed(1, 10).forEach(i -> {
            Optional<Member> result = memberRepository.findById(1L);
            Member member = result.get();
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

        Optional<Item> findItem = itemRepository.findById(2L);
        Item item = findItem.get();

        log.info("Before : " + item.getSizeS());
        int stock = 1;
        item.addStock("S", stock);

        log.info("After : " + item.getSizeS());

        item.removeStock("S", 1);

        log.info("Final : " + item.getSizeS());

    }

}