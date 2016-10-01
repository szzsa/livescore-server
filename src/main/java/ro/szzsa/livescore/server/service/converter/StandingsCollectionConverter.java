package ro.szzsa.livescore.server.service.converter;

import ro.szzsa.livescore.model.Standings;

/**
 *
 */
public class StandingsCollectionConverter
    extends CollectionConverter<Standings, ro.szzsa.livescore.server.repository.model.Standings> {

  public StandingsCollectionConverter(
      Converter<Standings, ro.szzsa.livescore.server.repository.model.Standings> converter) {
    super(converter);
  }
}
