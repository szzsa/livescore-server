package ro.szzsa.livescore.server.service.converter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 */
public abstract class CollectionConverter<Model, Entity> implements Converter<List<Model>, Set<Entity>> {

  private final Converter<Model, Entity> converter;

  public CollectionConverter(Converter<Model, Entity> converter) {
    this.converter = converter;
  }

  @Override
  public List<Model> toModel(Set<Entity> entities) {
    if (entities == null) {
      return null;
    }
    List<Model> models = new ArrayList<>(entities.size());
    entities.forEach(entity -> models.add(converter.toModel(entity)));
    return models;
  }

  @Override
  public Set<Entity> toEntity(List<Model> models) {
    if (models == null) {
      return null;
    }
    Set<Entity> entities = new HashSet<>(models.size());
    models.forEach(model -> entities.add(converter.toEntity(model)));
    return entities;
  }
}
