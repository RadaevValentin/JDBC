package User_interaction_module;


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
    public void setDriver(String driver) {
        this.driver = driver;
    }

    private String driver;
    private String url;
    private String name;
    private String password;


    public void create(String driver, String url, String name, String password){
        this.url = url;
        this.name = name;
        this.password = password;
        this.driver = driver;
    }
}
