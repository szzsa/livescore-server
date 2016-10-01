package ro.szzsa.livescore.server.service;

import java.util.List;

import ro.szzsa.livescore.model.LeaguePhase;

/**
 *
 */
public interface LeaguePhasesService {

  List<LeaguePhase> getLeaguePhases();

  void updateLeaguePhase(LeaguePhase leaguePhase);

  void updateLeaguePhases(List<LeaguePhase> leaguePhases);
}
