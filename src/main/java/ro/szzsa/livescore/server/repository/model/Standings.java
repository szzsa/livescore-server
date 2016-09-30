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
public class Standings {

  @Id
  private long id;

  private long leaguePhaseId;

  @OneToMany(mappedBy = "standingsId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Set<TeamStats> stats;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getLeaguePhaseId() {
    return leaguePhaseId;
  }

  public void setLeaguePhaseId(long leaguePhaseId) {
    this.leaguePhaseId = leaguePhaseId;
  }

  public Set<TeamStats> getStats() {
    return stats;
  }

  public void setStats(Set<TeamStats> stats) {
    this.stats = stats;
  }
}
