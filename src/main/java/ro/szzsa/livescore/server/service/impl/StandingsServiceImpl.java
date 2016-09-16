package ro.szzsa.livescore.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.szzsa.livescore.model.Standings;
import ro.szzsa.livescore.server.repository.dao.StandingsDao;
import ro.szzsa.livescore.server.service.StandingsService;
import ro.szzsa.livescore.server.service.converter.StandingsConverter;

/**
 *
 */
@Service
public class StandingsServiceImpl implements StandingsService {

  private final StandingsDao dao;

  private final StandingsConverter converter;

  @Autowired
  public StandingsServiceImpl(StandingsDao dao,
                              StandingsConverter converter) {
    this.dao = dao;
    this.converter = converter;
  }

  @Override
  public List<Standings> getStandings() {
    return convertStandingsList(dao.findAll());
  }

  @Override
  public void updateStandings(Standings standings) {
    dao.save(converter.toEntity(standings));
  }

  private List<Standings> convertStandingsList(Iterable<ro.szzsa.livescore.server.repository.model.Standings> entities) {
    List<Standings> standingsList = new ArrayList<>();
    entities.forEach(standings -> standingsList.add(converter.toModel(standings)));
    return standingsList;
  }
}
