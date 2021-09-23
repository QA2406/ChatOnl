package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;

import database.DBClass;


public interface Friend {
    public void set(ImageIcon image, int ID, String name, String time);

    public ImageIcon getImage();

    public String getfName();
}