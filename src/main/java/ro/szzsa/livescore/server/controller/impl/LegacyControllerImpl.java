package ro.szzsa.livescore.server.controller.impl;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.szzsa.livescore.server.controller.Constants;
import ro.szzsa.livescore.server.controller.LegacyController;
import ro.szzsa.utils.converter.Converter;
import ro.szzsa.utils.converter.Converters;

/**
 *
 */
@RestController
public class LegacyControllerImpl implements LegacyController {

  private final Converter converter;

  public LegacyControllerImpl() {
    this.converter = Converters.createJsonConverter();
  }

  @PostMapping(value = Constants.LEGACY_SYNC_VERSION_URL,
    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public String syncVersion(String message) {
    VersionSyncResponse response = new VersionSyncResponse();
    response.setUpdateApp(true);
    return converter.toString(response);
  }

  private class VersionSyncResponse {

    private boolean updateApp;

    private boolean updateTeams;

    public boolean isUpdateApp() {
      return updateApp;
    }

    public void setUpdateApp(boolean updateApp) {
      this.updateApp = updateApp;
    }

    public boolean isUpdateTeams() {
      return updateTeams;
    }

    public void setUpdateTeams(boolean updateTeams) {
      this.updateTeams = updateTeams;
    }
  }
}
