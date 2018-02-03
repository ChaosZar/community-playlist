package org.chaos.cp.repository;

import org.chaos.cp.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByLogin(String login);

    User findOne(Long id);

    User save(User user);
}
