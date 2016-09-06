package ro.szzsa.livescore.server.controller;

/**
 *
 */
public interface ExceptionHandlingController {

  void handleNotFoundMapping();

  void handleNotSupportedMediaType();

  void handleNotSupportedMethod();

  void handleInternalError(Exception e);
}
