package ro.szzsa.livescore.server.service.impl;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import ro.szzsa.livescore.api.device.server.NotificationException;
import ro.szzsa.livescore.api.device.server.NotificationSender;
import ro.szzsa.livescore.model.Game;
import ro.szzsa.livescore.server.service.NotificationService;

/**
 *
 */
@Service
public class NotificationServiceImpl implements NotificationService {

  private static final String API_KEY = "";

  private final static Logger LOGGER = Logger.getLogger(NotificationServiceImpl.class);

  @Async
  @Override
  public void sendNotification(Game game) {
    try {
      new NotificationSender().sendGameUpdate(API_KEY, game);
    } catch (NotificationException e) {
      LOGGER.error("Cannot send notification", e);
    }
  }
}
