package cn.ruiyeclub.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.quartz.SchedulerException;
import org.quartz.utils.ConnectionProvider;

import java.sql.Connection;
import java.sql.SQLException;

@Data
public class DruidConnectionProvider implements ConnectionProvider {

    /**
     * JDBC驱动
     */
    public String driver;
    /**
     * JDBC连接串
     */
    public String URL;
    /**
     * 数据库用户名
     */
    public String user;
    /**
     * 数据库用户密码
     */
    public String password;
    /**
     * 数据库最大连接数
     */
    public int maxConnection;
    /**
     * 数据库SQL查询每次连接返回执行到连接池，以确保它仍然是有效的。
     */
    public String validationQuery;

    private boolean validateOnCheckout;

    private int idleConnectionValidationSeconds;

    public String maxCachedStatementsPerConnection;

    private String discardIdleConnectionsSeconds;

    public static final int DEFAULT_DB_MAX_CONNECTIONS = 10;

    public static final int DEFAULT_DB_MAX_CACHED_STATEMENTS_PER_CONNECTION = 120;

    //Druid连接池
    private DruidDataSource datasource;

    @Override
    public Connection getConnection() throws SQLException {
        return datasource.getConnection();
    }

    @Override
    public void shutdown() throws SQLException {
        datasource.close();
    }

    @Override
    public void initialize() throws SQLException {
        if (this.URL == null) {
            throw new SQLException("DBPool could not be created: DB URL cannot be null");
        }

        if (this.driver == null) {
            throw new SQLException("DBPool driver could not be created: DB driver class name cannot be null!");
        }

        if (this.maxConnection < 0) {
            throw new SQLException("DBPool maxConnectins could not be created: Max connections must be greater than zero!");
        }

        datasource = new DruidDataSource();
        try{
            datasource.setDriverClassName(this.driver);
        } catch (Exception e) {
            try {
                throw new SchedulerException("Problem setting driver class name on datasource: " + e.getMessage(), e);
            } catch (SchedulerException e1) {
            }
        }

        datasource.setUrl(this.URL);
        datasource.setUsername(this.user);
        datasource.setPassword(this.password);
        datasource.setMaxActive(this.maxConnection);
        datasource.setMinIdle(1);
        datasource.setMaxWait(0);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(DEFAULT_DB_MAX_CONNECTIONS);

        if (this.validationQuery != null) {
            datasource.setValidationQuery(this.validationQuery);
            if(!this.validateOnCheckout) {
                datasource.setTestOnReturn(true);
            }
            else {
                datasource.setTestOnBorrow(true);
            }
            datasource.setValidationQueryTimeout(this.idleConnectionValidationSeconds);
        }
    }

}