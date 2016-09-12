package ro.szzsa.livescore.server.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.szzsa.livescore.server.repository.model.Goal;

/**
 *
 */
public interface GoalDao extends JpaRepository<Goal, Long> {
}
