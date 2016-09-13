package ro.szzsa.livescore.server.repository.dao;

import org.springframework.data.repository.CrudRepository;

import ro.szzsa.livescore.server.repository.model.Game;

/**
 *
 */
public interface GameDao extends CrudRepository<Game, String> {
}
