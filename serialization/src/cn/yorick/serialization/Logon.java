package cn.yorick.serialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

public class Logon implements Serializable {
	private Date date = new Date();
	private String username;
	private transient String password;
	public Logon(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Logon info: \n username: " + username + 
				"\n date: " + date + "\n password: " + password;
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		Logon logon = new Logon("Yorick", "guess");
		System.out.println("logon logon = " + logon);
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Logon.out"));
		oos.writeObject(logon);
		oos.close();
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Logon.out"));
		System.out.println("recocering object at " + new Date());
		logon = (Logon) ois.readObject();
		System.out.println("logon logon = " + logon);
	}
}
