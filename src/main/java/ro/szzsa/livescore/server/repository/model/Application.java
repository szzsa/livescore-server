package ro.szzsa.livescore.server.repository.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 */
@Entity
public class Application {

  @Id
  private long id;

  private int version;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }
}
