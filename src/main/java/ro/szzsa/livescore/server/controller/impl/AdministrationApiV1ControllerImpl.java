package ro.szzsa.livescore.server.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.szzsa.livescore.api.admin.protocol.AdministrationApiEndpoints;
import ro.szzsa.livescore.api.admin.protocol.request.IceRinkUpdateRequest;
import ro.szzsa.livescore.api.admin.protocol.request.TeamUpdateRequest;
import ro.szzsa.livescore.server.controller.AdministrationApiV1Controller;
import ro.szzsa.livescore.server.service.IceRinkService;
import ro.szzsa.livescore.server.service.TeamService;
import ro.szzsa.utils.converter.Converter;
import ro.szzsa.utils.converter.Converters;

/**
 *
 */
@RestController
@RequestMapping(AdministrationApiEndpoints.ADMINISTRATION_API_V1_ROOT_PATH)
public class AdministrationApiV1ControllerImpl implements AdministrationApiV1Controller {

  private final Converter converter = Converters.createJsonConverter();

  private final TeamService teamService;

  private final IceRinkService iceRinkService;

  @Autowired
  public AdministrationApiV1ControllerImpl(TeamService teamService,
                                           IceRinkService iceRinkService) {
    this.teamService = teamService;
    this.iceRinkService = iceRinkService;
  }

  @PostMapping(value = AdministrationApiEndpoints.GET_TEAMS_PATH,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public String getTeams() {
    return converter.toString(teamService.getTeams());
  }

  @PostMapping(value = AdministrationApiEndpoints.UPDATE_TEAM_PATH,
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void updateTeam(String teamUpdateRequest) {
    TeamUpdateRequest request = converter.fromString(teamUpdateRequest, TeamUpdateRequest.class);
    teamService.updateTeam(request.getTeam());
  }

  @PostMapping(value = AdministrationApiEndpoints.DELETE_TEAM_PATH,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void deleteTeam(String teamUpdateRequest) {
    TeamUpdateRequest request = converter.fromString(teamUpdateRequest, TeamUpdateRequest.class);
    teamService.deleteTeam(request.getTeam());
  }

  @PostMapping(value = AdministrationApiEndpoints.GET_ICE_RINKS_PATH,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public String getIceRinks() {
    return converter.toString(iceRinkService.getIceRinks());
  }

  @PostMapping(value = AdministrationApiEndpoints.UPDATE_ICE_RINK_PATH,
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void updateIceRink(String iceRinkUpdateRequest) {
    IceRinkUpdateRequest request = converter.fromString(iceRinkUpdateRequest, IceRinkUpdateRequest.class);
    iceRinkService.updateIceRink(request.getIceRink());
  }

  @PostMapping(value = AdministrationApiEndpoints.DELETE_ICE_RINK_PATH,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void deleteIceRink(String iceRinkUpdateRequest) {
    IceRinkUpdateRequest request = converter.fromString(iceRinkUpdateRequest, IceRinkUpdateRequest.class);
    iceRinkService.deleteIceRink(request.getIceRink());
  }
}
