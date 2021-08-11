package cc.sybx.example.conf;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author : yuanzp
 * @Date : 2021/8/11 下午8:05
 */
@Configuration
@MapperScan(basePackages = "cc.sybx.example.app.mapper", sqlSessionTemplateRef = "sqlSessionTemplate")
public class SybxDataSourceConfig {

    @Value("${sybx.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "sybx.datasource.sybx")
    public DataSource buildDataSource(){
        return DataSourceBuilder.create().type(this.dataSourceType).build();
    }

    @Primary
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory buildSqlSessionFactory(@Qualifier("dataSource") final DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis/mybatis-config.xml"));
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/sybx/*.xml"));
        return bean.getObject();
    }

    @Primary
    @Bean(name = "transactionManager")
    public DataSourceTransactionManager buildTransactionManager(@Qualifier("dataSource") final DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate buildSqlSessionTemplate(@Qualifier("sqlSessionFactory") final SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
