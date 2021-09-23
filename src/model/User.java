package model;

import javax.swing.ImageIcon;

public class User{
	 private int id;
	 private String username, password;
	 private ImageIcon image;
	 public User(int id, String username, String password){
	        this.id = id;
	        this.username = username;
	        this.password = password;
	        
	    }
	 
	 public void logout(){
	        username = "";
	        password="";
	        id = 0;
	    }
	 
		public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }
	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }
}