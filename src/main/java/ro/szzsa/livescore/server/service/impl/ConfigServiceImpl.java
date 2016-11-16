package ro.szzsa.livescore.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.szzsa.livescore.server.repository.dao.ConfigDao;
import ro.szzsa.livescore.server.repository.model.Config;
import ro.szzsa.livescore.server.service.ConfigService;

/**
 *
 */
@Service
public class ConfigServiceImpl implements ConfigService {

  private static final long DEFAULT_CONFIG_ID = 0L;
  private static final int DEFAULT_ANDROID_APP_VERSION = 0;
  private static final String DEFAULT_FCM_API_KEY = "FCM API key";
  private final ConfigDao dao;

  @Autowired
  public ConfigServiceImpl(ConfigDao dao) {
    this.dao = dao;
  }

  @Override
  public int getAndroidAppVersion() {
    return retrieveConfig().getAndroidAppVersion();
  }

  @Override
  public String getFCMApiKey() {
    return retrieveConfig().getFcmApiKey();
  }

  private Config retrieveConfig() {
    Config entity = dao.findOne(DEFAULT_CONFIG_ID);
    if (entity == null) {
      entity = new Config();
      entity.setId(DEFAULT_CONFIG_ID);
      entity.setAndroidAppVersion(DEFAULT_ANDROID_APP_VERSION);
      entity.setFcmApiKey(DEFAULT_FCM_API_KEY);
      dao.save(entity);
    }
    return entity;
  }
}
