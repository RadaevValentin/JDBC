package Business_logic;

import User_interaction_module.IUser;
import org.apache.commons.dbutils.DbUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

/**
 * Created by YFJ on 23.04.2017.
 */
public class ConnectionDb {
    private Connection conn;

    public Connection getConn() {
        return conn;
    }
    public void setConn(Connection conn) {
        this.conn = conn;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getDriver() {
        return driver;
    }
    public void setDriver(String driver) {
        this.driver = driver;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    private String url;
    private String driver;
    private String login;
    private String password;
    public ConnectionDb(IUser user){
        this.driver = user.getDriver();
        this.url = user.getUrl();
        this.login = user.getName();
        this.password = user.getPassword();
    }
    public Connection connect() {
        try {
            Locale.setDefault(Locale.ENGLISH);
            DbUtils.loadDriver(driver);
            conn = DriverManager.getConnection(url, login, password);
            System.out.println("Connection succesful.");
        } catch (SQLException se) {
            se.printStackTrace();
            System.out.println("Error! Check input data and try again.");
        }
        return conn;
    }

    public void disconnect() {
        if(conn != null){
            DbUtils.closeQuietly(conn);
            System.out.println("Connection closed.");
        }
        else{
            System.out.println("There was no connection.");
        }
    }
}
