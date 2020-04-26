package org.euvsvirus.cac.repository;

import org.euvsvirus.cac.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface UserRepository extends CrudRepository<User, String> {

    User findByEmail(String email);

    boolean existsByEmail(String username);

    @Modifying(flushAutomatically = true)
    @Query("update User u set u.available = :available where u.id = :userId")
    void updateStatus(@Param("userId") String userId, @Param("available") boolean available);

    @Modifying(flushAutomatically = true)
    @Query("update User u set u.teamId = :teamId where u.id = :userId")
    void addUserToTeam(@Param("userId") String userId, @Param("teamId") String teamId);

    Set<User> findAllByTeamId(@Param("teamId") String teamId);
}
