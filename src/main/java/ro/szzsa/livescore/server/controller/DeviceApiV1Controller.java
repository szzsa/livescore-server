package ro.szzsa.livescore.server.controller;

import ro.szzsa.utils.converter.ConverterException;

/**
 *
 */
public interface DeviceApiV1Controller {

  String getGameDetails(String gameDetailsRequest) throws ConverterException;

  String getStats() throws ConverterException;

  String syncVersion(String versionSyncRequest) throws ConverterException;
}
