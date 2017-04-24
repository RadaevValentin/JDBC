package Work_with_db;

import Business_logic.ConnectionDb;
import Business_logic.Employee;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Your Friend Jesus on 16.04.2017.
 */
public class Comm_Read implements IServer_command {
    private BufferedReader reader;
    private ConnectionDb connection;

    public Comm_Read(BufferedReader reader, ConnectionDb c){
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
                String q = reader.readLine();
                Employee e = new Employee(Integer.parseInt(q));
                System.out.println("Do you want to find employee with empno " + e.getEmpno());
                if (reader.readLine().equalsIgnoreCase("yes")) {
                    System.out.println("========Searching INFO About employee by empno========");
                    String SQL = "SELECT * FROM emp WHERE empno = ?";
                    QueryRunner query = new QueryRunner();
                    ResultSetHandler<List<Employee>> res = new ResultSetHandler<List<Employee>>() {
                        @Override
                        public List<Employee> handle(ResultSet resultSet) throws SQLException {
                            List<Employee> employees = new ArrayList<>();
                            while (resultSet.next()) {
                                employees.add(new Employee(
                                        resultSet.getInt("EMPNO"),
                                        resultSet.getString("ENAME"),
                                        resultSet.getString("JOB"),
                                        resultSet.getInt("MGR"),
                                        resultSet.getDate("HIREDATE"),
                                        resultSet.getInt("SAL"),
                                        resultSet.getInt("COMM"),
                                        resultSet.getInt("DEPTNO")));
                            }
                            return employees;
                        }
                    };
                    List<Employee> emp = query.query(connection.getConn(), SQL, res, e.getEmpno());
                    for(Employee em: emp){
                        System.out.println("Employees found : " + em.toString());
                    }
                }
                else{break;}
                System.out.println("Do you want to continue?");
                s = reader.readLine();
            }
        }
        catch (IOException | SQLException e1) {
            e1.printStackTrace();
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }
        finally {
            connection.disconnect();
        }
    }
}
