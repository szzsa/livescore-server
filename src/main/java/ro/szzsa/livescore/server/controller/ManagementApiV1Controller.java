package ro.szzsa.livescore.server.controller;

import ro.szzsa.utils.converter.ConverterException;

/**
 *
 */
public interface ManagementApiV1Controller {

  void updateStandings(String standingsUpdateRequest) throws ConverterException;

  void updateGames(String gamesUpdateRequest) throws ConverterException;

  void updateGameDetails(String gameDetailsUpdateRequest) throws ConverterException;
}
