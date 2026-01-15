package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ItemDBConnection {
    private static ItemDBConnection instance;
    private Connection connection;
    private ItemDBConnection () throws SQLException {
       connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "rashpro");

    }
    public Connection getConnection(){
        return connection;
    }
    public static ItemDBConnection getInstance() throws SQLException {
        return instance==null?instance=new ItemDBConnection():instance;

    }

}
