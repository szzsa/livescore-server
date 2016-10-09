package ro.szzsa.livescore.server.controller.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ro.szzsa.livescore.server.controller.Constatnts;
import ro.szzsa.livescore.server.controller.KeepAliveController;

/**
 *
 */
@Controller
@RequestMapping(Constatnts.KEEP_ALIVE_URL)
public class KeepAliveControllerImpl implements KeepAliveController {

  @GetMapping
  @ResponseBody
  @Override
  public String keepAlive() {
    return "I'm alive!";
  }
}
