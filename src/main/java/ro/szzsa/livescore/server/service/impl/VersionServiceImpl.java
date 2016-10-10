package ro.szzsa.livescore.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.szzsa.livescore.server.repository.dao.ApplicationDao;
import ro.szzsa.livescore.server.repository.model.Application;
import ro.szzsa.livescore.server.service.VersionService;

/**
 *
 */
@Service
public class VersionServiceImpl implements VersionService {

  private static final long DEFAULT_APP_INFO_ID = 0L;
  private static final int DEFAULT_APP_VERSION = 13;
  private final ApplicationDao dao;

  @Autowired
  public VersionServiceImpl(ApplicationDao dao) {
    this.dao = dao;
  }

  @Override
  public int getVersion() {
    Application entity = dao.findOne(DEFAULT_APP_INFO_ID);
    if (entity == null) {
      entity = new Application();
      entity.setId(DEFAULT_APP_INFO_ID);
      entity.setVersion(DEFAULT_APP_VERSION);
      dao.save(entity);
    }
    return entity.getVersion();
  }
}
