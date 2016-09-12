package ro.szzsa.livescore.server.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.szzsa.livescore.server.repository.model.Team;

/**
 *
 */
public interface TeamDao extends JpaRepository<Team, String> {
}
