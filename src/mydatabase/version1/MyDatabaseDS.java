package mydatabase.version1;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Simplest connection to the database using Datasource
 * @author JV
 */
public class MyDatabaseDS {

    public static void main(String[] args) throws SQLException  {
        //create Datasource object
        MysqlDataSource dataSource = new MysqlDataSource();             
        
        //set DataSource properties
        dataSource.setServerName(Config.serverName);
        dataSource.setPortNumber(Config.port);
        dataSource.setDatabaseName(Config.databaseName);
        dataSource.setUser(Config.username);
        dataSource.setPassword(Config.password);

        //create connection
        try (Connection connection = dataSource.getConnection()){       
            //create statement
            Statement stmnt = connection.createStatement();                 
            //execute query "Get all info about a student with id 3"
            String studentId = "1";
            ResultSet rs = stmnt.executeQuery("SELECT * FROM students WHERE Id =" + studentId);
            
            //process result
            while (rs.next()) {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                int age = rs.getInt("age");
                System.out.println(name + " " + surname + " " + age);
            }
        } //close connection not needed as try with resources is used 
    }
}
