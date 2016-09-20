package ro.szzsa.livescore.server.model.notification;

/**
 *
 */
public class Notification {

  private String to;

  private Data data;

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public Data getData() {
    return data;
  }

  public void setData(Data data) {
    this.data = data;
  }
}
