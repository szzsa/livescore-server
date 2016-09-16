package ro.szzsa.livescore.server.controller;

/**
 *
 */
public interface DeviceApiV1Controller {

  String getGameDetails(String gameDetailsRequest);

  String getStats();

  String syncVersion(String versionSyncRequest);
}
