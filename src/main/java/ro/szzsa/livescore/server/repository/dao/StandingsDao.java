package ro.szzsa.livescore.server.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.szzsa.livescore.server.repository.model.Standings;

/**
 *
 */
public interface StandingsDao extends JpaRepository<Standings, Integer> {
}
