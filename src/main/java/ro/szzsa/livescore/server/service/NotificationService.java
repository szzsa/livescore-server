package ro.szzsa.livescore.server.service;

import ro.szzsa.livescore.model.Game;

/**
 *
 */
public interface NotificationService {

  void sendNotification(Game game);
}
