package ro.szzsa.livescore.server.service.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.szzsa.livescore.model.Penalty;
import ro.szzsa.livescore.model.PenaltyType;
import ro.szzsa.livescore.server.repository.dao.PenaltyDao;

/**
 *
 */
@Service
public class PenaltyConverter implements Converter<Penalty, ro.szzsa.livescore.server.repository.model.Penalty> {

  private final PenaltyDao dao;

  @Autowired
  public PenaltyConverter(PenaltyDao dao) {
    this.dao = dao;
  }

  @Override
  public Penalty toModel(ro.szzsa.livescore.server.repository.model.Penalty entity) {
    if (entity == null) {
      return null;
    }
    Penalty model = new Penalty();
    model.setId(entity.getId());
    model.setGameId(entity.getGameId());
    model.setTeamId(entity.getTeamId());
    model.setTime(entity.getTime());
    model.setPlayer(entity.getPlayer());
    model.setType(PenaltyType.valueOf(entity.getType()));
    return model;
  }

  @Override
  public ro.szzsa.livescore.server.repository.model.Penalty toEntity(Penalty model) {
    ro.szzsa.livescore.server.repository.model.Penalty entity =
        new ro.szzsa.livescore.server.repository.model.Penalty();
    long id = calculateId(model);
    entity.setId(id);
    if (dao.exists(id)) {
      entity = dao.findOne(id);
    }
    entity.setGameId(model.getGameId());
    entity.setTeamId(model.getTeamId());
    entity.setTime(model.getTime());
    entity.setPlayer(model.getPlayer());
    entity.setType(model.getType().name());
    return entity;
  }

  private long calculateId(Penalty model) {
    return Long.parseLong(String.valueOf(model.getGameId()) + "00" + String.valueOf(model.getOrder()));
  }
}
