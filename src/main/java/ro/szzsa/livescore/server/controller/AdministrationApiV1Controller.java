package ro.szzsa.livescore.server.controller;

/**
 *
 */
public interface AdministrationApiV1Controller {

  String getTeams();

  void updateTeam(String teamUpdateRequest);

  void deleteTeam(String teamUpdateRequest);

  String getIceRinks();

  void updateIceRink(String iceRinkUpdateRequest);

  void deleteIceRink(String iceRinkUpdateRequest);
}
