package database;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import com.mysql.jdbc.Driver;
import database_manager.DatabaseManager;
import main.MethodClient;

public class DBClass {
    String conString = "jdbc:mysql://localhost:3306/user";
    String username = "root";
    String password = "21082002";
    DatabaseManager database;
    public boolean connected = false;
    //INSERT INTO DB
    public Boolean add(String name) {
        //SQL STMT
        String sql = "INSERT INTO playerstb(Name) VALUES('" + name + "')";
        try {
            //GET COONECTION
            Connection con = DriverManager.getConnection(conString, username, password);
            // PREPARED STMT
            Statement s = con.prepareStatement(sql);
            //EXECUTE
            s.execute(sql);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public void saveimg() {
    	String sql = "insert into userforchat(image) values(?)";
    	try {
			Connection con = DriverManager.getConnection(conString, username, password);
			File file = new File("D:\\img-test");
			FileInputStream fis = new FileInputStream(file);
			
			java.sql.PreparedStatement ps = con.prepareStatement(sql);
			ps.setBinaryStream(3, fis, file.length());
			ps.executeUpdate();
			ps.close();
			fis.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
    
    public boolean userExists(String usr) {
    	String sql = "select *from userforchat";
    	try {
			Connection con = DriverManager.getConnection(conString, username, password);
			Statement st = con.createStatement();
			if(usr.length() > 0) {
				sql = sql +"where username like '%"+usr+"%'";
			}
			ResultSet rst = st.executeQuery(sql);
			if(rst.isBeforeFirst() == true) {
				JOptionPane.showMessageDialog(null, "BUG");
				return true;
			}
    	} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return false;
    }
    public boolean checkForUser(String user,String pass) throws SQLException, Exception {
        Object[] columns = {"id", "username", "password"};
    //    if (!validEmail(user)) {
           java.sql.PreparedStatement count =  database.connection.prepareStatement("SELECT COUNT(username) "
                    + "FROM userforchat WHERE username = " + "'" + user + "'");
            ResultSet result = count.executeQuery();
            result.next();
            if (result.getInt(1) > 0) {
                java.sql.PreparedStatement db_user = database.Select(columns, "userforchat", "username = " + "'" + user + "'");
                ResultSet users = db_user.executeQuery();
                users.next();
                if (pass.equals(users.getString("password"))) {
                    Object[] session = {"status"};
                    java.sql.PreparedStatement checkIfLoged = database.Select(session, "userforchat", "username="+"'"+user+"'");
                    ResultSet loged = checkIfLoged.executeQuery();
                    loged.next();
                    if(loged.getString("status").equals("0")){
                        java.sql.PreparedStatement update = database.Update("userforchat", "status", "1", "username = "+"'"+user+"'");
                        if(update.executeUpdate() == 1){
                        	return true;
                        }else{
                          System.out.println("Error with login");
                        }
                    }else{
                        System.out.println("Some one is logged on this account");
                    }
                } else {
                	System.out.println("Password id correct");
                }
            } else {
               System.out.println("User does not exit");
            }
       // }

        return false;
    }
    public boolean checklogin(String usr,String pass) {
    		String sql ="select *from userofchat where username=? and password=?";
    		try {
    			Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection(conString, username, password);
				java.sql.PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, usr);
				ps.setString(2, pass);
				ResultSet rst = ps.executeQuery();
				if(rst.next()) {
					connected = true;
					return true;
				}else {
					connected = false;
				}
    		} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return false;
			}
			return false;
    }
    //RETRIEVE DATA
    public DefaultListModel retrieve() {
        DefaultListModel dlm = new DefaultListModel();
        //SQL STMT
        String sql = "SELECT username FROM userforchat";

        try {
            Connection con = DriverManager.getConnection(conString, username, password);
            //PREPARED STMT
            Statement s = con.prepareStatement(sql);
            ResultSet rs = s.executeQuery(sql);
            //LOOP THRU GETTING ALL VALUES
            while (rs.next()) {
                //GET VALUES
                String name = rs.getString(1);
                //ADD TO DM
                dlm.addElement(name);
                
            }
          //  System.out.println(dlm);
            return dlm;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    //UPDATE DATA
    public Boolean update(String id, String value) {
        String sql = "UPDATE userforchat SET username ='" + value + "' WHERE username='" + id + "'";

        try {
            Connection con=DriverManager.getConnection(conString, username, password);

            //STATEMENT
            Statement s=con.prepareStatement(sql);
            //EXECUTE
            s.execute(sql);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public void test() {
    
    }
   //DELETE DATA
    public Boolean delete(String id)
    {
        //SQL STMT
        String sql="DELETE FROM userforchat WHERE Name ='"+id+"'";
        try
        {
            //CONNECTION
             Connection con=DriverManager.getConnection(conString, username, password);
             //sTAETEMT
             Statement s=con.prepareStatement(sql);
             //EXECUTE
             s.execute(sql);
             return true;
        }catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}