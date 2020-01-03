package mydatabase.version1;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Simplest connection to the database using datasource
 * Example of SqlInjection security problem
 * Solution using PreparedStatement
 * @author JV
 */
public class MyDatabaseDSInjection {

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
            
            System.out.println("SQL injection problem");
            //execute query "Get all info about a student named Alice"
            //example of sqlinjection security problem 
            String studentName = "'Alice' OR 1=1";
            //select will return info about all students
            ResultSet rs = stmnt.executeQuery("SELECT * FROM students WHERE name =" + studentName);
            //process result
            while (rs.next()) {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                int age = rs.getInt("age");
                System.out.println(name + " " + surname + " " + age);
            }
            
            //Solution - use PreparedStatement
            String sql = "SELECT * FROM students WHERE name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, studentName);
            //select will return no info as there is no student named "Alice OR 1=1" 
            ResultSet rs1 = preparedStatement.executeQuery();
            
            System.out.println("Solution");
            //process result
            while (rs1.next()) {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                int age = rs.getInt("age");
                System.out.println(name + " " + surname + " " + age);
            }
        } //close connection not needed as try with resources is used 
    }
}
