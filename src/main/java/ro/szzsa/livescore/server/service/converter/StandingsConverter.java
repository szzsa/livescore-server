package ro.szzsa.livescore.server.service.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ro.szzsa.livescore.model.Standings;
import ro.szzsa.livescore.model.TeamStats;
import ro.szzsa.livescore.server.repository.dao.StandingsDao;

/**
 *
 */
@Service
public class StandingsConverter
    implements DaoConverter<Standings, ro.szzsa.livescore.server.repository.model.Standings> {

  private final StandingsDao dao;

  private final TeamStatsConverter statsConverter;

  @Autowired
  public StandingsConverter(StandingsDao dao,
                            TeamStatsConverter statsConverter) {
    this.dao = dao;
    this.statsConverter = statsConverter;
  }

  @Override
  public Standings toModel(ro.szzsa.livescore.server.repository.model.Standings entity) {
    if (entity == null) {
      return null;
    }
    Standings standings = new Standings();
    standings.setId(entity.getId());
    standings.setLeaguePhaseId(entity.getLeaguePhaseId());
    standings.setStats(convertEntitiesToStats(entity.getStats()));
    return standings;
  }

  @Override
  public ro.szzsa.livescore.server.repository.model.Standings toEntity(Standings standings) {
    ro.szzsa.livescore.server.repository.model.Standings entity =
        new ro.szzsa.livescore.server.repository.model.Standings();
    entity.setId(standings.getId());
    if (dao.exists(standings.getId())) {
      entity = dao.findOne(standings.getId());
    }
    entity.setLeaguePhaseId(standings.getLeaguePhaseId());
    entity.setStats(convertStatsToEntities(standings.getStats()));
    return entity;
  }

  private List<TeamStats> convertEntitiesToStats(Set<ro.szzsa.livescore.server.repository.model.TeamStats> entities) {
    if (entities == null) {
      return null;
    }
    List<TeamStats> stats = new ArrayList<>(entities.size());
    entities.forEach(teamStats -> stats.add(statsConverter.toModel(teamStats)));
    return stats;
  }

  private Set<ro.szzsa.livescore.server.repository.model.TeamStats> convertStatsToEntities(List<TeamStats> stats) {
    if (stats == null) {
      return null;
    }
    Set<ro.szzsa.livescore.server.repository.model.TeamStats> entities = new HashSet<>(stats.size());
    stats.forEach(teamStats -> entities.add(statsConverter.toEntity(teamStats)));
    return entities;
  }
}
