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
  public Game getGame(long id) {
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
    Game oldGame = getGame(game.getId());
    dao.save(converter.toEntity(game));
    if ((oldGame != null && GameStatus.LIVE.equals(oldGame.getStatus()))
      || GameStatus.LIVE.equals(game.getStatus())) {
      checkLiveGameEvents(oldGame, game);
    }
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

  private void checkLiveGameEvents(Game oldGame, Game game) {
    if (areGoalsChanged(oldGame, game) ||
      arePenaltiesChanged(oldGame, game) ||
      isTimeChanged(oldGame, game) ||
      isStatusChanged(oldGame, game)) {
      notificationService.sendNotification(getGame(game.getId()));
    }
  }

  private boolean areGoalsChanged(Game oldGame, Game game) {
    if (game == null || game.getGoals() == null) {
      return false;
    }
    if (oldGame == null || oldGame.getGoals() == null) {
      return game.getGoals().size() > 0;
    }
    return game.getGoals().size() != oldGame.getGoals().size();
  }

  private boolean arePenaltiesChanged(Game oldGame, Game game) {
    if (game == null || game.getPenalties() == null) {
      return false;
    }
    if (oldGame == null || oldGame.getPenalties() == null) {
      return game.getPenalties().size() > 0;
    }
    return game.getPenalties().size() != oldGame.getPenalties().size();
  }

  private boolean isTimeChanged(Game oldGame, Game game) {
    if (game == null || game.getTime() == null) {
      return false;
    }
    if (oldGame == null || oldGame.getTime() == null) {
      return game.getTime().length() > 0;
    }
    return !game.getTime().equals(oldGame.getTime());
  }

  private boolean isStatusChanged(Game oldGame, Game game) {
    if (game == null || game.getStatus() == null) {
      return false;
    }
    if (oldGame == null || oldGame.getStatus() == null) {
      return true;
    }
    return !oldGame.getStatus().equals(game.getStatus());
  }
}
