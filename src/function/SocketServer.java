		package function;

import java.io.*;
import java.net.*;

import database.DBClass;
import function.Main;
import main.MethodClient;
import message.Message;

class ServerThread extends Thread { 
	
    public SocketServer server = null;
    public Socket socket = null;
    public int ID = -1;
    public String username = "";
    public ObjectInputStream streamIn  =  null;
    public ObjectOutputStream streamOut = null;
    public Main ui;

    public ServerThread(SocketServer _server, Socket _socket){  
    	super();
        server = _server;
        socket = _socket;
        ID     = socket.getPort();
        ui = _server.ui;
    }
    public void send(Message msg){
        try {
            streamOut.writeObject(msg);
            streamOut.flush();
        } 
        catch (IOException ex) {
            System.out.println("Exception [SocketClient : send(...)]");
        }
    }
    
    public int getID(){  
	    return ID;
    }
   
    @SuppressWarnings("deprecation")
	public void run(){  
    	ui.txt.append("\n Server Thread " + ID + " running.");     
    	while (true){  
    	    try{  
    	    	Message msg = (Message) streamIn.readObject();
    	    	server.handle(ID, msg);
            }
            catch(Exception ioe){  
            	System.out.println(ID + " ERROR reading: " + ioe.getMessage());
        //        server.remove(ID);
         //       stop();
            }
        }
    }
    
    public void open() throws IOException {  
        streamOut = new ObjectOutputStream(socket.getOutputStream());
        streamOut.flush();
        streamIn = new ObjectInputStream(socket.getInputStream());
    }
    
    public void close() throws IOException {  
    	if (socket != null)    socket.close();
        if (streamIn != null)  streamIn.close();
        if (streamOut != null) streamOut.close();
    }
}
public class SocketServer implements Runnable {
    
    public ServerThread clients[];
    public ServerSocket server = null;
    public Thread       thread = null;
    public int clientCount = 0, port = 24242;
    public Main ui;
    public DBClass db;

    public SocketServer(Main frame){   
        clients = new ServerThread[50];
        ui = frame;
        db = new DBClass();
        
	try{  
	    server = new ServerSocket(port);
        port = server.getLocalPort();
	    ui.txt.append("Server startet. IP : " + InetAddress.getLocalHost() + ", Port : " + server.getLocalPort());
	    start(); 
        }
	catch(IOException ioe){  
            ui.txt.append("Can not bind to port : " + port + "\nRetrying"); 
		}
    }   
    public SocketServer(Main frame, int Port){
    	
        clients = new ServerThread[50];
        ui = frame;
        port = Port;
        db = new DBClass();
        
	try{  
	    server = new ServerSocket(port);
        port = server.getLocalPort();
	    ui.txt.append("Server startet. IP : " + InetAddress.getLocalHost() + ", Port : " + server.getLocalPort());
	    start(); 
        }
	catch(IOException ioe){  
            ui.txt.append("\nCan not bind to port " + port + ": " + ioe.getMessage()); 
		}
    }
    public void run(){  
	while (thread != null){  
            try{  
            ui.txt.append("\nWaiting for a client ..."); 
	        addThread(server.accept());
	        System.out.println(clients.toString());

	    }
	    catch(Exception ioe){ 
                ui.txt.append("\nServer accept error: \n");
             
	    	}
        }
    }	
    public void start(){  
    	if (thread == null){  
          thread = new Thread(this); 
          thread.start();
    	}
    }
    
    @SuppressWarnings("deprecation")
    public void stop(){  
        if (thread != null){  
            thread.stop(); 
	    thread = null;
	}
    }
    
    private int findClient(int ID){  
    	for (int i = 0; i < clientCount; i++){
        	if (clients[i].getID() == ID){
                    return i;
                }
	}
	return -1;
    }
    public ServerThread findUserThread(String usr){
        for(int i = 0; i < clientCount; i++){
            if(clients[i].username.equals(usr)){
                return clients[i];
            }
        }
        return null;
    }
   public synchronized void handle(int ID, Message msg){  
	if (msg.getStatus().equals(".bye")){
            Announce("signout", "SERVER", msg.content);
            remove(ID); 
	}else{
            if(msg.status.equals("login")){
                if(findUserThread(msg.sender) == null) {
                    if(db.checklogin(msg.sender, msg.content)){
                        clients[findClient(ID)].username = msg.sender;
                        clients[findClient(ID)].send(new Message("login", "SERVER",msg.ID, "True", msg.sender,null));
                        ui.txt.append("New Client name : " + msg.sender + " has connected ...\n");
                        System.out.println(ID);
                        Announce("newuser", "SERVER", msg.sender);
                        SendUserList(msg.sender);
                    }else{
                        clients[findClient(ID)].send(new Message("login", "SERVER",msg.ID, "False", msg.sender,null));
                    } 
                }else{
                    clients[findClient(ID)].send(new Message("login", "SERVER",msg.ID, "quocanh", msg.sender,null));
                }
            }
            else if(msg.status.equals("message")){
                if(msg.recipient.equals("All")){
                    Announce("message", msg.sender, msg.content);
                    System.out.println(msg.toString()+"quocanh");
                }else{
                    findUserThread(msg.recipient).send(new Message(msg.status, msg.sender,msg.ID, msg.content, msg.recipient,msg.image));
                    System.out.println(msg.ID+"\t đây là ID được nhận ");
                    
                    clients[findClient(ID)].send(new Message(msg.status, msg.sender,msg.ID, msg.content, msg.recipient,msg.image));
                }
            }
            else if(msg.status.equals("test")){
                clients[findClient(ID)].send(new Message("test", "SERVER",msg.ID, "OK", msg.sender,null));
            		}
				}
            }
           public void Announce(String type, String sender, String content){
        	   	Message msg = new Message(type, sender,0, content, "All",null);
        for(int i = 0; i < clientCount; i++){
            clients[i].send(msg);
        }
    }
    
    public void SendUserList(String toWhom){
        for(int i = 0; i < clientCount; i++){
       //     findUserThread(toWhom).send(new Message("newuser", "SERVER", clients[i].username, toWhom));
        }
    }
    @SuppressWarnings("deprecation")
    public synchronized void remove(int ID){
    int pos = findClient(ID);
        if (pos >= 0){  
            ServerThread toTerminate = clients[pos];
            ui.txt.append("\nRemoving client thread " + ID + " at " + pos);
	    if (pos < clientCount-1){
                for (int i = pos+1; i < clientCount; i++){
                    clients[i-1] = clients[i];
	        }
	    }
	    clientCount--;
	    try{  
	      	toTerminate.close(); 
	    }
	    catch(IOException ioe){  
	      	ui.txt.append("\nError closing thread: " + ioe); 
	    }
	    toTerminate.stop(); 
	}
    }
    private void addThread(Socket socket){  
	if (clientCount < clients.length){  
            ui.txt.append("\nClient accepted: " + socket);
	    clients[clientCount] = new ServerThread(this, socket);
	    try{  
	      	clients[clientCount].open(); 
	        clients[clientCount].start();  
	        clientCount++; 
	    }
	    catch(IOException ioe){  
	      	ui.txt.append("\nError opening thread: " + ioe); 
	    } 
	}
	else{
            ui.txt.append("\nClient refused: maximum " + clients.length + " reached.");
		}
    }
}
