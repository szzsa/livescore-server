package ro.szzsa.livescore.server.repository.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 */
@Entity
public class Penalty {

  @Id
  private int id;

  private String types;

  private String gameId;

  private String player;

  private String teamCode;

  private String time;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTypes() {
    return types;
  }

  public void setTypes(String types) {
    this.types = types;
  }

  public String getGameId() {
    return gameId;
  }

  public void setGameId(String gameId) {
    this.gameId = gameId;
  }

  public String getPlayer() {
    return player;
  }

  public void setPlayer(String player) {
    this.player = player;
  }

  public String getTeamCode() {
    return teamCode;
  }

  public void setTeamCode(String teamCode) {
    this.teamCode = teamCode;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }
}
