package ro.szzsa.livescore.server.repository.dao;

import org.springframework.data.repository.CrudRepository;

import ro.szzsa.livescore.server.repository.model.ApplicationInfo;

/**
 *
 */
public interface ApplicationInfoDao extends CrudRepository<ApplicationInfo, Long> {
}
