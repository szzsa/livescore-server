package ro.szzsa.livescore.server.service.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.szzsa.livescore.model.LeaguePhase;
import ro.szzsa.livescore.server.repository.dao.LeaguePhaseDao;

/**
 *
 */
@Service
public class LeaguePhaseConverter
    implements Converter<LeaguePhase, ro.szzsa.livescore.server.repository.model.LeaguePhase> {

  private final LeaguePhaseDao dao;

  private final StandingsCollectionConverter standingsCollectionConverter;

  @Autowired
  public LeaguePhaseConverter(LeaguePhaseDao dao,
                              StandingsConverter standingsConverter) {
    this.dao = dao;
    standingsCollectionConverter = new StandingsCollectionConverter(standingsConverter);
  }

  @Override
  public LeaguePhase toModel(ro.szzsa.livescore.server.repository.model.LeaguePhase entity) {
    if (entity == null) {
      return null;
    }
    LeaguePhase model = new LeaguePhase();
    model.setId(entity.getId());
    model.setTitle(entity.getTitle());
    model.setActive(entity.isActive());
    model.setPlayoff(entity.isPlayoff());
    model.setPlaces(entity.getPlaces());
    model.setSeriesLimit(entity.getSeriesLimit());
    model.setNumberOfTeams(entity.getNumberOfTeams());
    model.setStandingsList(standingsCollectionConverter.toModel(entity.getStandingsSet()));
    return model;
  }

  @Override
  public ro.szzsa.livescore.server.repository.model.LeaguePhase toEntity(LeaguePhase model) {
    ro.szzsa.livescore.server.repository.model.LeaguePhase entity =
        new ro.szzsa.livescore.server.repository.model.LeaguePhase();
    entity.setId(model.getId());
    if (dao.exists(model.getId())) {
      entity = dao.findOne(model.getId());
    }
    entity.setTitle(model.getTitle());
    entity.setActive(model.isActive());
    entity.setPlayoff(model.isPlayoff());
    entity.setPlaces(model.getPlaces());
    entity.setSeriesLimit(model.getSeriesLimit());
    entity.setNumberOfTeams(model.getNumberOfTeams());
    entity.setStandingsSet(standingsCollectionConverter.toEntity(model.getStandingsList()));
    return entity;
  }
}
