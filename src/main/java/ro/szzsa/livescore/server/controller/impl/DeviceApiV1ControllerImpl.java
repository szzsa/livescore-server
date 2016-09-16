package ro.szzsa.livescore.server.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.szzsa.livescore.api.device.protocol.DeviceApiEndpoints;
import ro.szzsa.livescore.api.device.protocol.request.GameDetailsRequest;
import ro.szzsa.livescore.api.device.protocol.request.VersionSyncRequest;
import ro.szzsa.livescore.api.device.protocol.response.GameDetailsResponse;
import ro.szzsa.livescore.api.device.protocol.response.StatsSyncResponse;
import ro.szzsa.livescore.api.device.protocol.response.VersionSyncResponse;
import ro.szzsa.livescore.server.controller.DeviceApiV1Controller;
import ro.szzsa.livescore.server.service.GameService;
import ro.szzsa.livescore.server.service.StandingsService;
import ro.szzsa.livescore.server.service.TeamService;
import ro.szzsa.livescore.server.service.VersionService;
import ro.szzsa.utils.converter.Converter;
import ro.szzsa.utils.converter.Converters;

/**
 *
 */
@RestController
@RequestMapping(DeviceApiEndpoints.DEVICE_API_V1_ROOT_PATH)
public class DeviceApiV1ControllerImpl implements DeviceApiV1Controller {

  private final GameService gameService;

  private final StandingsService standingsService;

  private final TeamService teamService;

  private final VersionService versionService;

  private final Converter converter;

  @Autowired
  public DeviceApiV1ControllerImpl(GameService gameService,
                                   StandingsService standingsService,
                                   TeamService teamService,
                                   VersionService versionService) {
    this.gameService = gameService;
    this.standingsService = standingsService;
    this.teamService = teamService;
    this.versionService = versionService;
    converter = Converters.createJsonConverter();
  }

  @PostMapping(value = DeviceApiEndpoints.GET_GAME_DETAILS_PATH,
    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public String getGameDetails(@RequestBody String gameDetailsRequest) {
    GameDetailsRequest request = converter.fromString(gameDetailsRequest, GameDetailsRequest.class);
    GameDetailsResponse response = new GameDetailsResponse();
    response.setGame(gameService.getGame(request.getGameId()));
    return converter.toString(response);
  }

  @PostMapping(value = DeviceApiEndpoints.GET_STATS_PATH,
    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public String getStats() {
    StatsSyncResponse response = new StatsSyncResponse();
    response.setGames(gameService.getGames());
    response.setStandings(standingsService.getStandings());
    response.setTeams(teamService.getTeams());
    return converter.toString(response);
  }

  @PostMapping(value = DeviceApiEndpoints.SYNC_VERSION_PATH,
    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public String syncVersion(@RequestBody String versionSyncRequest) {
    VersionSyncRequest request = converter.fromString(versionSyncRequest, VersionSyncRequest.class);
    VersionSyncResponse response = new VersionSyncResponse();
    response.setUpdateApp(request.getAppVersion() < versionService.getVersion());
    return converter.toString(response);
  }
}
