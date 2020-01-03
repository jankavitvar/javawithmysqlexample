package mydatabase.version1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * Download jar e.g. from here https://mvnrepository.com/artifact/mysql/mysql-connector-java/8.0.18
 * Copy .jar to lib project folder 
 * Add  .jar to Project->Properties->Libraries->Add_jar
 *
 * Simplest connection to the database using DriverManager
 * @author JV
 */
public class MyDatabaseDM {

    public static void main(String[] args) throws Exception {
        //register the driver class, not needed from Java6
        //Class.forName("com.mysql.jdbc.Driver");
        
        //compose database URL
        //e.g. "jdbc:mysql://localhost:3306/nnmportal?verifyServerCertificate=false&useSSL=true"
        //"jdbc:mysql://localhost:3306/nnmportal", //generates warning
        String databaseURL = "jdbc:mysql://" + Config.serverName + ":" + Config.port + "/"+ Config.databaseName + "?verifyServerCertificate=false&useSSL=true";
        
        //create connection
        try (Connection connection = DriverManager.getConnection(databaseURL, Config.username, Config.password)) {
            //create statement
            Statement stmnt = connection.createStatement();                 
            //execute query
            ResultSet rs = stmnt.executeQuery("SELECT * FROM students");        
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
