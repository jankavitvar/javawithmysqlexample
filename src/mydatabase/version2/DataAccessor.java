package mydatabase.version2;

import java.sql.SQLException;
import java.util.List;

/**
 * Generic data accessor specifying required methods
 * @author JV
 * @param <T>
 */
public interface DataAccessor<T> {
    public T save(T t) throws SQLException; //insert
    public T update(T t) throws SQLException;
    public void delete(T t) throws SQLException;
    public T findById(int id) throws SQLException;
    public List<T> findAll()throws SQLException;
}
