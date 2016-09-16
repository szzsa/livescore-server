package ro.szzsa.livescore.server.service;

import java.util.List;

import ro.szzsa.livescore.model.Team;

/**
 *
 */
public interface TeamService {

  List<Team> getTeams();

  void updateTeam(Team team);

  void deleteTeam(Team team);
}
