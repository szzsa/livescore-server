package ro.szzsa.livescore.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import ro.szzsa.livescore.model.LeaguePhase;
import ro.szzsa.livescore.server.repository.dao.LeaguePhaseDao;
import ro.szzsa.livescore.server.service.LeaguePhasesService;
import ro.szzsa.livescore.server.service.converter.LeaguePhaseConverter;

/**
 *
 */
@Service
public class LeaguePhasesServiceImpl implements LeaguePhasesService {

  private final LeaguePhaseDao dao;

  private final LeaguePhaseConverter converter;

  @Autowired
  public LeaguePhasesServiceImpl(LeaguePhaseDao dao,
                                 LeaguePhaseConverter converter) {
    this.dao = dao;
    this.converter = converter;
  }

  @Override
  public List<LeaguePhase> getLeaguePhases() {
    return convertLeaguePhases(dao.findAll());
  }

  @Override
  public void updateLeaguePhase(LeaguePhase leaguePhase) {
    if (leaguePhase == null) {
      return;
    }
    dao.save(converter.toEntity(leaguePhase));
  }

  @Override
  public void updateLeaguePhases(List<LeaguePhase> leaguePhases) {
    if (leaguePhases == null) {
      return;
    }
    leaguePhases.forEach(this::updateLeaguePhase);
  }

  private List<LeaguePhase> convertLeaguePhases(
      Iterable<ro.szzsa.livescore.server.repository.model.LeaguePhase> entities) {
    List<LeaguePhase> leaguePhases = new ArrayList<>();
    entities.forEach(standings -> leaguePhases.add(converter.toModel(standings)));
    return leaguePhases;
  }
}
