package mydatabase.version2;

/**
 * Data accessor implementation for Student
 * @author JV
 */

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection ;
import java.sql.PreparedStatement;
import java.sql.Statement ;
import java.sql.ResultSet ;
import java.sql.SQLException ;

import java.util.ArrayList ;
import java.util.List;
import mydatabase.version1.Config;

public class StudentDataAccessor implements DataAccessor<Student>{
    private MysqlDataSource dataSource;
            
    public StudentDataAccessor(){
        dataSource = new MysqlDataSource();
        dataSource.setServerName(Config.serverName);
        dataSource.setPortNumber(Config.port);
        dataSource.setDatabaseName(Config.databaseName);
        dataSource.setUser(Config.username);
        dataSource.setPassword(Config.password);
    }
    
    // in real projects, use a connection pool....

    @Override
    public List findAll() throws SQLException{
        try(Connection connection = dataSource.getConnection()){       
            Statement stmnt = connection.createStatement();                 
            ResultSet rs = stmnt.executeQuery("SELECT * FROM students"); 
            List<Student> studentsList = new ArrayList<>();
            while (rs.next()) {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                int id = rs.getInt("id"); //by column name
                int age = rs.getInt(4); //by column number
                Student student = new Student(id, name, surname, age);
                studentsList.add(student);
            }
            return studentsList;
        }
    }
    
    @Override
    public Student findById(int id) throws SQLException{
        try(Connection connection = dataSource.getConnection()){       
            Statement stmnt = connection.createStatement(); 
            String sql = ("SELECT id, name, surname, age FROM students WHERE id = " + id);
            ResultSet resultSet = stmnt.executeQuery(sql); 
        
            if(resultSet.next()){
                Student user = new Student();
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setAge(resultSet.getInt(4));
                user.setId(id);
                return user;
            }
            else {
                return null;
            }
        }
    }

    @Override
    public Student save(Student t) throws SQLException {
        try(Connection connection = dataSource.getConnection()){       
            Statement stmnt = connection.createStatement();
            String name = t.getName();
            String surname = t.getSurname();
            int age = t.getAge();
            String sql = String.format("INSERT INTO students (name, surname, age) VALUES ('%s', '%s', %d)", name, surname, age);
            //execute data manipulation
            stmnt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS); //The constant indicating that generated keys should be made available for retrieval.
            ResultSet resultSet = stmnt.getGeneratedKeys(); //Retrieves any auto-generated keys created as a result of executing this Statement object.
            Student newStudent = new Student(t);
            if(resultSet.next()){
                newStudent.setId(resultSet.getInt(1));
            }
            return newStudent;
        }
    }
    
    public Student saveP(Student t) throws SQLException {
        try(Connection connection = dataSource.getConnection()){
            String sql = "INSERT INTO students (name, surname, age) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,t.getName());
            preparedStatement.setString(2,t.getSurname());
            preparedStatement.setInt(3,t.getAge());
            preparedStatement.executeUpdate();
            Student newStudent = new Student(t);
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                newStudent.setId(resultSet.getInt(1));
            }
            return newStudent;
        }
    }

    @Override
    public void delete(Student t) throws SQLException{
        try(Connection connection = dataSource.getConnection()){       
            Statement stmnt = connection.createStatement();
            int id = t.getId();
            String sql = ("DELETE FROM students WHERE id = " + id);
            stmnt.executeUpdate(sql);
        }
    }
    
    public void deleteP(Student t) throws SQLException{
        try(Connection connection = dataSource.getConnection()){       
            String sql = "DELETE FROM students WHERE id =?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, t.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public Student update(Student t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
        //TODO
    }
}
