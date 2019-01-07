package cn.yorick.serialization;

import java.io.FileInputStream;


import java.io.IOException;
import java.io.ObjectInputStream;


/**
 * 
 * @author Yorick
 * version 1.0
 * core ���л���ʹ�úͷ���
 * 2019��1��2��
 */
public class Dog{
	String name ;	//��������
	Collar collar;	//��������
	
	
	public Dog() {
		super();
		System.out.println("������޲ι���!");
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
		return getName() + "��" + getCollar();
	}
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Dog dog = new Dog();
		dog.setCollar(new Collar(5, "red"));
		dog.setName("tom");
		//���л�dog����
		
			/*FileOutputStream fos = new FileOutputStream("dog.ser");
			ObjectOutputStream oos  = new ObjectOutputStream(fos);
			oos.writeObject(dog);
			oos.close();*/
		
		//�����л�
		FileInputStream fis = new FileInputStream("dog.ser");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Object object = ois.readObject();
		Dog dog1 = (Dog) object;
		System.out.println("���л�����ͷ����л��Ķ����Ƿ���ͬһ������:" + (dog == dog1));
		System.out.println("���л�ǰ�Ķ���:" + dog.toString());
		System.out.println("�����л���Ķ���:" + dog1.toString());
	}
	
}
