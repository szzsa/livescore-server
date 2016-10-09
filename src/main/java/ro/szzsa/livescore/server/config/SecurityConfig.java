package ro.szzsa.livescore.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ro.szzsa.livescore.api.admin.protocol.AdministrationApiEndpoints;
import ro.szzsa.livescore.api.device.protocol.DeviceApiEndpoints;
import ro.szzsa.livescore.api.management.protocol.ManagementApiEndpoints;
import ro.szzsa.livescore.server.controller.Constatnts;

@Configuration
@EnableWebSecurity
@Import(AuthProviderConfig.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private static final String MANAGEMENT_ROLE = "MANAGEMENT";

  private static final String ADMIN_ROLE = "ADMIN";

  private static final String SUB_PATHS = "/**";

  private final UserDetailsService userDetailsService;

  @Autowired
  public SecurityConfig(UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordencoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .authorizeRequests()
        .antMatchers(Constatnts.KEEP_ALIVE_URL).permitAll()
        .antMatchers(DeviceApiEndpoints.DEVICE_API_ROOT_PATH + SUB_PATHS).permitAll()
        .antMatchers(ManagementApiEndpoints.MANAGEMENT_API_ROOT_PATH + SUB_PATHS).hasRole(MANAGEMENT_ROLE)
        .antMatchers(AdministrationApiEndpoints.ADMINISTRATION_API_ROOT_PATH + SUB_PATHS).hasRole(ADMIN_ROLE)
        .and().httpBasic()
        .and().requiresChannel()
        .antMatchers(Constatnts.KEEP_ALIVE_URL).requiresInsecure()
        .antMatchers(DeviceApiEndpoints.DEVICE_API_ROOT_PATH + SUB_PATHS).requiresInsecure()
        .antMatchers(ManagementApiEndpoints.MANAGEMENT_API_ROOT_PATH + SUB_PATHS).requiresSecure()
        .antMatchers(AdministrationApiEndpoints.ADMINISTRATION_API_ROOT_PATH + SUB_PATHS).requiresSecure()
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  @Bean
  public PasswordEncoder passwordencoder() {
    return new BCryptPasswordEncoder();
  }
}
