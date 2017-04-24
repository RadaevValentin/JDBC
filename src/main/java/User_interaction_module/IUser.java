package User_interaction_module;

/**
 * Created by YFJ on 18.04.2017.
 */
public interface IUser {
    void create(String driver, String url, String name, String password);
    String getUrl();
    String getName();
    String getPassword();
    String getDriver();
}
