package com.BookStorePTD.BookStorePTD.products;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ProductRepository {

    @Autowired
    private com.BookStorePTD.BookStorePTD.repositories.ProductRepository productRepository;

    @Test
    public void existsByName(){
        boolean check= productRepository.existsByName("Laptop Lenovo Legion 5");
        System.out.println(check);
    }
}
