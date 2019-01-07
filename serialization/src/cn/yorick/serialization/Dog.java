package cn.yorick.serialization;

import java.io.FileInputStream;


import java.io.IOException;
import java.io.ObjectInputStream;


/**
 * 
 * @author Yorick
 * version 1.0
 * core 序列化的使用和分析
 * 2019年1月2日
 */
public class Dog{
	String name ;	//狗的名字
	Collar collar;	//狗的类型
	
	
	public Dog() {
		super();
		System.out.println("父类的无参构造!");
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Collar getCollar() {
		return collar;
	}
	public void setCollar(Collar collar) {
		this.collar = collar;
	}
	@Override
	public String toString() {
		return getName() + "是" + getCollar();
	}
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Dog dog = new Dog();
		dog.setCollar(new Collar(5, "red"));
		dog.setName("tom");
		//序列化dog对象
		
			/*FileOutputStream fos = new FileOutputStream("dog.ser");
			ObjectOutputStream oos  = new ObjectOutputStream(fos);
			oos.writeObject(dog);
			oos.close();*/
		
		//反序列化
		FileInputStream fis = new FileInputStream("dog.ser");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Object object = ois.readObject();
		Dog dog1 = (Dog) object;
		System.out.println("序列化对象和反序列化的对象是否是同一个对象:" + (dog == dog1));
		System.out.println("序列化前的对象:" + dog.toString());
		System.out.println("反序列化后的对象:" + dog1.toString());
	}
	
}
