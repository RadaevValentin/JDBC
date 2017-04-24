package Business_logic;

import java.sql.Date;

/**
 * Created by Your Friend Jesus on 16.04.2017.
 */
public class Employee {
    public int getEmpno() {
        return empno;
    }
    public void setEmpno(int empno) {
        this.empno = empno;
    }
    public String getEname() {
        return ename;
    }
    public void setEname(String ename) {
        this.ename = ename;
    }
    public String getJob() {
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }
    public int getMgr() {
        return mgr;
    }
    public void setMgr(int mgr) {
        this.mgr = mgr;
    }
    public Date getHiredate() {
        return hiredate;
    }
    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }
    public int getSal() {
        return sal;
    }
    public void setSal(int sal) {
        this.sal = sal;
    }
    public int getComm() {
        return comm;
    }
    public void setComm(int comm) {
        this.comm = comm;
    }
    public int getDeptno() {
        return deptno;
    }
    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    private int empno;
    private String ename;
    private String job;
    private int mgr;
    private Date hiredate;
    private int sal;
    private int comm;
    private int deptno;
    public Employee(){}
    public Employee(int empno, String ename, String job, int mgr, Date hiredate, int sal, int comm, int deptno){
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.hiredate = hiredate;
        this.sal = sal;
        this.comm = comm;
        this.deptno = deptno;
    }

    public Employee(int empno){
        this.empno = empno;
    }

    public Employee(Integer deptno){
        this.deptno = deptno;
    }

    public Employee(String job){
        this.job = job;
    }

    @Override
    public String toString(){
        return  "Employee:" + "| " + empno + " | " +
                              "| " + ename + " | " +
                              "| " + job + " | " +
                              "| " + mgr + " | " +
                              "| " + hiredate + " | " +
                              "| " + sal + " | " +
                              "| " + comm + " | " +
                              "| " + deptno + " | ";
    }
}
