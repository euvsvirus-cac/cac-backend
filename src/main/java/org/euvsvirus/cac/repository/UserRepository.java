package org.euvsvirus.cac.repository;

import org.euvsvirus.cac.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

    User findByEmail(String email);

    Boolean existsByEmail(String username);

}
