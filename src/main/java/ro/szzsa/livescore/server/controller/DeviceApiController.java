package ro.szzsa.livescore.server.controller;

/**
 *
 */
public interface DeviceApiController {

  String getGameDetails();

  String getStats();

  String syncVersion();
}
