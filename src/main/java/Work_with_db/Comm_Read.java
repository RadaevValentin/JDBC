package Work_with_db;

import Business_logic.ConnectionDb;
import Business_logic.Employee;
import User_interaction_module.IUser;
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
    private IUser user;

    public Comm_Read(IUser user){
        this.user = user;
    }

    @Override
    public void execute() {
        String s = "yes";
        try {
            while (s.equalsIgnoreCase("yes")) {
                System.out.println("Insert empno:");
                String q = user.getConnection().getReader().readLine();
                Employee e = new Employee(Integer.parseInt(q));
                System.out.println("Do you want to find employee with empno " + e.getEmpno());
                if (user.getConnection().getReader().readLine().equalsIgnoreCase("yes")) {
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
                    List<Employee> emp = query.query(user.getConnection().getConn(), SQL, res, e.getEmpno());
                    for(Employee em: emp){
                        System.out.println("Employees found : " + em.toString());
                    }
                }
                else{break;}
                System.out.println("Do you want to continue?");
                s = user.getConnection().getReader().readLine();
            }
        }
        catch (IOException | SQLException e1) {
            e1.printStackTrace();
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
