package ro.szzsa.livescore.server.service.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.szzsa.livescore.model.Standings;
import ro.szzsa.livescore.server.repository.dao.StandingsDao;

/**
 *
 */
@Service
public class StandingsConverter
    implements Converter<Standings, ro.szzsa.livescore.server.repository.model.Standings> {

  private final StandingsDao dao;

  private final TeamStatsCollectionConverter teamStatsCollectionConverter;

  @Autowired
  public StandingsConverter(StandingsDao dao,
                            TeamStatsConverter statsConverter) {
    this.dao = dao;
    teamStatsCollectionConverter = new TeamStatsCollectionConverter(statsConverter);
  }

  @Override
  public Standings toModel(ro.szzsa.livescore.server.repository.model.Standings entity) {
    if (entity == null) {
      return null;
    }
    Standings model = new Standings();
    model.setId(entity.getId());
    model.setLeaguePhaseId(entity.getLeaguePhaseId());
    model.setStats(teamStatsCollectionConverter.toModel(entity.getStats()));
    return model;
  }

  @Override
  public ro.szzsa.livescore.server.repository.model.Standings toEntity(Standings model) {
    ro.szzsa.livescore.server.repository.model.Standings entity =
        new ro.szzsa.livescore.server.repository.model.Standings();
    entity.setId(model.getId());
    if (dao.exists(model.getId())) {
      entity = dao.findOne(model.getId());
    }
    entity.setLeaguePhaseId(model.getLeaguePhaseId());
    entity.setStats(teamStatsCollectionConverter.toEntity(model.getStats()));
    return entity;
  }
}
