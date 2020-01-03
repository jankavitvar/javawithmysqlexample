package mydatabase.version2;

/**
 * Represents data
 * @author JV
 */
public class Student {
    private int id;
    private String name = "";
    private String surname = "";
    private int age = 0;

    public Student(){    
    }
    
    public Student (int id, String name, String surname, int age){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }
    
    public Student (Student user){
        this.id = user.id;
        this.name = user.name;
        this.surname = user.surname;
        this.age = user.age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", age=" + age + '}';
    }

    
}
