package ro.szzsa.livescore.server.service;

import java.util.List;

import ro.szzsa.livescore.model.Standings;

/**
 *
 */
public interface StandingsService {

  List<Standings> getStandings();
}
