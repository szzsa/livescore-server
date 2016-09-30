package ro.szzsa.livescore.server.service.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ro.szzsa.livescore.model.LeaguePhase;
import ro.szzsa.livescore.model.Standings;
import ro.szzsa.livescore.server.repository.dao.LeaguePhaseDao;

/**
 *
 */
@Service
public class LeaguePhaseConverter
    implements DaoConverter<LeaguePhase, ro.szzsa.livescore.server.repository.model.LeaguePhase> {

  private final LeaguePhaseDao dao;

  private final StandingsConverter standingsConverter;

  @Autowired
  public LeaguePhaseConverter(LeaguePhaseDao dao,
                              StandingsConverter standingsConverter) {
    this.dao = dao;
    this.standingsConverter = standingsConverter;
  }

  @Override
  public LeaguePhase toModel(ro.szzsa.livescore.server.repository.model.LeaguePhase entity) {
    if (entity == null) {
      return null;
    }
    LeaguePhase leaguePhase = new LeaguePhase();
    leaguePhase.setId(entity.getId());
    leaguePhase.setTitle(entity.getTitle());
    leaguePhase.setActive(entity.isActive());
    leaguePhase.setPlayoff(entity.isPlayoff());
    leaguePhase.setPlaces(entity.getPlaces());
    leaguePhase.setSeriesLimit(entity.getSeriesLimit());
    leaguePhase.setNumberOfTeams(entity.getNumberOfTeams());
    leaguePhase.setStandingsList(convertEntitiesToStandings(entity.getStandingsSet()));
    return leaguePhase;
  }

  @Override
  public ro.szzsa.livescore.server.repository.model.LeaguePhase toEntity(LeaguePhase leaguePhase) {
    ro.szzsa.livescore.server.repository.model.LeaguePhase entity =
        new ro.szzsa.livescore.server.repository.model.LeaguePhase();
    entity.setId(leaguePhase.getId());
    if (dao.exists(leaguePhase.getId())) {
      entity = dao.findOne(leaguePhase.getId());
    }
    entity.setTitle(leaguePhase.getTitle());
    entity.setActive(leaguePhase.isActive());
    entity.setPlayoff(leaguePhase.isPlayoff());
    entity.setPlaces(leaguePhase.getPlaces());
    entity.setSeriesLimit(leaguePhase.getSeriesLimit());
    entity.setNumberOfTeams(leaguePhase.getNumberOfTeams());
    entity.setStandingsSet(convertStandingsListToEntities(leaguePhase.getStandingsList()));
    return entity;
  }

  private List<Standings> convertEntitiesToStandings(
      Set<ro.szzsa.livescore.server.repository.model.Standings> entities) {
    if (entities == null) {
      return null;
    }
    List<Standings> stats = new ArrayList<>(entities.size());
    entities.forEach(teamStats -> stats.add(standingsConverter.toModel(teamStats)));
    return stats;
  }

  private Set<ro.szzsa.livescore.server.repository.model.Standings> convertStandingsListToEntities(
      List<Standings> stats) {
    if (stats == null) {
      return null;
    }
    Set<ro.szzsa.livescore.server.repository.model.Standings> entities = new HashSet<>(stats.size());
    stats.forEach(teamStats -> entities.add(standingsConverter.toEntity(teamStats)));
    return entities;
  }
}
