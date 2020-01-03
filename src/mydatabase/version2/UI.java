package mydatabase.version2;

import java.sql.SQLException;

/**
 * Testing dao methods
 * @author JV
 */
public class UI {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        StudentDataAccessor dao = new StudentDataAccessor();
        System.out.println(dao.findAll().size());
        System.out.println(dao.findById(1).getAge());
        Student student = new Student();
        student.setName("Cyril");
        student.setSurname("Stredny");
        student.setAge(21);
        Student newStudent = dao.saveP(student);
        System.out.println(newStudent);
        System.out.println(dao.findAll().size());
        dao.delete(newStudent);
        System.out.println(dao.findAll().size());
        
    }
}
