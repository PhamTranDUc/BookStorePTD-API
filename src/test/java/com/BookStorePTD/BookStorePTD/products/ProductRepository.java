package com.BookStorePTD.BookStorePTD.products;

import com.BookStorePTD.BookStorePTD.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void getByListId(){
        List<Long> listId= new ArrayList<>();
        listId.addAll(List.of(1L,158L,160L));
        List<Product> listRs= productRepository.getByListId(listId);
        for(Product p: listRs){
            System.out.println(p.getName());
        }
    }

}
