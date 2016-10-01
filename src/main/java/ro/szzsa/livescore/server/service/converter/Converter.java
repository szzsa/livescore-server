package ro.szzsa.livescore.server.service.converter;

/**
 *
 */
public interface Converter<Model, Entity> {

  Model toModel(Entity entity);

  Entity toEntity(Model model);
}
