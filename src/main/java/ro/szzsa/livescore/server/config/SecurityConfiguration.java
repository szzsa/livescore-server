package ro.szzsa.livescore.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import ro.szzsa.livescore.api.admin.protocol.AdministrationApiEndpoints;
import ro.szzsa.livescore.api.device.protocol.DeviceApiEndpoints;
import ro.szzsa.livescore.api.management.protocol.ManagementApiEndpoints;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().withUser("manager").password("123456").roles("MANAGEMENT");
    auth.inMemoryAuthentication().withUser("admin").password("123456").roles("ADMIN");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .authorizeRequests()
        .antMatchers(DeviceApiEndpoints.DEVICE_API_ROOT_PATH + "/**").permitAll()
        .antMatchers(ManagementApiEndpoints.MANAGEMENT_API_ROOT_PATH + "/**").hasRole("MANAGEMENT")
        .antMatchers(AdministrationApiEndpoints.ADMINISTRATION_API_ROOT_PATH + "/**").hasRole("ADMIN")
        .and().httpBasic()
        .and().requiresChannel()
        .antMatchers(DeviceApiEndpoints.DEVICE_API_ROOT_PATH + "/**").requiresInsecure()
        .antMatchers(ManagementApiEndpoints.MANAGEMENT_API_ROOT_PATH + "/**").requiresSecure()
        .antMatchers(AdministrationApiEndpoints.ADMINISTRATION_API_ROOT_PATH + "/**").requiresSecure();
  }
}
