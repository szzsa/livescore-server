package ro.szzsa.livescore.server.service.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.szzsa.livescore.model.Game;
import ro.szzsa.livescore.model.GameStatus;
import ro.szzsa.livescore.server.repository.dao.GameDao;

/**
 *
 */
@Service
public class GameConverter implements Converter<Game, ro.szzsa.livescore.server.repository.model.Game> {

  private final GameDao dao;

  private final GoalCollectionConverter goalCollectionConverter;

  private final PenaltyCollectionConverter penaltyCollectionConverter;

  @Autowired
  public GameConverter(GameDao dao,
                       GoalConverter goalConverter,
                       PenaltyConverter penaltyConverter) {
    this.dao = dao;
    goalCollectionConverter = new GoalCollectionConverter(goalConverter);
    penaltyCollectionConverter = new PenaltyCollectionConverter(penaltyConverter);
  }

  @Override
  public Game toModel(ro.szzsa.livescore.server.repository.model.Game entity) {
    if (entity == null) {
      return null;
    }
    Game model = new Game();
    model.setId(entity.getId());
    model.setCode(entity.getCode());
    model.setDate(entity.getDate());
    model.setStatus(GameStatus.valueOf(entity.getStatus()));
    model.setTime(entity.getTime());
    model.setHomeTeamId(entity.getHomeTeamId());
    model.setVisitorTeamId(entity.getVisitorTeamId());
    model.setHomeTeamScore(entity.getHomeTeamScore());
    model.setVisitorTeamScore(entity.getVisitorTeamScore());
    model.setGoals(goalCollectionConverter.toModel(entity.getGoals()));
    model.setPenalties(penaltyCollectionConverter.toModel(entity.getPenalties()));
    return model;
  }

  @Override
  public ro.szzsa.livescore.server.repository.model.Game toEntity(Game model) {
    ro.szzsa.livescore.server.repository.model.Game entity = new ro.szzsa.livescore.server.repository.model.Game();
    entity.setId(model.getId());
    if (dao.exists(model.getId())) {
      entity = dao.findOne(model.getId());
    }
    entity.setCode(model.getCode());
    entity.setDate(model.getDate());
    entity.setStatus(model.getStatus().name());
    entity.setTime(model.getTime());
    entity.setHomeTeamId(model.getHomeTeamId());
    entity.setVisitorTeamId(model.getVisitorTeamId());
    entity.setHomeTeamScore(model.getHomeTeamScore());
    entity.setVisitorTeamScore(model.getVisitorTeamScore());
    entity.setGoals(goalCollectionConverter.toEntity(model.getGoals()));
    entity.setPenalties(penaltyCollectionConverter.toEntity(model.getPenalties()));
    return entity;
  }
}
