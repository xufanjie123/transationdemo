package com.fanjiexu.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;



@Configuration
@MapperScan(basePackages = {"com.fanjiexu.mapper.user"}, sqlSessionFactoryRef = "sqlSessionFactoryUser")
public class DBUserConfig {

    public static final String MAPPER_LOCATION = "classpath:mapping/user/*.xml";

    @Bean(name = "dbuser")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.dbuser") // application.properteis中对应属性的前缀
    public DataSource dataSource1() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "sqlSessionFactoryUser")
    @Primary
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("dbuser") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        Resource[] resource = new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION);
        bean.setMapperLocations(resource);
        return bean.getObject();
    }

    @Bean(name = "transactionManagerUser")
    @Primary
    public DataSourceTransactionManager testTransactionManager(@Qualifier("dbuser") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "sqlSessionTemplateUser")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("sqlSessionFactoryUser") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
