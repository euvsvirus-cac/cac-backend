package org.euvsvirus.cac.repository;

import org.euvsvirus.cac.model.TeamUser;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Nils Knudsen
 * @since 24.04.20
 **/
public interface TeamUserRepository extends CrudRepository<TeamUser, String> {
}
