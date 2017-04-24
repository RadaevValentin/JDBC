package Work_with_db;

import Business_logic.ConnectionDb;
import Business_logic.Employee;
import org.apache.commons.dbutils.QueryRunner;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Your Friend Jesus on 16.04.2017.
 */
public class Comm_Delete implements IServer_command {
    private BufferedReader reader;
    private ConnectionDb connection;

    public Comm_Delete(BufferedReader reader, ConnectionDb c){
        this.reader = reader;
        connection = c;
    }

    @Override
    public void execute() {
        connection.connect();
        String s = "yes";
        try {
            while (s.equalsIgnoreCase("yes")) {
                System.out.println("Insert empno:");
                Employee e = new Employee(Integer.parseInt(reader.readLine()));
                String SQL = "DELETE FROM emp WHERE empno = ?";
                System.out.println("Do you want to delete employee with empno " + e.getEmpno());
                if (reader.readLine().equalsIgnoreCase("yes")) {
                    QueryRunner query = new QueryRunner();
                    query.update(connection.getConn(), SQL, e.getEmpno());
                    System.out.println("Employee successfully deleted.");
                }
                else{break;}
                System.out.println("Do you want to continue?");
                s = reader.readLine();
            }
        }
        catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }
        finally {
            connection.disconnect();
        }
    }
}
