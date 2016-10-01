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
    implements Converter<TeamStats, ro.szzsa.livescore.server.repository.model.TeamStats> {

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
    TeamStats model = new TeamStats();
    model.setTeamId(entity.getTeamId());
    model.setGamesPlayed(entity.getGamesPlayed());
    model.setGoalsAgainst(entity.getGoalsAgainst());
    model.setGoalsFor(entity.getGoalsFor());
    model.setLosses(entity.getLosses());
    model.setOvertimeLosses(entity.getOvertimeLosses());
    model.setOvertimeWins(entity.getOvertimeWins());
    model.setPlace(entity.getPlace());
    model.setPoints(entity.getPoints());
    model.setStandingsId(entity.getStandingsId());
    model.setWins(entity.getWins());
    return model;
  }

  @Override
  public ro.szzsa.livescore.server.repository.model.TeamStats toEntity(TeamStats model) {
    ro.szzsa.livescore.server.repository.model.TeamStats entity =
        new ro.szzsa.livescore.server.repository.model.TeamStats();
    long id = calculateId(model);
    entity.setId(id);
    if (dao.exists(id)) {
      entity = dao.findOne(id);
    }
    entity.setTeamId(model.getTeamId());
    entity.setGamesPlayed(model.getGamesPlayed());
    entity.setGoalsAgainst(model.getGoalsAgainst());
    entity.setGoalsFor(model.getGoalsFor());
    entity.setLosses(model.getLosses());
    entity.setOvertimeLosses(model.getOvertimeLosses());
    entity.setOvertimeWins(model.getOvertimeWins());
    entity.setPlace(model.getPlace());
    entity.setPoints(model.getPoints());
    entity.setStandingsId(model.getStandingsId());
    entity.setWins(model.getWins());
    return entity;
  }

  private long calculateId(TeamStats model) {
    return Long.parseLong(String.valueOf(model.getStandingsId()) + "00" + String.valueOf(model.getTeamId()));
  }
}
