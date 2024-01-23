package com.BookStorePTD.BookStorePTD.users;

import com.BookStorePTD.BookStorePTD.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepository {

    @Autowired
    private com.BookStorePTD.BookStorePTD.repositories.UserRepository userRepository;

    @Test
    public void findByUserName(){
        User user = userRepository.findByUserName("phamduc2000").get();
        System.out.println(user.getFullName());

    }

    @Test
    public void existByUserName(){
        boolean check = userRepository.existsByUserName("phamduc2000");
        System.out.println(check);
    }
}
