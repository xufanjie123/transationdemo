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
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.fanjiexu.mapper.order"}, sqlSessionFactoryRef = "sqlSessionFactoryOrder")
public class DBOrderConfig {

    public static final String MAPPER_LOCATION = "classpath:mapping/orders/*.xml";

    @Bean(name = "dborder")
    @ConfigurationProperties(prefix = "spring.datasource.dborders") // application.properteis中对应属性的前缀
    public DataSource dataSource2() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "sqlSessionFactoryOrder")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("dborder") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        Resource[] resource = new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION);
        bean.setMapperLocations(resource);
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Bean(name = "transactionManagerOrder")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("dborder") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "sqlSessionTemplateOrder")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("sqlSessionFactoryOrder") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
