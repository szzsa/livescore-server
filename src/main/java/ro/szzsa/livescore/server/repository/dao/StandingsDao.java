package ro.szzsa.livescore.server.repository.dao;

import org.springframework.data.repository.CrudRepository;

import ro.szzsa.livescore.server.repository.model.Standings;

/**
 *
 */
public interface StandingsDao extends CrudRepository<Standings, Integer> {
}
