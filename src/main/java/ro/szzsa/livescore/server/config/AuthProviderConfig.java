package ro.szzsa.livescore.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import javax.sql.DataSource;

/**
 *
 */
@Configuration
public class AuthProviderConfig {

  private static final String USERS_BY_USERNAME_QUERY = "select username,password, enabled from users where username=?";

  private static final String AUTHORITIES_BY_USERNAME_QUERY =
      "select b.username, a.role from user_roles a, users b where b.username=? and a.userid=b.userid";

  private static final String AUTH_DATASOURCE_JNDI_NAME = "java:jboss/datasources/AuthDS";

  @Bean
  public DataSource dataSource() {
    JndiDataSourceLookup lookup = new JndiDataSourceLookup();
    return lookup.getDataSource(AUTH_DATASOURCE_JNDI_NAME);
  }

  @Bean
  public UserDetailsService userDetailsService() {
    JdbcDaoImpl jdbcImpl = new JdbcDaoImpl();
    jdbcImpl.setDataSource(dataSource());
    jdbcImpl.setUsersByUsernameQuery(USERS_BY_USERNAME_QUERY);
    jdbcImpl.setAuthoritiesByUsernameQuery(AUTHORITIES_BY_USERNAME_QUERY);
    return jdbcImpl;
  }
}
