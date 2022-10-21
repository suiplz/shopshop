package com.example.shopshop.Item.repository;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.member.domain.Member;
import com.example.shopshop.member.repository.MemberRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
                    .quantity(10 * i)
                    .provider(member)
                    .reviewCnt(30)
                    .build();
            itemRepository.save(item);

        });
    }

    @Test
    void getByMember() {

        IntStream.rangeClosed(1, 10).forEach(i -> {
            Optional<Member> result = memberRepository.findById(1L);
            Member member = result.get();
            Item item = Item.builder()
                    .itemName("itemName" + i)
                    .price(10 * i)
                    .quantity(10 * i)
                    .provider(member)
                    .reviewCnt(30)
                    .build();
            itemRepository.save(item);

        });
        List<Item> itemByMemberId = itemRepository.getItemByMemberId(1L);

        for (Item item : itemByMemberId) {
            System.out.println("item = " + item);
        }

    }



}