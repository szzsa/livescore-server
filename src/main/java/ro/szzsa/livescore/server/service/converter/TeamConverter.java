package ro.szzsa.livescore.server.service.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.szzsa.livescore.model.Team;
import ro.szzsa.livescore.server.repository.dao.TeamDao;

/**
 *
 */
@Service
public class TeamConverter implements DaoConverter<Team, ro.szzsa.livescore.server.repository.model.Team> {

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
    Team team = new Team();
    team.setCode(entity.getCode());
    team.setName(entity.getName());
    team.setCity(entity.getCity());
    team.setCountry(entity.getCountry());
    team.setYearFounded(entity.getYearFounded());
    team.setTimeZone(entity.getTimeZone());
    team.setIceRink(iceRinkConverter.toModel(entity.getIceRink()));
    team.setHomeColor(entity.getHomeColor());
    team.setAwayColor(entity.getAwayColor());
    return team;
  }

  @Override
  public ro.szzsa.livescore.server.repository.model.Team toEntity(Team team) {
    ro.szzsa.livescore.server.repository.model.Team entity = new ro.szzsa.livescore.server.repository.model.Team();
    entity.setCode(team.getCode());
    if (dao.exists(team.getCode())) {
      entity = dao.findOne(team.getCode());
    }
    entity.setName(team.getName());
    entity.setCity(team.getCity());
    entity.setCountry(team.getCountry());
    entity.setYearFounded(team.getYearFounded());
    entity.setTimeZone(team.getTimeZone());
    entity.setIceRink(iceRinkConverter.toEntity(team.getIceRink()));
    entity.setHomeColor(team.getHomeColor());
    entity.setAwayColor(team.getAwayColor());
    return entity;
  }
}
