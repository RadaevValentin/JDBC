package Start_module;

import Business_logic.ConnectionDb;
import User_interaction_module.IUser;
import User_interaction_module.StandartUser;
import Work_with_db.Comm_Add;
import Work_with_db.Comm_Delete;
import Work_with_db.Comm_Read;
import Work_with_db.Comm_Search;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JDBCApplication {

	public static void main(String[] args) {
        IUser user = new StandartUser();
        user.create("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@ sql.edu-netcracker.com:1251:XE", "unc17i_vradaev", "BY8mWhEQ");
        ConnectionDb connection = new ConnectionDb(user);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Comm_Add comm_add = new Comm_Add(reader, connection);
        comm_add.execute();
        Comm_Read comm_read = new Comm_Read(reader, connection);
        comm_read.execute();
        Comm_Search comm_search = new Comm_Search(reader, connection);
        comm_search.execute();
        Comm_Delete comm_delete = new Comm_Delete(reader, connection);
        comm_delete.execute();
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
