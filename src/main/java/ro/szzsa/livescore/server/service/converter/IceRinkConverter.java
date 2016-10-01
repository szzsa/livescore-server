package ro.szzsa.livescore.server.service.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.szzsa.livescore.model.IceRink;
import ro.szzsa.livescore.server.repository.dao.IceRinkDao;

/**
 *
 */
@Service
public class IceRinkConverter implements Converter<IceRink, ro.szzsa.livescore.server.repository.model.IceRink> {

  private final IceRinkDao dao;

  @Autowired
  public IceRinkConverter(IceRinkDao dao) {
    this.dao = dao;
  }

  @Override
  public IceRink toModel(ro.szzsa.livescore.server.repository.model.IceRink entity) {
    if (entity == null) {
      return null;
    }
    IceRink model = new IceRink();
    model.setId(entity.getId());
    model.setName(entity.getName());
    model.setCapacity(entity.getCapacity());
    model.setAddress(entity.getAddress());
    model.setLatitude(entity.getLatitude());
    model.setLongitude(entity.getLongitude());
    return model;
  }

  @Override
  public ro.szzsa.livescore.server.repository.model.IceRink toEntity(IceRink model) {
    ro.szzsa.livescore.server.repository.model.IceRink entity =
        new ro.szzsa.livescore.server.repository.model.IceRink();
    entity.setId(model.getId());
    if (dao.exists(model.getId())) {
      entity = dao.findOne(model.getId());
    }
    entity.setName(model.getName());
    entity.setCapacity(model.getCapacity());
    entity.setAddress(model.getAddress());
    entity.setLatitude(model.getLatitude());
    entity.setLongitude(model.getLongitude());
    return entity;
  }
}
