package ro.szzsa.livescore.server.service.converter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.szzsa.livescore.model.Game;
import ro.szzsa.livescore.model.GameStatus;
import ro.szzsa.livescore.model.Goal;
import ro.szzsa.livescore.model.Penalty;
import ro.szzsa.livescore.server.repository.dao.GameDao;

/**
 *
 */
@Service
public class GameConverter implements DaoConverter<Game, ro.szzsa.livescore.server.repository.model.Game> {

  private final GameDao dao;

  private final GoalConverter goalConverter;

  private final PenaltyConverter penaltyConverter;

  @Autowired
  public GameConverter(GameDao dao,
                       GoalConverter goalConverter,
                       PenaltyConverter penaltyConverter) {
    this.dao = dao;
    this.goalConverter = goalConverter;
    this.penaltyConverter = penaltyConverter;
  }

  @Override
  public Game toModel(ro.szzsa.livescore.server.repository.model.Game entity) {
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

  @Override
  public ro.szzsa.livescore.server.repository.model.Game toEntity(Game game) {
    ro.szzsa.livescore.server.repository.model.Game entity = new ro.szzsa.livescore.server.repository.model.Game();
    entity.setId(game.getId());
    if (dao.exists(game.getId())) {
      entity = dao.findOne(game.getId());
    }
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

  private List<Goal> convertGoals(Set<ro.szzsa.livescore.server.repository.model.Goal> entities) {
    if (entities == null) {
      return null;
    }
    List<Goal> goals = new ArrayList<>(entities.size());
    entities.forEach(goal -> goals.add(goalConverter.toModel(goal)));
    return goals;
  }

  private List<Penalty> convertPenalties(Set<ro.szzsa.livescore.server.repository.model.Penalty> entities) {
    if (entities == null) {
      return null;
    }
    List<Penalty> penalties = new ArrayList<>(entities.size());
    entities.forEach(penalty -> penalties.add(penaltyConverter.toModel(penalty)));
    return penalties;
  }

  private Set<ro.szzsa.livescore.server.repository.model.Goal> convertGoalsToEntities(List<Goal> goals) {
    if (goals == null) {
      return null;
    }
    Set<ro.szzsa.livescore.server.repository.model.Goal> entities = new HashSet<>(goals.size());
    goals.forEach(goal -> entities.add(goalConverter.toEntity(goal)));
    return entities;
  }

  private Set<ro.szzsa.livescore.server.repository.model.Penalty> convertPenaltiesToEntities(List<Penalty> penalties) {
    if (penalties == null) {
      return null;
    }
    Set<ro.szzsa.livescore.server.repository.model.Penalty> entities = new HashSet<>(penalties.size());
    penalties.forEach(penalty -> entities.add(penaltyConverter.toEntity(penalty)));
    return entities;
  }
}
