package ro.szzsa.livescore.server.service;

import java.util.List;

import ro.szzsa.livescore.model.IceRink;

/**
 *
 */
public interface IceRinkService {

  List<IceRink> getIceRinks();

  void updateIceRink(IceRink iceRink);

  void deleteIceRink(IceRink iceRink);
}
