package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;

    @Test
    void ItemRepositoryTest() throws Exception {
        //given
        Item book = new Book();
        //when
        itemRepository.save(book);
        //then
        assertThatCode(()->itemRepository.findOne(book.getId())).doesNotThrowAnyException();
    }

    @Test
    void Item_MergeTest() throws Exception {
        //given
        Item book = new Book();
        book.setName("이기적유전자");
        //when
        itemRepository.save(book)
        ;
        book.setName("이기적유전자");
        itemRepository.save(book);
        Item itemRepositoryOne = itemRepository.findOne(book.getId());

        String repositoryOneName = itemRepositoryOne.getName();
        //then
        assertThat(repositoryOneName).isEqualTo("이기적유전자");

    }
}