package ro.szzsa.livescore.server.service.converter;

import ro.szzsa.livescore.model.TeamStats;

/**
 *
 */
public class TeamStatsCollectionConverter
    extends CollectionConverter<TeamStats, ro.szzsa.livescore.server.repository.model.TeamStats> {

  public TeamStatsCollectionConverter(
      Converter<TeamStats, ro.szzsa.livescore.server.repository.model.TeamStats> converter) {
    super(converter);
  }
}
