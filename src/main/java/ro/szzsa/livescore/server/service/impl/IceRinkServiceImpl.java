package ro.szzsa.livescore.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import ro.szzsa.livescore.model.IceRink;
import ro.szzsa.livescore.server.repository.dao.IceRinkDao;
import ro.szzsa.livescore.server.service.IceRinkService;
import ro.szzsa.livescore.server.service.converter.IceRinkConverter;

/**
 *
 */
@Service
public class IceRinkServiceImpl implements IceRinkService {

  private final IceRinkDao dao;

  private final IceRinkConverter converter;

  @Autowired
  public IceRinkServiceImpl(IceRinkDao dao,
                            IceRinkConverter converter) {
    this.dao = dao;
    this.converter = converter;
  }

  @Override
  public List<IceRink> getIceRinks() {
    return convertIceRinks(dao.findAll());
  }

  @Override
  public void updateIceRink(IceRink iceRink) {
    if (iceRink == null) {
      return;
    }
    dao.save(converter.toEntity(iceRink));
  }

  @Override
  public void deleteIceRink(IceRink iceRink) {
    if (iceRink == null) {
      return;
    }
    if (dao.exists(iceRink.getId())) {
      dao.delete(iceRink.getId());
    }
  }

  private List<IceRink> convertIceRinks(Iterable<ro.szzsa.livescore.server.repository.model.IceRink> entities) {
    List<IceRink> iceRinks = new ArrayList<>();
    entities.forEach(iceRink -> iceRinks.add(converter.toModel(iceRink)));
    return iceRinks;
  }
}
