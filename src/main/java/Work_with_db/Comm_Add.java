package Work_with_db;


import Business_logic.ConnectionDb;
import Business_logic.Employee;
import org.apache.commons.dbutils.QueryRunner;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Your Friend Jesus on 16.04.2017.
 */
public class Comm_Add implements IServer_command {

    private BufferedReader reader;
    private ConnectionDb connection;

    public Comm_Add(BufferedReader reader, ConnectionDb c){
        this.reader = reader;
        connection = c;
    }

    @Override
    public void execute() {
        connection.connect();
        String s = "yes";
            try {
                while(s.equalsIgnoreCase("yes")) {
                    System.out.println("Insert empno:");
                    String empno_str = reader.readLine();
                    int empno = 0;
                    if(empno_str.equals("")){
                        empno = Integer.parseInt(null);
                    }
                    else {
                        empno = Integer.parseInt(empno_str);
                    }

                    System.out.println("Insert ename:");
                    String ename = reader.readLine();
                    if(ename.equals("")){
                        ename = null;
                    }

                    System.out.println("Insert job:");
                    String job = reader.readLine();
                    if(job.equals("")){
                        job = null;
                    }

                    System.out.println("Insert mgr:");
                    String mgr_str = reader.readLine();
                    int mgr = 0;
                    if(mgr_str.equals("")){
                        mgr = Integer.parseInt(null);
                    }
                    else {
                        mgr = Integer.parseInt(mgr_str);
                    }

                    System.out.println("Insert hiredate (dd.MM.yyyy):");
                    String date_str = reader.readLine();
                    java.sql.Date hiredate = null;
                    if(date_str.equals("")){
                        hiredate = null;
                    }
                    else {
                        long l = new SimpleDateFormat("dd.MM.yyyy").parse(date_str).getTime();
                        hiredate = new java.sql.Date(l);
                    }

                    System.out.println("Insert sal:");
                    String sal_str = reader.readLine();
                    int sal = 0;
                    if(sal_str.equals("")){
                        sal = Integer.parseInt(null);
                    }
                    else {
                        sal = Integer.parseInt(sal_str);
                    }

                    System.out.println("Insert comm:");
                    String comm_str = reader.readLine();
                    int comm = 0;
                    if(comm_str.equals("")){
                        comm = Integer.parseInt(null);
                    }
                    else {
                        comm = Integer.parseInt(comm_str);
                    }

                    System.out.println("Insert deptno:");
                    String deptno_str = reader.readLine();
                    int deptno = 0;
                    if(deptno_str.equals("")){
                        deptno = Integer.parseInt(null);
                    }
                    else {
                        deptno = Integer.parseInt(deptno_str);
                    }
                    Employee e = new Employee(empno, ename, job, mgr, hiredate, sal, comm, deptno);
                    System.out.println("Do you want to add " + e.toString());
                    if(reader.readLine().equalsIgnoreCase("yes")) {
                        System.out.println("========Creating new records to DB========");
                        String SQL = "INSERT INTO emp VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
                        QueryRunner query = new QueryRunner();
                        query.update(connection.getConn(), SQL, e.getEmpno(), e.getEname(), e.getJob(), e.getMgr(), e.getHiredate(), e.getSal(), e.getComm(), e.getDeptno());
                        System.out.println("Developer successfully created.\n" + e.toString());
                    }
                    else{break;}
                    System.out.println("Do you want to continue?");
                    s = reader.readLine();
                }
            } catch (IOException | SQLException | ParseException e1) {
                e1.printStackTrace();
            }
            finally {
                connection.disconnect();
            }
    }
}
