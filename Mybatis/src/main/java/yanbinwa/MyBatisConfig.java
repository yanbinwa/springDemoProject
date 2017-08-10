package yanbinwa;

import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.pool.DruidDataSourceFactory;

@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "database")
@MapperScan(basePackages="yanbinwa.mapper")
public class MyBatisConfig
{
    
    private Map<String, String> mysqlProperties;
    private Map<String, String> mybateisProperties;
    
    public void setMysqlProperties(Map<String, String> properties)
    {
        this.mysqlProperties = properties;
    }
    
    public Map<String, String> getMysqlProperties()
    {
        return this.mysqlProperties;
    }
    
    public void setMybateisProperties(Map<String, String> properties)
    {
        this.mybateisProperties = properties;
    }
    
    public Map<String, String> getMybateisProperties()
    {
        return this.mybateisProperties;
    }
    
    @Bean
    public DataSource getDataSource() throws Exception
    {
        Properties props = new Properties();
        props.setProperty(IConstant.DATABASE_DRIVER_CLASS_NAME, mysqlProperties.get(IConstant.DATABASE_DRIVER_CLASS_NAME));
        props.setProperty(IConstant.DATABASE_URL, mysqlProperties.get(IConstant.DATABASE_URL));
        props.setProperty(IConstant.DATABASE_USERNAME, mysqlProperties.get(IConstant.DATABASE_USERNAME));
        props.setProperty(IConstant.DATABASE_PASSWORD, mysqlProperties.get(IConstant.DATABASE_PASSWORD));
        String driver = mysqlProperties.get(IConstant.DATABASE_DRIVER_CLASS_NAME);
        String url = (String)mysqlProperties.get(IConstant.DATABASE_URL);
        String username = (String)mysqlProperties.get(IConstant.DATABASE_USERNAME);
        String password = (String)mysqlProperties.get(IConstant.DATABASE_PASSWORD);
        System.out.println(driver + "; " + url + "; " + username + "; " + password);
        return DruidDataSourceFactory.createDataSource(props);
    }
    
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception{
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(ds);
        fb.setTypeAliasesPackage(mybateisProperties.get(IConstant.DATABASE_TYPE_ALIASES_PACKAGE));
        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mybateisProperties.get(IConstant.DATABASE_MAPPER_LOCATIONS)));
        
        return fb.getObject();
    }
}
