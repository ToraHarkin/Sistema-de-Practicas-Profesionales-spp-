package spp.data.connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.commons.dbcp2.BasicDataSource;
import java.time.Duration;

/**
 * Implementing a JDBC connection pool using Apache DBCP2's, apply the Singleton
 * pattern to ensure a single instance of the pool. You can change the Singleton
 * pattern properties and database properties from: (src/main/resources)
 *
 */
public class ConnectionPool {

    private static ConnectionPool singleInstancePool;
    private final BasicDataSource basicDataSource;

    /**
     * Initializes the connection pool from the external configuration.
     * @throw SQLEception if an error occurs while loading the configuration
     * or if the properties are invalid 
     */   
    private ConnectionPool() throws SQLException {
        
        Properties properties = loadProperties();
        validateProperties(properties);
        
        basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(properties.getProperty("db.driver"));
        basicDataSource.setUrl(properties.getProperty("db.url"));
        basicDataSource.setUsername(properties.getProperty("db.user"));
        basicDataSource.setPassword(properties.getProperty("db.password"));
        basicDataSource.setMinIdle(Integer.parseInt(properties.getProperty("db.minIdle")));
        basicDataSource.setMaxIdle(Integer.parseInt(properties.getProperty("db.maxIdle")));
        basicDataSource.setMaxTotal(Integer.parseInt(properties.getProperty("db.maxTotal")));
        basicDataSource.setMaxWait(Duration.ofMillis(
                Long.parseLong(properties.getProperty("db.maxWaitMillis"))
        ));
    }
    
    /**
     * Application of the singleton pattern:
     * there cannot be more than one instance of the pool
     * @return single instance of ConnectionPool
     * @throws SQLException if an error occurs while initializing the pool
     */
    public static ConnectionPool getInstanceConectionPool() throws SQLException {
        
        if (singleInstancePool == null) {
            singleInstancePool = new ConnectionPool();
        }
        return singleInstancePool;
    }   
    
    /**
     * @return active connection to the database
     * @throws SQLException if connection cannot be established
     */
    public Connection getConnectionPool() throws SQLException {
        
        return basicDataSource.getConnection(); 
    }
    
    /**
     * 
     * @return object Properties with the loaded configuration (db.properties)
     * @throws SQLException if the file is not found or cannot be read
     */
    private Properties loadProperties() throws SQLException {
        
        Properties properties = new Properties();
        try(InputStream input = getClass()
                .getClassLoader()
                .getResourceAsStream("db.properties")) {
            
            if (input == null) {     
                throw new SQLException("No se encontró db.properties en resources"); 
            }
            
            properties.load(input);
            
        } catch(IOException e) {
            throw new SQLException("Error al cargar db.properties", e);
        }
        
        return properties;
    }
    
    /**
     * 
     * @param properties properties charged
     * @throws SQLException if any required property is missing
     */
    private void validateProperties(Properties properties) throws SQLException {
        
        String[] required = {
            "db.driver",
            "db.url",
            "db.user",
            "db.password",
            "db.minIdle",
            "db.maxIdle",
            "db.maxTotal", 
            "db.maxWaitMillis"
        };
        
        for (String key : required) { 
            if (properties.getProperty(key) == null) {
                throw new SQLException("Falta propiedad requerida: " + key);
            } 
        }
    }
}
