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
public class PenaltyConverter implements DaoConverter<Penalty, ro.szzsa.livescore.server.repository.model.Penalty> {

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
    Penalty penalty = new Penalty();
    penalty.setGameId(entity.getGameId());
    penalty.setTeamId(entity.getTeamId());
    penalty.setTime(entity.getTime());
    penalty.setPlayer(entity.getPlayer());
    penalty.setType(PenaltyType.valueOf(entity.getType()));
    return penalty;
  }

  @Override
  public ro.szzsa.livescore.server.repository.model.Penalty toEntity(Penalty penalty) {
    ro.szzsa.livescore.server.repository.model.Penalty entity =
        new ro.szzsa.livescore.server.repository.model.Penalty();
    long id = calculateId(penalty);
    entity.setId(id);
    if (dao.exists(id)) {
      entity = dao.findOne(id);
    }
    entity.setGameId(penalty.getGameId());
    entity.setTeamId(penalty.getTeamId());
    entity.setTime(penalty.getTime());
    entity.setPlayer(penalty.getPlayer());
    entity.setType(penalty.getType().name());
    return entity;
  }

  private long calculateId(Penalty penalty) {
    return Long.parseLong(String.valueOf(penalty.getGameId()) + "00" + String.valueOf(penalty.getOrder()));
  }
}
