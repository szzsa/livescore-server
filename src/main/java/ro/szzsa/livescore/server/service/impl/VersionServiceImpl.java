package ro.szzsa.livescore.server.service.impl;

import org.springframework.stereotype.Service;

import ro.szzsa.livescore.server.service.VersionService;

/**
 *
 */
@Service
public class VersionServiceImpl implements VersionService {

  @Override
  public int getVersion() {
    return 0;
  }
}
