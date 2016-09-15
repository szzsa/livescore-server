package ro.szzsa.livescore.server.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
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

  private static final String SEPARATOR = ",";

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

  @Override
  public void updateGame(Game game) {
    dao.save(convertGameToEntity(game));
  }

  @Override
  public void updateGames(List<Game> games) {
    games.forEach(this::updateGame);
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
    if (entities == null) {
      return null;
    }

    List<Goal> goals = new ArrayList<>(entities.size());
    entities.forEach(goal -> goals.add(convertGoal(goal)));
    return goals;
  }

  private Goal convertGoal(ro.szzsa.livescore.server.repository.model.Goal entity) {
    Goal goal = new Goal();
    goal.setTime(entity.getTime());
    if (StringUtils.isNotEmpty(entity.getAssists())) {
      goal.setAssists(Arrays.asList(entity.getAssists().split(SEPARATOR)));
    }
    goal.setAuthor(entity.getAuthor());
    goal.setGameId(entity.getGameId());
    goal.setTeamCode(entity.getTeamCode());
    return goal;
  }

  private List<Penalty> convertPenalties(Set<ro.szzsa.livescore.server.repository.model.Penalty> entities) {
    if (entities == null) {
      return null;
    }

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
    for (String penaltyType : entity.split(SEPARATOR)) {
      penaltyTypes.add(PenaltyType.valueOf(penaltyType));
    }
    return penaltyTypes;
  }

  private ro.szzsa.livescore.server.repository.model.Game convertGameToEntity(Game game) {
    ro.szzsa.livescore.server.repository.model.Game entity = new ro.szzsa.livescore.server.repository.model.Game();
    if (dao.exists(game.getId())) {
      entity = dao.findOne(game.getId());
    }
    entity.setId(game.getId());
    entity.setDate(game.getDate());
    entity.setStatus(game.getStatus().name());
    entity.setTime(game.getTime());
    entity.setHomeTeamCode(game.getHomeTeamCode());
    entity.setVisitorTeamCode(game.getVisitorTeamCode());
    entity.setHomeTeamScore(game.getHomeTeamScore());
    entity.setVisitorTeamScore(game.getVisitorTeamScore());
    entity.setGoals(convertGoalsToEntities(game.getGoals()));
    entity.setPenalties(convertPenaltiesToEntities(game.getPenalties()));
    return entity;
  }

  private Set<ro.szzsa.livescore.server.repository.model.Goal> convertGoalsToEntities(List<Goal> goals) {
    if (goals == null) {
      return null;
    }

    Set<ro.szzsa.livescore.server.repository.model.Goal> entities = new HashSet<>(goals.size());
    goals.forEach(goal -> entities.add(convertGoalToEntity(goal)));
    return entities;
  }

  private ro.szzsa.livescore.server.repository.model.Goal convertGoalToEntity(Goal goal) {
    ro.szzsa.livescore.server.repository.model.Goal entity = new ro.szzsa.livescore.server.repository.model.Goal();
    entity.setId(goal.getGameId() + "-" + goal.getTime());
    entity.setTime(goal.getTime());
    entity.setAssists(StringUtils.join(goal.getAssists(), SEPARATOR));
    entity.setAuthor(goal.getAuthor());
    entity.setGameId(goal.getGameId());
    entity.setTeamCode(goal.getTeamCode());
    return entity;
  }

  private Set<ro.szzsa.livescore.server.repository.model.Penalty> convertPenaltiesToEntities(List<Penalty> penalties) {
    if (penalties == null) {
      return null;
    }

    Set<ro.szzsa.livescore.server.repository.model.Penalty> entities = new HashSet<>(penalties.size());
    penalties.forEach(penalty -> entities.add(convertPenaltyToEntity(penalty)));
    return entities;
  }

  private ro.szzsa.livescore.server.repository.model.Penalty convertPenaltyToEntity(Penalty penalty) {
    ro.szzsa.livescore.server.repository.model.Penalty entity = new ro.szzsa.livescore.server.repository.model.Penalty();
    entity.setId(penalty.getGameId() + "-" + penalty.getTime());
    entity.setTime(penalty.getTime());
    entity.setPlayer(penalty.getPlayer());
    entity.setTypes(convertPenaltyTypesToEntity(penalty.getTypes()));
    entity.setGameId(penalty.getGameId());
    entity.setTeamCode(penalty.getTeamCode());
    return entity;
  }

  private String convertPenaltyTypesToEntity(List<PenaltyType> types) {
    if (types == null) {
      return null;
    }

    return StringUtils.join(types, SEPARATOR);
  }
}
