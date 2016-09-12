package ro.szzsa.livescore.server.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.szzsa.livescore.server.repository.model.Game;

/**
 *
 */
public interface GameDao extends JpaRepository<Game, String> {
}
