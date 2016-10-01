package ro.szzsa.livescore.server.controller;

/**
 *
 */
public interface ManagementApiV1Controller {

  void updateLeaguePhases(String leaguePhasesUpdateRequest);

  void updateGames(String gamesUpdateRequest);

  void updateGame(String gameUpdateRequest);
}
