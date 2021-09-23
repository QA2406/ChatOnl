package message;

import java.io.Serializable;
import javax.swing.ImageIcon;

public class Message implements Serializable {

    public String status;
    public int ID;
    public String sender;
    public String recipient;
    public String content;
    public ImageIcon image;
    public byte[] data;
    
    public Message(String status, String sender,int ID, String content, String recipient, ImageIcon image) {
		super();
		this.ID = ID;
		this.status = status;
		this.sender = sender;
		this.recipient = recipient;
		this.content = content;
		this.image = image;
	}
    public String toString(){
        return "{type='"+status+"', sender='"+sender+"', content='"+content+"', recipient='"+recipient+"'}";
    }
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public ImageIcon getImage() {
		return image;
	}
	public void setImage(ImageIcon image) {
		this.image = image;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}

}