package ro.szzsa.livescore.server.repository.dao;

import org.springframework.data.repository.CrudRepository;

import ro.szzsa.livescore.server.repository.model.Team;

/**
 *
 */
public interface TeamDao extends CrudRepository<Team, String> {
}
