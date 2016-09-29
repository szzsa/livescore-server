package ro.szzsa.livescore.server.service.impl;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import ro.szzsa.livescore.api.device.protocol.notification.NotificationConstants;
import ro.szzsa.livescore.model.Game;
import ro.szzsa.livescore.server.model.notification.Data;
import ro.szzsa.livescore.server.model.notification.Notification;
import ro.szzsa.livescore.server.service.NotificationService;
import ro.szzsa.utils.connector.Connector;
import ro.szzsa.utils.connector.HttpConnectorBuilder;
import ro.szzsa.utils.connector.Request;
import ro.szzsa.utils.connector.exception.ConnectorException;
import ro.szzsa.utils.converter.Converter;
import ro.szzsa.utils.converter.Converters;

/**
 *
 */
@Service
public class NotificationServiceImpl implements NotificationService {

  private static final String FCM_SEND_URL = "https://fcm.googleapis.com/fcm/send";

  private static final String STATS_TOPIC = "/topics/" + NotificationConstants.STATS_TOPIC_NAME;

  private static final String API_KEY = "AIzaSyB3NhdQSWGcOHn0CSAfip4h11AcXjw95hM";

  private final static Logger LOGGER = Logger.getLogger(NotificationServiceImpl.class);

  private final Converter converter;

  private final Connector connector;

  public NotificationServiceImpl() {
    this.converter = Converters.createJsonConverter();
    this.connector = new HttpConnectorBuilder(API_KEY).build();
  }

  @Async
  @Override
  public void sendNotification(Game game) {
    Notification notification = new Notification();
    notification.setTo(STATS_TOPIC);
    notification.setData(new Data());
    notification.getData().setMessage(converter.toString(game));

    try {
      connector.sendRequest(new Request(FCM_SEND_URL, converter.toString(notification)));
    } catch (ConnectorException e) {
      LOGGER.error("Cannot send notification", e);
    }
  }
}
