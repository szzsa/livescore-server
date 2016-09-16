package ro.szzsa.livescore.server.controller;

/**
 *
 */
public interface ManagementApiV1Controller {

  void updateStandings(String standingsUpdateRequest);

  void updateGames(String gamesUpdateRequest);

  void updateGameDetails(String gameDetailsUpdateRequest);
}
