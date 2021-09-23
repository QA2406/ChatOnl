import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;


class user {
	private String username;
	private String password;
	private int id;
	public user(int id,String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
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
	public String toString() {
		return username.toString();
	}
}

public class test3 extends JFrame{
    JButton button ;
    JButton button2;
    JLabel label;
    JTextField textID;
    JTextField textNAME;
    JTextArea area;
    String s;
     
    public test3(){
   
    super("insert image to database in java");
    
    button = new JButton("ADD");
    button.setBounds(200,300,100,40);
    
    button2 = new JButton("Browse");
    button2.setBounds(80, 300, 100, 40);
    
    textID = new JTextField("ID");
    textID.setBounds(320,290,100,20);

    textNAME = new JTextField("Name");
    textNAME.setBounds(320,330,100,20);

    area = new JTextArea("DESCRIPTION",100, 100);
    
    JScrollPane pane = new JScrollPane(area);
    pane.setBounds(450, 270, 200, 100);
    
    label = new JLabel();
    label.setBounds(10,10,670,250);   
  
    //button to browse the image into jlabel
    button2.addActionListener(new ActionListener(){
        @Override
     public void actionPerformed(ActionEvent e){
         JFileChooser fileChooser = new JFileChooser();
         fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
         FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg","gif","png");
         fileChooser.addChoosableFileFilter(filter);
         int result = fileChooser.showSaveDialog(null);
         if(result == JFileChooser.APPROVE_OPTION){
             File selectedFile = fileChooser.getSelectedFile();
             String path = selectedFile.getAbsolutePath();
             label.setIcon(ResizeImage(path));
             s = path;
              }
         else if(result == JFileChooser.CANCEL_OPTION){
             System.out.println("No Data");
         }
     }
    });

    //button to insert image and some data into mysql database
    button.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try{
	               Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user?autoReconnect=true&useSSL=false","root","21082002");
	               java.sql.PreparedStatement ps =  con.prepareStatement("insert into userforchat(id,username,password,image,status) values(?,?,?,?,?)");
	               InputStream is = new FileInputStream(new File(s));
	               ps.setString(1, textID.getText());
	               ps.setString(2, textNAME.getText());
	               ps.setString(3, area.getText());
	               ps.setBlob(4,is);
	               ps.setInt(5, 0);
	               ps.executeUpdate();
	          
	               JOptionPane.showMessageDialog(null, "Data Inserted");
	           }catch(Exception ex){
	               ex.printStackTrace();
	           }
		}
	});

    add(label);
    add(textID);
    add(textNAME);
    add(pane);
    add(button);
    add(button2);
    setLayout(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(700,420);
    setVisible(true);
    }
    
    //Methode To Resize The ImageIcon
    public ImageIcon ResizeImage(String imgPath){
        ImageIcon MyImage = new ImageIcon(imgPath);
        java.awt.Image img = MyImage.getImage();
        java.awt.Image newImage = img.getScaledInstance(label.getWidth(), label.getHeight(),java.awt.Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImage);
        return image;
    }
 
    public static void main(String[] args){
        new test3();
    }
   }
/*public class test3{
	public static void main(String[] args) throws SQLException, IOException {
		Connection connection = null;
		FileInputStream imageInputStream = null;
		 try{
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user?autoReconnect=true&useSSL=false","root","21082002");
             PreparedStatement ps = (PreparedStatement) con.prepareStatement("insert into im(image) values(?)");
             InputStream is = new FileInputStream(new File("D:\\profile_small.png"));
             ps.setBlob(1,is);
             ps.executeUpdate();
             JOptionPane.showMessageDialog(null, "Data Inserted");
         }catch(Exception ex){
             ex.printStackTrace();
         }
	} */
/*	public static void main(String args[]){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/user?autoReconnect=true&useSSL=false","root","21082002");
			
			File file=new File("E:\\image1.png");
			FileOutputStream fos=new FileOutputStream(file);
			byte b[];
			Blob blob;
			
			PreparedStatement ps=(PreparedStatement) con.prepareStatement("select * from im"); 
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				blob=(Blob) rs.getBlob("image");
				b=blob.getBytes(1,(int)blob.length());
				fos.write(b);
			}
			
			ps.close();
			fos.close();
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	} 
} */