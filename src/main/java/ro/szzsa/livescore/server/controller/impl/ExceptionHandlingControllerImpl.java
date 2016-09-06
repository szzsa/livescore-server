package ro.szzsa.livescore.server.controller.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import ro.szzsa.livescore.server.controller.ExceptionHandlingController;

/**
 *
 */
@ControllerAdvice
public class ExceptionHandlingControllerImpl implements ExceptionHandlingController {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlingControllerImpl.class);

  @ExceptionHandler(NoHandlerFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @Override
  public void handleNotFoundMapping() {
  }

  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
  @Override
  public void handleNotSupportedMediaType() {
    LOGGER.error("Media type not supported");
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
  @Override
  public void handleNotSupportedMethod() {
    LOGGER.error("Method not allowed");
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @Override
  public void handleInternalError(Exception e) {
    LOGGER.error("Something went wrong", e);
  }
}
