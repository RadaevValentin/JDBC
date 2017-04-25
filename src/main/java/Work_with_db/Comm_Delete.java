package Work_with_db;

import Business_logic.ConnectionDb;
import Business_logic.Employee;
import User_interaction_module.IUser;
import org.apache.commons.dbutils.QueryRunner;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Your Friend Jesus on 16.04.2017.
 */
public class Comm_Delete implements IServer_command {
    private IUser user;


    public Comm_Delete(IUser user){
        this.user = user;
    }

    @Override
    public void execute() {
        String s = "yes";
        try {
            while (s.equalsIgnoreCase("yes")) {
                System.out.println("Insert empno:");
                Employee e = new Employee(Integer.parseInt(user.getConnection().getReader().readLine()));
                String SQL = "DELETE FROM emp WHERE empno = ?";
                System.out.println("Do you want to delete employee with empno " + e.getEmpno());
                if (user.getConnection().getReader().readLine().equalsIgnoreCase("yes")) {
                    QueryRunner query = new QueryRunner();
                    query.update(user.getConnection().getConn(), SQL, e.getEmpno());
                    System.out.println("Employee successfully deleted.");
                }
                else{break;}
                System.out.println("Do you want to continue?");
                s = user.getConnection().getReader().readLine();
            }
        }
        catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
