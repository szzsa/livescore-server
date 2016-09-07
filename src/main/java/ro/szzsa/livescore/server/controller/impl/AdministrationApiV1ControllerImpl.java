package ro.szzsa.livescore.server.controller.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.szzsa.livescore.api.admin.protocol.AdministrationApiEndpoints;
import ro.szzsa.livescore.server.controller.AdministrationApiV1Controller;
import ro.szzsa.utils.converter.Converter;
import ro.szzsa.utils.converter.Converters;

/**
 *
 */
@RestController
@RequestMapping(AdministrationApiEndpoints.ADMINISTRATION_API_V1_ROOT_PATH)
public class AdministrationApiV1ControllerImpl implements AdministrationApiV1Controller {

  private final Converter converter = Converters.createJsonConverter();
}
