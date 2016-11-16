package ro.szzsa.livescore.server.repository.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 */
@Entity
public class Config {

  @Id
  private long id;

  private int androidAppVersion;

  private String fcmApiKey;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getAndroidAppVersion() {
    return androidAppVersion;
  }

  public void setAndroidAppVersion(int androidAppVersion) {
    this.androidAppVersion = androidAppVersion;
  }

  public String getFcmApiKey() {
    return fcmApiKey;
  }

  public void setFcmApiKey(String fcmApiKey) {
    this.fcmApiKey = fcmApiKey;
  }
}
