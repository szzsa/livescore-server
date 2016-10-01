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
import ro.szzsa.livescore.api.management.protocol.request.LeaguePhaseUpdateRequest;
import ro.szzsa.livescore.server.controller.ManagementApiV1Controller;
import ro.szzsa.livescore.server.service.GameService;
import ro.szzsa.livescore.server.service.LeaguePhasesService;
import ro.szzsa.utils.converter.Converter;
import ro.szzsa.utils.converter.Converters;

/**
 *
 */
@RestController
@RequestMapping(ManagementApiEndpoints.MANAGEMENT_API_V1_ROOT_PATH)
public class ManagementApiV1ControllerImpl implements ManagementApiV1Controller {

  private final GameService gameService;

  private final LeaguePhasesService leaguePhasesService;

  private final Converter converter = Converters.createJsonConverter();

  @Autowired
  public ManagementApiV1ControllerImpl(GameService gameService,
                                       LeaguePhasesService leaguePhasesService) {
    this.gameService = gameService;
    this.leaguePhasesService = leaguePhasesService;
  }

  @PostMapping(value = ManagementApiEndpoints.UPDATE_LEAGUE_PHASES_PATH,
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void updateLeaguePhases(@RequestBody String leaguePhasesUpdateRequest) {
    LeaguePhaseUpdateRequest request = converter.fromString(leaguePhasesUpdateRequest, LeaguePhaseUpdateRequest.class);
    leaguePhasesService.updateLeaguePhases(request.getLeaguePhases());
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
  public void updateGame(@RequestBody String gameUpdateRequest) {
    GameDetailsUpdateRequest request = converter.fromString(gameUpdateRequest, GameDetailsUpdateRequest.class);
    gameService.updateGame(request.getGameDetails());
  }
}
