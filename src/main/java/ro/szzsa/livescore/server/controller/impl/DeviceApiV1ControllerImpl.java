package ro.szzsa.livescore.server.controller.impl;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.szzsa.livescore.api.device.protocol.DeviceApiEndpoints;
import ro.szzsa.livescore.server.controller.DeviceApiV1Controller;

/**
 *
 */
@RestController
@RequestMapping(DeviceApiEndpoints.DEVICE_API_V1_ROOT_PATH)
public class DeviceApiV1ControllerImpl implements DeviceApiV1Controller {

  @PostMapping(value = DeviceApiEndpoints.GET_GAME_DETAILS_PATH,
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public String getGameDetails() {
    return "{}";
  }

  @PostMapping(value = DeviceApiEndpoints.GET_STATS_PATH,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public String getStats() {
    return "{}";
  }

  @PostMapping(value = DeviceApiEndpoints.SYNC_VERSION_PATH,
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public String syncVersion() {
    return "{}";
  }
}
