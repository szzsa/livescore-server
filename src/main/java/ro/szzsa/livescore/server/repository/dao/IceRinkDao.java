package ro.szzsa.livescore.server.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.szzsa.livescore.server.repository.model.IceRink;

/**
 *
 */
public interface IceRinkDao extends JpaRepository<IceRink, String> {
}
