package ro.szzsa.livescore.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.szzsa.livescore.model.Game;
import ro.szzsa.livescore.model.GameStatus;
import ro.szzsa.livescore.server.repository.dao.GameDao;
import ro.szzsa.livescore.server.service.GameService;
import ro.szzsa.livescore.server.service.NotificationService;
import ro.szzsa.livescore.server.service.converter.GameConverter;

/**
 *
 */
@Service
public class GameServiceImpl implements GameService {

  private final GameDao dao;

  private final GameConverter converter;

  private final NotificationService notificationService;

  @Autowired
  public GameServiceImpl(GameDao dao,
                         GameConverter converter,
                         NotificationService notificationService) {
    this.dao = dao;
    this.converter = converter;
    this.notificationService = notificationService;
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
    checkLiveGameEvents(game, getGame(game.getId()));
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

  private void checkLiveGameEvents(Game newGame, Game game) {
    if (areGoalsChanged(newGame, game) ||
      arePenaltiesChanged(newGame, game) ||
      isTimeChanged(newGame, game) ||
      isStatusChanged(newGame)) {
      notificationService.sendNotification(game);
    }
  }

  private boolean areGoalsChanged(Game newGame, Game game) {
    if (game.getGoals() == null) {
      return newGame.getGoals().size() > 0;
    }
    return newGame.getGoals().size() != game.getGoals().size();
  }

  private boolean arePenaltiesChanged(Game newGame, Game game) {
    if (game.getPenalties() == null) {
      return newGame.getPenalties().size() > 0;
    }
    return newGame.getPenalties().size() != game.getPenalties().size();
  }

  private boolean isTimeChanged(Game newGame, Game game) {
    if (game.getTime() == null) {
      return newGame.getTime().length() > 0;
    }
    return !newGame.getTime().equals(game.getTime());
  }

  private boolean isStatusChanged(Game newGame) {
    return !GameStatus.LIVE.equals(newGame.getStatus());
  }
}
