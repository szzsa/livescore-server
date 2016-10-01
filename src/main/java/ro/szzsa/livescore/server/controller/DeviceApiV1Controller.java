package ro.szzsa.livescore.server.controller;

/**
 *
 */
public interface DeviceApiV1Controller {

  String syncGame(String gameSyncRequest);

  String syncStats();

  String syncVersion(String versionSyncRequest);
}
