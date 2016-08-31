package ro.szzsa.livescore.server;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 */
@EnableWebMvc
@Configuration
@ComponentScan({"ro.szzsa.livescore.server"})
public class SpringWebConfig extends WebMvcConfigurerAdapter {
}