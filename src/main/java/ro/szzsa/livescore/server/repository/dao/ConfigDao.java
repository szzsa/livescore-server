package ro.szzsa.livescore.server.repository.dao;

import org.springframework.data.repository.CrudRepository;

import ro.szzsa.livescore.server.repository.model.Config;

/**
 *
 */
public interface ConfigDao extends CrudRepository<Config, Long> {
}
