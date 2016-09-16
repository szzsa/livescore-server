package ro.szzsa.livescore.server.service.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.szzsa.livescore.model.IceRink;
import ro.szzsa.livescore.server.repository.dao.IceRinkDao;

/**
 *
 */
@Service
public class IceRinkConverter implements DaoConverter<IceRink, ro.szzsa.livescore.server.repository.model.IceRink> {

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
    IceRink iceRink = new IceRink();
    iceRink.setName(entity.getName());
    iceRink.setCapacity(entity.getCapacity());
    iceRink.setAddress(entity.getAddress());
    iceRink.setLatitude(entity.getLatitude());
    iceRink.setLongitude(entity.getLongitude());
    return iceRink;
  }

  @Override
  public ro.szzsa.livescore.server.repository.model.IceRink toEntity(IceRink iceRink) {
    ro.szzsa.livescore.server.repository.model.IceRink entity = new ro.szzsa.livescore.server.repository.model.IceRink();
    entity.setName(iceRink.getName());
    if (dao.exists(iceRink.getName())) {
      entity = dao.findOne(iceRink.getName());
    }
    entity.setCapacity(iceRink.getCapacity());
    entity.setAddress(iceRink.getAddress());
    entity.setLatitude(iceRink.getLatitude());
    entity.setLongitude(iceRink.getLongitude());
    return entity;
  }
}
