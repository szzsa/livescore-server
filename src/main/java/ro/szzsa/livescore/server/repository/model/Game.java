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
public class Game {

  @Id
  private String id;

  private String homeTeamCode;

  private String visitorTeamCode;

  private long date;

  private String status;

  private int homeTeamScore;

  private int visitorTeamScore;

  private String time;

  @OneToMany(mappedBy = "gameId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Set<Goal> goals;

  @OneToMany(mappedBy = "gameId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Set<Penalty> penalties;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getHomeTeamCode() {
    return homeTeamCode;
  }

  public void setHomeTeamCode(String homeTeamCode) {
    this.homeTeamCode = homeTeamCode;
  }

  public String getVisitorTeamCode() {
    return visitorTeamCode;
  }

  public void setVisitorTeamCode(String visitorTeamCode) {
    this.visitorTeamCode = visitorTeamCode;
  }

  public long getDate() {
    return date;
  }

  public void setDate(long date) {
    this.date = date;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getHomeTeamScore() {
    return homeTeamScore;
  }

  public void setHomeTeamScore(int homeTeamScore) {
    this.homeTeamScore = homeTeamScore;
  }

  public int getVisitorTeamScore() {
    return visitorTeamScore;
  }

  public void setVisitorTeamScore(int visitorTeamScore) {
    this.visitorTeamScore = visitorTeamScore;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public Set<Goal> getGoals() {
    return goals;
  }

  public void setGoals(Set<Goal> goals) {
    this.goals = goals;
  }

  public Set<Penalty> getPenalties() {
    return penalties;
  }

  public void setPenalties(Set<Penalty> penalties) {
    this.penalties = penalties;
  }
}
