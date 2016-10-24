package ro.szzsa.livescore.server.repository.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 */
@Entity
public class Goal {

  @Id
  private long id;

  private long gameId;

  private long teamId;

  private int orderInGame;

  private String type;

  private String author;

  private String assists;

  private String time;

  private boolean isEmptyNet;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getGameId() {
    return gameId;
  }

  public void setGameId(long gameId) {
    this.gameId = gameId;
  }

  public long getTeamId() {
    return teamId;
  }

  public void setTeamId(long teamId) {
    this.teamId = teamId;
  }

  public int getOrderInGame() {
    return orderInGame;
  }

  public void setOrderInGame(int orderInGame) {
    this.orderInGame = orderInGame;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getAssists() {
    return assists;
  }

  public void setAssists(String assists) {
    this.assists = assists;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public boolean isEmptyNet() {
    return isEmptyNet;
  }

  public void setEmptyNet(boolean emptyNet) {
    isEmptyNet = emptyNet;
  }
}
