package org.euvsvirus.cac.repository;

import org.euvsvirus.cac.model.UserTeam;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Nils Knudsen
 * @since 24.04.20
 **/
public interface UserTeamRepository extends CrudRepository<UserTeam, String> {
}
