package org.euvsvirus.cac.repository;

import org.euvsvirus.cac.model.Team;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Nils Knudsen
 * @since 24.04.20
 **/
public interface TeamRepository extends CrudRepository<Team, String> {

    Team findByInvitationId(String invitationCode);

}
