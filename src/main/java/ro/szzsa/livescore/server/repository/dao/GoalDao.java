package ro.szzsa.livescore.server.repository.dao;

import org.springframework.data.repository.CrudRepository;

import ro.szzsa.livescore.server.repository.model.Goal;

/**
 *
 */
public interface GoalDao extends CrudRepository<Goal, Integer> {
}
