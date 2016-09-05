package ro.szzsa.livescore.server.controller.impl;

import java.util.ArrayList;

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
import ro.szzsa.livescore.model.Game;
import ro.szzsa.livescore.server.controller.DeviceApiV1Controller;
import ro.szzsa.livescore.server.converter.Converter;
import ro.szzsa.livescore.server.converter.Converters;
import ro.szzsa.livescore.server.service.GameDetailsService;

/**
 *
 */
@RestController
@RequestMapping(DeviceApiEndpoints.DEVICE_API_V1_ROOT_PATH)
public class DeviceApiV1ControllerImpl implements DeviceApiV1Controller {

  private final Converter converter = Converters.createJsonConverter();

  private final GameDetailsService gameDetailsService;

  @Autowired
  public DeviceApiV1ControllerImpl(GameDetailsService gameDetailsService) {
    this.gameDetailsService = gameDetailsService;
  }

  @PostMapping(value = DeviceApiEndpoints.GET_GAME_DETAILS_PATH,
    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public String getGameDetails(@RequestBody String gameDetailsRequest) {
    GameDetailsRequest request = converter.fromString(gameDetailsRequest, GameDetailsRequest.class);
    GameDetailsResponse response = new GameDetailsResponse();
    response.setGame(new Game());
    return converter.toString(response);
  }

  @PostMapping(value = DeviceApiEndpoints.GET_STATS_PATH,
    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public String getStats() {
    StatsSyncResponse response = new StatsSyncResponse();
    response.setGames(new ArrayList<>());
    response.setStandings(new ArrayList<>());
    response.setTeams(new ArrayList<>());
    return converter.toString(response);
  }

  @PostMapping(value = DeviceApiEndpoints.SYNC_VERSION_PATH,
    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public String syncVersion(@RequestBody String versionSyncRequest) {
    VersionSyncRequest request = converter.fromString(versionSyncRequest, VersionSyncRequest.class);
    VersionSyncResponse response = new VersionSyncResponse();
    response.setUpdateApp(request.getAppVersion() < 4);
    return converter.toString(response);
  }
}
