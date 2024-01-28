package com.BookStorePTD.BookStorePTD.orders;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class OrderRepository {

    @Autowired
    private com.BookStorePTD.BookStorePTD.repositories.OrderRepository orderRepository;

    @Test
    public void testSetActive(){
        orderRepository.setActive(2L,true);
    }
}
