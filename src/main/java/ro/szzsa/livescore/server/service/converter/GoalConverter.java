package ro.szzsa.livescore.server.service.converter;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.szzsa.livescore.model.Goal;
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
    goal.setGameId(entity.getGameId());
    goal.setTeamCode(entity.getTeamCode());
    goal.setTime(entity.getTime());
    goal.setAuthor(entity.getAuthor());
    goal.setAssists(Arrays.asList(entity.getAssists().split(SEPARATOR)));
    return goal;
  }

  @Override
  public ro.szzsa.livescore.server.repository.model.Goal toEntity(Goal goal) {
    ro.szzsa.livescore.server.repository.model.Goal entity = new ro.szzsa.livescore.server.repository.model.Goal();
    String id = calculateId(goal);
    entity.setId(id);
    if (dao.exists(id)) {
      entity = dao.findOne(id);
    }
    entity.setGameId(goal.getGameId());
    entity.setTeamCode(goal.getTeamCode());
    entity.setTime(goal.getTime());
    entity.setAuthor(goal.getAuthor());
    entity.setAssists(StringUtils.join(goal.getAssists(), SEPARATOR));
    return entity;
  }

  private String calculateId(Goal goal) {
    return goal.getGameId() + "-" + goal.getTime();
  }
}
