package com.frontrow.springapp.repository;

import com.frontrow.springapp.entity.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> getById(Long id);

    Optional<User> findByName(String name);
}
