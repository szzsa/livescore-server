package ro.szzsa.livescore.server.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.szzsa.livescore.api.management.protocol.ManagementApiEndpoints;
import ro.szzsa.livescore.api.management.protocol.request.GameDetailsUpdateRequest;
import ro.szzsa.livescore.api.management.protocol.request.GamesUpdateRequest;
import ro.szzsa.livescore.api.management.protocol.request.StandingsUpdateRequest;
import ro.szzsa.livescore.server.controller.ManagementApiV1Controller;
import ro.szzsa.livescore.server.service.GameService;
import ro.szzsa.livescore.server.service.StandingsService;
import ro.szzsa.utils.converter.Converter;
import ro.szzsa.utils.converter.Converters;

/**
 *
 */
@RestController
@RequestMapping(ManagementApiEndpoints.MANAGEMENT_API_V1_ROOT_PATH)
public class ManagementApiV1ControllerImpl implements ManagementApiV1Controller {

  private final GameService gameService;

  private final StandingsService standingsService;

  private final Converter converter = Converters.createJsonConverter();

  @Autowired
  public ManagementApiV1ControllerImpl(GameService gameService,
                                       StandingsService standingsService) {
    this.gameService = gameService;
    this.standingsService = standingsService;
  }

  @PostMapping(value = ManagementApiEndpoints.UPDATE_STANDINGS_PATH,
    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void updateStandings(@RequestBody String standingsUpdateRequest) {
    StandingsUpdateRequest request = converter.fromString(standingsUpdateRequest, StandingsUpdateRequest.class);
    standingsService.updateStandings(request.getStandings());
  }

  @PostMapping(value = ManagementApiEndpoints.UPDATE_GAMES_PATH,
    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void updateGames(@RequestBody String gamesUpdateRequest) {
    GamesUpdateRequest request = converter.fromString(gamesUpdateRequest, GamesUpdateRequest.class);
    gameService.updateGames(request.getGames());
  }

  @PostMapping(value = ManagementApiEndpoints.UPDATE_GAME_DETAILS_PATH,
    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void updateGameDetails(@RequestBody String gameDetailsUpdateRequest) {
    GameDetailsUpdateRequest request = converter.fromString(gameDetailsUpdateRequest, GameDetailsUpdateRequest.class);
    gameService.updateGame(request.getGameDetails());
  }
}
