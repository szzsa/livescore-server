package ro.szzsa.livescore.server.service.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.szzsa.livescore.model.Team;
import ro.szzsa.livescore.server.repository.dao.TeamDao;

/**
 *
 */
@Service
public class TeamConverter implements Converter<Team, ro.szzsa.livescore.server.repository.model.Team> {

  private final TeamDao dao;

  private final IceRinkConverter iceRinkConverter;

  @Autowired
  public TeamConverter(TeamDao dao,
                       IceRinkConverter iceRinkConverter) {
    this.dao = dao;
    this.iceRinkConverter = iceRinkConverter;
  }

  @Override
  public Team toModel(ro.szzsa.livescore.server.repository.model.Team entity) {
    if (entity == null) {
      return null;
    }
    Team model = new Team();
    model.setId(entity.getId());
    model.setCode(entity.getCode());
    model.setName(entity.getName());
    model.setCity(entity.getCity());
    model.setCountry(entity.getCountry());
    model.setYearFounded(entity.getYearFounded());
    model.setTimeZone(entity.getTimeZone());
    model.setIceRink(iceRinkConverter.toModel(entity.getIceRink()));
    model.setHomeColor(entity.getHomeColor());
    model.setAwayColor(entity.getAwayColor());
    return model;
  }

  @Override
  public ro.szzsa.livescore.server.repository.model.Team toEntity(Team model) {
    ro.szzsa.livescore.server.repository.model.Team entity = new ro.szzsa.livescore.server.repository.model.Team();
    entity.setId(model.getId());
    if (dao.exists(model.getId())) {
      entity = dao.findOne(model.getId());
    }
    entity.setCode(model.getCode());
    entity.setName(model.getName());
    entity.setCity(model.getCity());
    entity.setCountry(model.getCountry());
    entity.setYearFounded(model.getYearFounded());
    entity.setTimeZone(model.getTimeZone());
    entity.setIceRink(iceRinkConverter.toEntity(model.getIceRink()));
    entity.setHomeColor(model.getHomeColor());
    entity.setAwayColor(model.getAwayColor());
    return entity;
  }
}
