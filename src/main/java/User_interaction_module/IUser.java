package User_interaction_module;

import Business_logic.ConnectionDb;
import Work_with_db.IServer_command;

/**
 * Created by YFJ on 18.04.2017.
 */
public interface IUser {
    void create(String driver, String url, String name, String password);
    String getUrl();
    String getName();
    String getPassword();
    String getDriver();
    void execute();
    void setComm(IServer_command c);
    ConnectionDb getConnection();
}
