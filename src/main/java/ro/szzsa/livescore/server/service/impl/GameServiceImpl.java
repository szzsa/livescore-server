package ro.szzsa.livescore.server.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.szzsa.livescore.model.Game;
import ro.szzsa.livescore.model.GameStatus;
import ro.szzsa.livescore.model.Goal;
import ro.szzsa.livescore.model.Penalty;
import ro.szzsa.livescore.model.PenaltyType;
import ro.szzsa.livescore.server.repository.dao.GameDao;
import ro.szzsa.livescore.server.service.GameService;

/**
 *
 */
@Service
public class GameServiceImpl implements GameService {

  private final GameDao dao;

  @Autowired
  public GameServiceImpl(GameDao dao) {
    this.dao = dao;
  }

  @Override
  public Game getGame(String id) {
    return convertGame(dao.findOne(id));
  }

  @Override
  public List<Game> getGames() {
    return convertGames(dao.findAll());
  }

  private List<Game> convertGames(Iterable<ro.szzsa.livescore.server.repository.model.Game> entities) {
    List<Game> games = new ArrayList<>();
    entities.forEach(game -> games.add(convertGame(game)));
    return games;
  }

  private Game convertGame(ro.szzsa.livescore.server.repository.model.Game entity) {
    if (entity == null) {
      return null;
    }

    Game game = new Game();
    game.setId(entity.getId());
    game.setDate(entity.getDate());
    game.setStatus(GameStatus.valueOf(entity.getStatus()));
    game.setTime(entity.getTime());
    game.setHomeTeamCode(entity.getHomeTeamCode());
    game.setVisitorTeamCode(entity.getVisitorTeamCode());
    game.setHomeTeamScore(entity.getHomeTeamScore());
    game.setVisitorTeamScore(entity.getVisitorTeamScore());
    game.setGoals(convertGoals(entity.getGoals()));
    game.setPenalties(convertPenalties(entity.getPenalties()));
    return game;
  }

  private List<Goal> convertGoals(Set<ro.szzsa.livescore.server.repository.model.Goal> entities) {
    List<Goal> goals = new ArrayList<>(entities.size());
    entities.forEach(goal -> goals.add(convertGoal(goal)));
    return goals;
  }

  private Goal convertGoal(ro.szzsa.livescore.server.repository.model.Goal entity) {
    Goal goal = new Goal();
    goal.setTime(entity.getTime());
    goal.setAssists(Arrays.asList(entity.getAssists().split(",")));
    goal.setAuthor(entity.getAuthor());
    goal.setGameId(entity.getGameId());
    goal.setTeamCode(entity.getTeamCode());
    return goal;
  }

  private List<Penalty> convertPenalties(Set<ro.szzsa.livescore.server.repository.model.Penalty> entities) {
    List<Penalty> penalties = new ArrayList<>(entities.size());
    entities.forEach(penalty -> penalties.add(convertPenalty(penalty)));
    return penalties;
  }

  private Penalty convertPenalty(ro.szzsa.livescore.server.repository.model.Penalty entity) {
    Penalty penalty = new Penalty();
    penalty.setTime(entity.getTime());
    penalty.setPlayer(entity.getPlayer());
    penalty.setTypes(convertPenaltyTypes(entity.getTypes()));
    penalty.setGameId(entity.getGameId());
    penalty.setTeamCode(entity.getTeamCode());
    return penalty;
  }

  private List<PenaltyType> convertPenaltyTypes(String entity) {
    List<PenaltyType> penaltyTypes = new ArrayList<>();
    for (String penaltyType : entity.split(",")) {
      penaltyTypes.add(PenaltyType.valueOf(penaltyType));
    }
    return penaltyTypes;
  }
}
