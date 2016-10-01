package ro.szzsa.livescore.server.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.szzsa.livescore.api.device.protocol.DeviceApiEndpoints;
import ro.szzsa.livescore.api.device.protocol.request.GameSyncRequest;
import ro.szzsa.livescore.api.device.protocol.request.VersionSyncRequest;
import ro.szzsa.livescore.api.device.protocol.response.GameSyncResponse;
import ro.szzsa.livescore.api.device.protocol.response.StatsSyncResponse;
import ro.szzsa.livescore.api.device.protocol.response.VersionSyncResponse;
import ro.szzsa.livescore.server.controller.DeviceApiV1Controller;
import ro.szzsa.livescore.server.service.GameService;
import ro.szzsa.livescore.server.service.LeaguePhasesService;
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

  private final LeaguePhasesService leaguePhasesService;

  private final TeamService teamService;

  private final VersionService versionService;

  private final Converter converter;

  @Autowired
  public DeviceApiV1ControllerImpl(GameService gameService,
                                   LeaguePhasesService leaguePhasesService,
                                   TeamService teamService,
                                   VersionService versionService) {
    this.gameService = gameService;
    this.leaguePhasesService = leaguePhasesService;
    this.teamService = teamService;
    this.versionService = versionService;
    converter = Converters.createJsonConverter();
  }

  @PostMapping(value = DeviceApiEndpoints.SYNC_GAME_PATH,
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public String syncGame(@RequestBody String gameSyncRequest) {
    GameSyncRequest request = converter.fromString(gameSyncRequest, GameSyncRequest.class);
    GameSyncResponse response = new GameSyncResponse();
    response.setGame(gameService.getGame(request.getGameId()));
    return converter.toString(response);
  }

  @PostMapping(value = DeviceApiEndpoints.SYNC_STATS_PATH,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public String syncStats() {
    StatsSyncResponse response = new StatsSyncResponse();
    response.setGames(gameService.getGames());
    response.setLeaguePhases(leaguePhasesService.getLeaguePhases());
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
