package Business_logic;

import User_interaction_module.IUser;
import org.apache.commons.dbutils.DbUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    public BufferedReader getReader() {
        return reader;
    }
    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    private BufferedReader reader;
    IUser user;



    public ConnectionDb(IUser user){
        this.user = user;
    }

    public Connection connect() {
        try {
            Locale.setDefault(Locale.ENGLISH);
            DbUtils.loadDriver(user.getDriver());
            conn = DriverManager.getConnection(user.getUrl(), user.getName(), user.getPassword());
            System.out.println("Connection succesful.");
            reader = new BufferedReader(new InputStreamReader(System.in));
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
        if(reader != null){
            try {
                reader.close();
                System.out.println("Reader closed.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("There was no open reader.");
        }
    }
}
