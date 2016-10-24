package ro.szzsa.livescore.server.service.converter;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.szzsa.livescore.model.Goal;
import ro.szzsa.livescore.model.GoalType;
import ro.szzsa.livescore.server.repository.dao.GoalDao;

/**
 *
 */
@Service
public class GoalConverter implements Converter<Goal, ro.szzsa.livescore.server.repository.model.Goal> {

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
    Goal model = new Goal();
    model.setId(entity.getId());
    model.setType(GoalType.valueOf(entity.getType()));
    model.setGameId(entity.getGameId());
    model.setTeamId(entity.getTeamId());
    model.setOrder(entity.getOrderInGame());
    model.setTime(entity.getTime());
    model.setEmptyNet(entity.isEmptyNet());
    model.setAuthor(entity.getAuthor());
    if (entity.getAssists() != null) {
      model.setAssists(Arrays.asList(entity.getAssists().split(SEPARATOR)));
    }
    return model;
  }

  @Override
  public ro.szzsa.livescore.server.repository.model.Goal toEntity(Goal model) {
    ro.szzsa.livescore.server.repository.model.Goal entity = new ro.szzsa.livescore.server.repository.model.Goal();
    long id = calculateId(model);
    entity.setId(id);
    if (dao.exists(id)) {
      entity = dao.findOne(id);
    }
    entity.setType(model.getType().name());
    entity.setGameId(model.getGameId());
    entity.setTeamId(model.getTeamId());
    entity.setOrderInGame(model.getOrder());
    entity.setTime(model.getTime());
    entity.setEmptyNet(model.isEmptyNet());
    entity.setAuthor(model.getAuthor());
    if (model.getAssists() != null) {
      entity.setAssists(StringUtils.join(model.getAssists(), SEPARATOR));
    }
    return entity;
  }

  private long calculateId(Goal model) {
    return Long.parseLong(String.valueOf(model.getGameId()) + "00" + String.valueOf(model.getOrder()));
  }
}
