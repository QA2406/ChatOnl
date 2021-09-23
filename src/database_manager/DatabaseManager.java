package database_manager;
import model.User;
import database.SQLWrapper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author root
 */
public class DatabaseManager {
    
    public Connection connection;
    public SQLWrapper wrapper;
    public User user;
    
    public DatabaseManager() throws SQLException{
        connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3307/messenger?autoReconnect=true&useSSL=false","root","21082002");
        wrapper = new SQLWrapper();
    }
    
    public PreparedStatement Select(Object[] columns ,String database, String where) throws SQLException{
    	System.out.println((PreparedStatement) connection.prepareStatement(wrapper.select(columns).from(database).where(where).getQuery()));
        return  (PreparedStatement) connection.prepareStatement(wrapper.select(columns).from(database).where(where).getQuery());
    }
    
    public PreparedStatement Delete(String table, String where) throws SQLException{
        return (PreparedStatement) connection.prepareStatement(wrapper.delete(table)
                .where(where).getQuery());       
    }
    
    public PreparedStatement Insert(String table, Object[] values) throws SQLException{
        return (PreparedStatement)connection.prepareStatement(wrapper.insert(table)
                .values(values).getQuery());
    }
    
    public PreparedStatement Update(String table, String word, String value, String where) throws SQLException{
        return (PreparedStatement)connection.prepareStatement(wrapper.update(table)
                .set(word, value).where(where).getQuery());
    }
    public PreparedStatement Select(Object[] columns, String table) throws SQLException{
        return (PreparedStatement) connection.prepareStatement(wrapper.select(columns).from(table).getQuery());
    }
    public User getUser(){
        return this.user;
    }
}   