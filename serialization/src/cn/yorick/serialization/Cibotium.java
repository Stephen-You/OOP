package cn.yorick.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.junit.Test;

public class Cibotium extends Dog implements Serializable{
	
	public Cibotium() {
		System.out.println("运行!");
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static void main(String[] args){
		Cibotium cibotium = new Cibotium();
		cibotium.setCollar(new Collar(8, "golden"));
		cibotium.setName("暖男");
		try {
			FileOutputStream fileOutputStream = new FileOutputStream("cibotium");
			ObjectOutputStream os = new ObjectOutputStream(fileOutputStream);
			os.writeObject(cibotium);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			FileInputStream fis = new FileInputStream("cibotium");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object object = ois.readObject();
			Cibotium cibotium2 = (Cibotium) object;
			ois.close();
			System.out.println("序列化对象和反序列化的对象是否是同一个对象:" + (cibotium == cibotium2));
			System.out.println("序列化前的对象:" + cibotium.toString());
			System.out.println("反序列化后的对象:" + cibotium2.toString() + cibotium2.hashCode());
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	@Test
	public void test() {
		Cibotium cibotium = new Cibotium();
		cibotium.setCollar(new Collar(8, "golden"));
		cibotium.setName("暖男");
		System.out.println(cibotium.getName());
		System.out.println(super.getName());
	}
//	@Override
//	public String toString() {
//		// TODO Auto-generated method stub
//		return getName() + getCollar();
//	}
	@Test
	public void test1() {
		Cibotium cibotium = new Cibotium();
		cibotium.setCollar(new Collar(8, "golden"));
		cibotium.setName("暖男");
		try {
			FileOutputStream fileOutputStream = new FileOutputStream("cibotium");
			ObjectOutputStream os = new ObjectOutputStream(fileOutputStream);
			os.writeObject(cibotium);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			FileInputStream fis = new FileInputStream("cibotium");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object object = ois.readObject();
			Cibotium cibotium2 = (Cibotium) object;
			ois.close();
			System.out.println("序列化对象和反序列化的对象是否是同一个对象:" + (cibotium == cibotium2));
			System.out.println("序列化前的对象:" + cibotium.toString());
			System.out.println("反序列化后的对象:" + cibotium2.toString() + cibotium2.hashCode());
			System.out.println(super.name);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		out.writeObject(name);
		out.writeObject(collar);
	}
	private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
		in.defaultReadObject();
		name = (String) in.readObject();
		collar = (Collar) in.readObject();
	}
}
