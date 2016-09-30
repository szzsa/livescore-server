package ro.szzsa.livescore.server.service.converter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import ro.szzsa.livescore.model.Goal;
import ro.szzsa.livescore.model.GoalType;
import ro.szzsa.livescore.server.repository.dao.GoalDao;

/**
 *
 */
@Service
public class GoalConverter implements DaoConverter<Goal, ro.szzsa.livescore.server.repository.model.Goal> {

  private static final String SEPARATOR = ",";

  private final GoalDao dao;

  @Autowired
  public GoalConverter(GoalDao dao) {
    this.dao = dao;
  }

  @Override
  public Goal toModel(ro.szzsa.livescore.server.repository.model.Goal entity) {
    if (entity == null) {
      return null;
    }
    Goal goal = new Goal();
    goal.setType(GoalType.valueOf(entity.getType()));
    goal.setGameId(entity.getGameId());
    goal.setTeamId(entity.getTeamId());
    goal.setOrder(entity.getOrderInGame());
    goal.setTime(entity.getTime());
    goal.setAuthor(entity.getAuthor());
    if (entity.getAssists() != null) {
      goal.setAssists(Arrays.asList(entity.getAssists().split(SEPARATOR)));
    }
    return goal;
  }

  @Override
  public ro.szzsa.livescore.server.repository.model.Goal toEntity(Goal goal) {
    ro.szzsa.livescore.server.repository.model.Goal entity = new ro.szzsa.livescore.server.repository.model.Goal();
    long id = calculateId(goal);
    entity.setId(id);
    if (dao.exists(id)) {
      entity = dao.findOne(id);
    }
    entity.setType(goal.getType().name());
    entity.setGameId(goal.getGameId());
    entity.setTeamId(goal.getTeamId());
    entity.setOrderInGame(goal.getOrder());
    entity.setTime(goal.getTime());
    entity.setAuthor(goal.getAuthor());
    if (goal.getAssists() != null) {
      entity.setAssists(StringUtils.join(goal.getAssists(), SEPARATOR));
    }
    return entity;
  }

  private long calculateId(Goal goal) {
    return Long.parseLong(String.valueOf(goal.getGameId()) + "00" + String.valueOf(goal.getOrder()));
  }
}
