package ro.szzsa.livescore.server.service.converter;

import ro.szzsa.livescore.model.Penalty;

/**
 *
 */
public class PenaltyCollectionConverter
    extends CollectionConverter<Penalty, ro.szzsa.livescore.server.repository.model.Penalty> {

  public PenaltyCollectionConverter(Converter<Penalty, ro.szzsa.livescore.server.repository.model.Penalty> converter) {
    super(converter);
  }
}
