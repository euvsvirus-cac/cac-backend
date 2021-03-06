package org.euvsvirus.cac.repository;

import org.euvsvirus.cac.model.Skill;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SkillRepository extends CrudRepository<Skill, String> {

    Skill findByNameAndTeamId(String name, String teamId);

    List<Skill> findAllByTeamIdOrderById(String teamId);

    List<Skill> findAllByTeamIdAndNameStartsWithOrderByNameAsc(String teamId, String nameStartsWith);

    @Modifying(flushAutomatically = true)
    @Query(
            value = "insert into user_skill (user_id, skill_id, level) values (:userId, :skillId, :level)",
            nativeQuery = true
    )
    void addUserSkill(@Param("userId") String userId, @Param("skillId") String skillId, @Param("level") String level);

}
