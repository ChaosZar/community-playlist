package org.chaos.cp;

import org.assertj.core.api.Assertions;
import org.chaos.cp.entity.User;
import org.chaos.cp.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository customers;

    @Test
    public void testFindByLastName() {
        User customer = new User("first", "last");
        entityManager.persist(customer);

        List<User> findByLastName = customers.findByLastName(customer.getLastName());

        Assertions.assertThat(findByLastName).extracting(User::getLastName).containsOnly(customer.getLastName());
    }
}
