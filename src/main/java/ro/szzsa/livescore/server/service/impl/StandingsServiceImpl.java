package ro.szzsa.livescore.server.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.szzsa.livescore.model.Standings;
import ro.szzsa.livescore.model.TeamStats;
import ro.szzsa.livescore.server.repository.dao.StandingsDao;
import ro.szzsa.livescore.server.service.StandingsService;

/**
 *
 */
@Service
public class StandingsServiceImpl implements StandingsService {

  private final StandingsDao dao;

  @Autowired
  public StandingsServiceImpl(StandingsDao dao) {
    this.dao = dao;
  }

  @Override
  public List<Standings> getStandings() {
    return convertStandingsList(dao.findAll());
  }

  private List<Standings> convertStandingsList(Iterable<ro.szzsa.livescore.server.repository.model.Standings> entities) {
    List<Standings> standingsList = new ArrayList<>();
    entities.forEach(standings -> standingsList.add(convertStandings(standings)));
    return standingsList;
  }

  private Standings convertStandings(ro.szzsa.livescore.server.repository.model.Standings entity) {
    Standings standings = new Standings();
    standings.setId(entity.getId());
    standings.setActive(entity.isActive());
    standings.setGroup(entity.isGroup());
    standings.setPlaces(entity.getPlaces());
    standings.setSeriesLimit(entity.getSeriesLimit());
    standings.setStats(convertStatsList(entity.getStats()));
    standings.setTitle(entity.getTitle());
    return standings;
  }

  private List<TeamStats> convertStatsList(Set<ro.szzsa.livescore.server.repository.model.TeamStats> entities) {
    List<TeamStats> statsList = new ArrayList<>(entities.size());
    entities.forEach(teamStats -> statsList.add(convertStats(teamStats)));
    return statsList;
  }

  private TeamStats convertStats(ro.szzsa.livescore.server.repository.model.TeamStats entity) {
    TeamStats stats = new TeamStats();
    stats.setTeamCode(entity.getTeamCode());
    stats.setGamesPlayed(entity.getGamesPlayed());
    stats.setGoalsAgainst(entity.getGoalsAgainst());
    stats.setGoalsFor(entity.getGoalsFor());
    stats.setLosses(entity.getLosses());
    stats.setOvertimeLosses(entity.getOvertimeLosses());
    stats.setOvertimeWins(entity.getOvertimeWins());
    stats.setPlace(entity.getPlace());
    stats.setPoints(entity.getPoints());
    stats.setStandingsId(entity.getStandingsId());
    stats.setWins(entity.getWins());
    return stats;
  }
}
