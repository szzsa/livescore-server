package ro.szzsa.livescore.server.repository.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 */
@Entity
public class LeaguePhase {

  @Id
  private long id;

  private String title;

  private boolean active;

  private boolean isPlayoff;

  private int seriesLimit;

  private String places;

  private int numberOfTeams;

  @OneToMany(mappedBy = "leaguePhaseId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Set<Standings> standingsSet;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public boolean isPlayoff() {
    return isPlayoff;
  }

  public void setPlayoff(boolean playoff) {
    isPlayoff = playoff;
  }

  public int getSeriesLimit() {
    return seriesLimit;
  }

  public void setSeriesLimit(int seriesLimit) {
    this.seriesLimit = seriesLimit;
  }

  public String getPlaces() {
    return places;
  }

  public void setPlaces(String places) {
    this.places = places;
  }

  public int getNumberOfTeams() {
    return numberOfTeams;
  }

  public void setNumberOfTeams(int numberOfTeams) {
    this.numberOfTeams = numberOfTeams;
  }

  public Set<Standings> getStandingsSet() {
    return standingsSet;
  }

  public void setStandingsSet(Set<Standings> standingsSet) {
    this.standingsSet = standingsSet;
  }
}
