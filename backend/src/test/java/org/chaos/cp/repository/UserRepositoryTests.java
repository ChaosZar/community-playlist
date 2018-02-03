package org.chaos.cp.repository;

import org.assertj.core.api.Assertions;
import org.chaos.cp.entity.Playlist;
import org.chaos.cp.entity.Song;
import org.chaos.cp.entity.User;
import org.chaos.cp.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTests {
    public static final String LOGIN = "myLogin";
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository customers;

    @Test
    public void testFindByLogin() {
        User customer = new User("myLogin");
        persist(customer);

        User findByLastName = customers.findByLogin(customer.getLogin());

        Assertions.assertThat(findByLastName.getLogin()).isEqualTo(customer.getLogin());
    }

    @Test
    public void testFindByLoginWithFilledPlaylist() {
        Song song = new Song("SONG_NAME");
        persist(song);

        Playlist playlist = new Playlist();
        playlist.add(song);
        persist(playlist);

        User customer = new User(LOGIN);
        customer.setPlaylist(playlist);
        persist(customer);

        User findByLastName = customers.findByLogin(customer.getLogin());

        Assertions.assertThat(findByLastName.getPlaylist().get(0).getName()).isEqualTo(customer.getPlaylist().get(0).getName());
    }

    private <E> E persist(E entity) {
        return entityManager.persist(entity);
    }
}
