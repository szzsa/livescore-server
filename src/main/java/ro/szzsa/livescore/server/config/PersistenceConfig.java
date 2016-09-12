package ro.szzsa.livescore.server.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

/**
 *
 */
@Configuration
@EnableJpaRepositories(
  basePackages = "ro.szzsa.livescore.server.repository.dao",
  entityManagerFactoryRef = "entityManager"
)
public class PersistenceConfig {

  private static final String STATS_DATASOURCE_JNDI_NAME = "java:jboss/datasources/StatsDS";

  @Bean
  @Primary
  public LocalContainerEntityManagerFactoryBean entityManager() {
    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(dataSource());
    em.setPackagesToScan("ro.szzsa.livescore.server.repository.model");

    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setGenerateDdl(true);
    em.setJpaVendorAdapter(vendorAdapter);

    return em;
  }

  @Bean
  @Primary
  public DataSource dataSource() {
    JndiDataSourceLookup lookup = new JndiDataSourceLookup();
    return lookup.getDataSource(STATS_DATASOURCE_JNDI_NAME);
  }

  @Bean
  @Primary
  public PlatformTransactionManager transactionManager() {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManager().getObject());
    return transactionManager;
  }
}
