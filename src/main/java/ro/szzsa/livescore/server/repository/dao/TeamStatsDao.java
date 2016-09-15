package ro.szzsa.livescore.server.repository.dao;

import org.springframework.data.repository.CrudRepository;

import ro.szzsa.livescore.server.repository.model.TeamStats;

/**
 *
 */
public interface TeamStatsDao extends CrudRepository<TeamStats, String> {
}
