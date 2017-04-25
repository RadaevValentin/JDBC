package User_interaction_module;


import Business_logic.ConnectionDb;
import Work_with_db.IServer_command;

/**
 * Created by YFJ on 17.04.2017.
 */
public class StandartUser implements IUser{
    public String getUrl() {
        return url;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public String getDriver() {
        return driver;
    }

    private String driver;
    private String url;
    private String name;
    private String password;
    private IServer_command comm;

    public ConnectionDb getConnection() {
        return connection;
    }

    private ConnectionDb connection = new ConnectionDb(this);

    public void create(String driver, String url, String name, String password){
        this.url = url;
        this.name = name;
        this.password = password;
        this.driver = driver;
    }

    public void setComm(IServer_command c){
        comm = c;
    }

    public void execute(){
        comm.execute();
    }
}
