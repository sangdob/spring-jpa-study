package jpa.shop.service;

import jpa.shop.domain.item.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

@SpringBootTest
public class ItemUpdateTest {

    @Autowired
    EntityManager entityManager;

    @Test
    public void updateTest() throws Exception {
        Book book = entityManager.find(Book.class, 1L);

        //Transaction
        book.setName("asdada");


    }
}
