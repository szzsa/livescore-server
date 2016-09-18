package ro.szzsa.livescore.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.szzsa.livescore.model.Game;
import ro.szzsa.livescore.server.repository.dao.GameDao;
import ro.szzsa.livescore.server.service.GameService;
import ro.szzsa.livescore.server.service.converter.GameConverter;

/**
 *
 */
@Service
public class GameServiceImpl implements GameService {

  private final GameDao dao;

  private final GameConverter converter;

  @Autowired
  public GameServiceImpl(GameDao dao,
                         GameConverter converter) {
    this.dao = dao;
    this.converter = converter;
  }

  @Override
  public Game getGame(String id) {
    return converter.toModel(dao.findOne(id));
  }

  @Override
  public List<Game> getGames() {
    return convertGames(dao.findAll());
  }

  @Override
  public void updateGame(Game game) {
    if (game == null) {
      return;
    }
    dao.save(converter.toEntity(game));
  }

  @Override
  public void updateGames(List<Game> games) {
    if (games == null) {
      return;
    }
    games.forEach(this::updateGame);
  }

  private List<Game> convertGames(Iterable<ro.szzsa.livescore.server.repository.model.Game> entities) {
    List<Game> games = new ArrayList<>();
    entities.forEach(game -> games.add(converter.toModel(game)));
    return games;
  }
}
