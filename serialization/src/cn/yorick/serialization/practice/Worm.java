package cn.yorick.serialization.practice;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
/**
 * 
 * @author Yorick
 * version 1.0
 * derivation :Java编程思想  18.12
 * core
 * 2019年1月3日
 */
import java.util.Random;
public class Worm implements Serializable {
	private static Random random = new Random(47);
	private Data[] d = {new Data(random.nextInt(10)),new Data(random.nextInt(10)),new Data(random.nextInt(10))};
	private Worm next;
	private char c;
	public Worm(int i, char x) {
		super();
		System.out.println("worm constructor: " + i);
		c = x;
		if(--i > 0) {
			next = new Worm(i,(char) (x + 1));
		}
	}
	public Worm() {
		System.out.println("default constructor!");
	}
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(":");
		result.append(c);
		result.append("(");
		for (Data data : d) {
			result.append(data);
		}
		result.append(")");
		if(next != null) {
			result.append(next);
		}
		return result.toString();
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		Worm w = new Worm(6, 'a');	//实际创建了6个对象
		System.out.println("w = " + w);
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("worm.out"));
		oos.writeObject("Worm storage\n");
		oos.writeObject(w);
		oos.close();
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("worm.out"));
		String s = (String) ois.readObject();
		Worm w2 = (Worm) ois.readObject();
		System.out.println(s + "w2 = " + w2);
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		ObjectOutputStream out2 = new ObjectOutputStream(bout);
		out2.writeObject("Worm storage\n");
		out2.writeObject(w);
		out2.flush();
		ObjectInputStream ois2 = new ObjectInputStream(new ByteArrayInputStream(bout.toByteArray()));
		s = (String) ois2.readObject();
		Worm w3 = (Worm) ois2.readObject();
		System.out.println(s + "w3 = " + w3);
	}
}
