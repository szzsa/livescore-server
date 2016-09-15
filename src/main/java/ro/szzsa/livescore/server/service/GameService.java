package ro.szzsa.livescore.server.service;

import java.util.List;

import ro.szzsa.livescore.model.Game;

/**
 *
 */
public interface GameService {

  Game getGame(String id);

  List<Game> getGames();

  void updateGame(Game game);

  void updateGames(List<Game> games);
}
