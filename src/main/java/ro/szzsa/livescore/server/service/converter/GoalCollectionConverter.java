package ro.szzsa.livescore.server.service.converter;

import ro.szzsa.livescore.model.Goal;

/**
 *
 */
public class GoalCollectionConverter
    extends CollectionConverter<Goal, ro.szzsa.livescore.server.repository.model.Goal> {

  public GoalCollectionConverter(Converter<Goal, ro.szzsa.livescore.server.repository.model.Goal> converter) {
    super(converter);
  }
}
