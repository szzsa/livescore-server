package ro.szzsa.livescore.server.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan({"ro.szzsa.livescore.server.controller",
                "ro.szzsa.livescore.server.service",
                "ro.szzsa.livescore.server.dao"})
@Import(SecurityConfig.class)
public class WebMvcConfig extends WebMvcConfigurerAdapter {
}
