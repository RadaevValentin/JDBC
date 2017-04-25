package Start_module;

import User_interaction_module.IUser;
import User_interaction_module.StandartUser;
import Work_with_db.Comm_Add;
import Work_with_db.Comm_Delete;
import Work_with_db.Comm_Read;
import Work_with_db.Comm_Search;

public class JDBCApplication {

	public static void main(String[] args) {
        IUser user = new StandartUser();
        user.create("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@ sql.edu-netcracker.com:1251:XE", "unc17i_vradaev", "BY8mWhEQ");
        user.getConnection().connect();
        user.setComm(new Comm_Add(user));
        user.execute();
        user.setComm(new Comm_Read(user));
        user.execute();
        user.setComm(new Comm_Search(user));
        user.execute();
        user.setComm(new Comm_Delete(user));
        user.execute();
        user.getConnection().disconnect();
    }
}
