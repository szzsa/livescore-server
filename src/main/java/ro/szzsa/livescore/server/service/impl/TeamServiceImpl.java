package ro.szzsa.livescore.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.szzsa.livescore.model.IceRink;
import ro.szzsa.livescore.model.Team;
import ro.szzsa.livescore.server.repository.dao.TeamDao;
import ro.szzsa.livescore.server.service.TeamService;

/**
 *
 */
@Service
public class TeamServiceImpl implements TeamService {

  private final TeamDao dao;

  @Autowired
  public TeamServiceImpl(TeamDao dao) {
    this.dao = dao;
  }

  @Override
  public List<Team> getTeams() {
    return convertTeams(dao.findAll());
  }

  private List<Team> convertTeams(Iterable<ro.szzsa.livescore.server.repository.model.Team> entities) {
    List<Team> teams = new ArrayList<>();
    entities.forEach(team -> teams.add(convertTeam(team)));
    return teams;
  }

  private Team convertTeam(ro.szzsa.livescore.server.repository.model.Team entity) {
    Team team = new Team();
    team.setCode(entity.getCode());
    team.setName(entity.getName());
    team.setCity(entity.getCity());
    team.setCountry(entity.getCountry());
    team.setYearFounded(entity.getYearFounded());
    team.setTimeZone(entity.getTimeZone());
    team.setIceRink(convertIceRink(entity.getIceRink()));
    team.setHomeColor(entity.getName());
    team.setAwayColor(entity.getName());
    return team;
  }

  private IceRink convertIceRink(ro.szzsa.livescore.server.repository.model.IceRink entity) {
    IceRink iceRink = new IceRink();
    iceRink.setName(entity.getName());
    iceRink.setAddress(entity.getAddress());
    iceRink.setCapacity(entity.getCapacity());
    iceRink.setLatitude(entity.getLatitude());
    iceRink.setLongitude(entity.getLongitude());
    return iceRink;
  }
}
