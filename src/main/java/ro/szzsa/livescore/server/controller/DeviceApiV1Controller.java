package ro.szzsa.livescore.server.controller;

/**
 *
 */
public interface DeviceApiV1Controller {

  String getGameDetails();

  String getStats();

  String syncVersion();
}
