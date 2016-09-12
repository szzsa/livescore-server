package ro.szzsa.livescore.server.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.szzsa.livescore.server.repository.model.TeamStats;

/**
 *
 */
public interface TeamStatsDao extends JpaRepository<TeamStats, Integer> {
}
