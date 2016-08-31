package ro.szzsa.livescore.server.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.szzsa.livescore.api.device.protocol.DeviceApiEndpoints;

/**
 *
 */
@RestController
@RequestMapping(DeviceApiEndpoints.DEVICE_API_ROOT_PATH)
public class DeviceApiController {

  @PostMapping(value = DeviceApiEndpoints.GET_GAME_DETAILS_PATH)
  public String getGameDetails() {
    return "";
  }

  @PostMapping(DeviceApiEndpoints.GET_STATS_PATH)
  public String getStats() {
    return "";
  }

  @PostMapping(DeviceApiEndpoints.SYNC_VERSION_PATH)
  public String syncVersion() {
    return "";
  }
}
