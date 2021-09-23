package function;

import java.util.ArrayList;

import javax.swing.JTextArea;

public class Method {
	private static int clientID;
	private static int fileID;
	private static ArrayList<SocketServer> clients;
	private static JTextArea txt;
	public static int getClientID() {
		return clientID;
	}
	public static void setClientID(int clientID) {
		Method.clientID = clientID;
	}
	public static int getFileID() {
		return fileID;
	}
	public static void setFileID(int fileID) {
		Method.fileID = fileID;
	}
	public static ArrayList<SocketServer> getClients() {
		return clients;
	}
	public static void setClients(ArrayList<SocketServer> clients) {
		Method.clients = clients;
		clientID = 1;
		fileID = 1;
	}
	public static int addClient(SocketServer client) {
		clients.add(client);
		return clientID++;
	}
	public static JTextArea getTxt() {
		return txt;
	}
	public static void setTxt(JTextArea txt) {
		Method.txt = txt;
	}
}
