package ro.szzsa.livescore.server.service;

import ro.szzsa.livescore.model.Game;

/**
 *
 */
public interface GameDetailsService {

  Game getGame(String id);
}
