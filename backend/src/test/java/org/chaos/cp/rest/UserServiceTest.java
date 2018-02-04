package org.chaos.cp.rest;

import org.assertj.core.api.Assertions;
import org.chaos.cp.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void shouldCreateAUserWithAnEmptyPlaylist() {

        User monkey = userService.getUser("Monkey");

        Assertions.assertThat(monkey.getPlaylist()).isNotNull();
    }
}