package ro.szzsa.livescore.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.szzsa.livescore.server.repository.dao.ApplicationInfoDao;
import ro.szzsa.livescore.server.repository.model.ApplicationInfo;
import ro.szzsa.livescore.server.service.ApplicationInfoService;

/**
 *
 */
@Service
public class ApplicationInfoServiceImpl implements ApplicationInfoService {

  private static final long DEFAULT_APP_INFO_ID = 0L;
  private static final int DEFAULT_APP_VERSION = 0;
  private static final String DEFAULT_API_KEY = "API key";
  private final ApplicationInfoDao dao;

  @Autowired
  public ApplicationInfoServiceImpl(ApplicationInfoDao dao) {
    this.dao = dao;
  }

  @Override
  public int getVersion() {
    return retrieveApplicationInfo().getVersion();
  }

  @Override
  public String getApiKey() {
    return retrieveApplicationInfo().getApiKey();
  }

  private ApplicationInfo retrieveApplicationInfo() {
    ApplicationInfo entity = dao.findOne(DEFAULT_APP_INFO_ID);
    if (entity == null) {
      entity = new ApplicationInfo();
      entity.setId(DEFAULT_APP_INFO_ID);
      entity.setVersion(DEFAULT_APP_VERSION);
      entity.setApiKey(DEFAULT_API_KEY);
      dao.save(entity);
    }
    return entity;
  }
}
