package ro.szzsa.livescore.server.service.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.szzsa.livescore.model.TeamStats;
import ro.szzsa.livescore.server.repository.dao.TeamStatsDao;

/**
 *
 */
@Service
public class TeamStatsConverter
    implements DaoConverter<TeamStats, ro.szzsa.livescore.server.repository.model.TeamStats> {

  private final TeamStatsDao dao;

  @Autowired
  public TeamStatsConverter(TeamStatsDao dao) {
    this.dao = dao;
  }

  @Override
  public TeamStats toModel(ro.szzsa.livescore.server.repository.model.TeamStats entity) {
    if (entity == null) {
      return null;
    }
    TeamStats stats = new TeamStats();
    stats.setTeamId(entity.getTeamId());
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

  @Override
  public ro.szzsa.livescore.server.repository.model.TeamStats toEntity(TeamStats teamStats) {
    ro.szzsa.livescore.server.repository.model.TeamStats entity =
        new ro.szzsa.livescore.server.repository.model.TeamStats();
    long id = calculateId(teamStats);
    entity.setId(id);
    if (dao.exists(id)) {
      entity = dao.findOne(id);
    }
    entity.setTeamId(teamStats.getTeamId());
    entity.setGamesPlayed(teamStats.getGamesPlayed());
    entity.setGoalsAgainst(teamStats.getGoalsAgainst());
    entity.setGoalsFor(teamStats.getGoalsFor());
    entity.setLosses(teamStats.getLosses());
    entity.setOvertimeLosses(teamStats.getOvertimeLosses());
    entity.setOvertimeWins(teamStats.getOvertimeWins());
    entity.setPlace(teamStats.getPlace());
    entity.setPoints(teamStats.getPoints());
    entity.setStandingsId(teamStats.getStandingsId());
    entity.setWins(teamStats.getWins());
    return entity;
  }

  private long calculateId(TeamStats teamStats) {
    return Long.parseLong(String.valueOf(teamStats.getStandingsId()) + "00" + String.valueOf(teamStats.getTeamId()));
  }
}
