package cn.yorick.externalization.practice;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class Blips{

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		System.out.println("Constructing objects:");
		Blip1 blip1 = new Blip1();
		Blip2 blip2 = new Blip2();
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Blips.out"));
		System.out.println("saving objects:");
		oos.writeObject(blip1);
		oos.writeObject(blip2);
		oos.close();
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Blips.out"));
		System.out.println("Recovering b1:");
//		blip2 = (Blip2) ois.readObject();//ע�����л��ͷ����л�ʱ��Ŷ����ȡ����������Ҫһ��,��������׷���ClassCastException
		blip1 = (Blip1) ois.readObject();
		/**
		 * ��ʱ������쳣 java.io.InvalidClassException: cn.yorick.externalization.Blip2; no valid constructor
		 * ������Ϊ�����л��ǵ��ù��캯��,����Blip2�Ĺ��캯������public��,���ɿ������л���Ҫ���ȵ��ù����޲εĹ��캯��,Blip2û��
		 * ����취�����һ��:�������޲ι��캯��
		 */
		blip2 = (Blip2) ois.readObject();
				
	}

}
class Blip1 implements Externalizable {

	public Blip1() {
		System.out.println("Blip1 Constructor");
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		System.out.println("Blip1 writeExternal!");
		
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		System.out.println("Blip1 readExternal!");
		
	}

	

}
class Blip2 implements Externalizable {

//	 Blip2() {
//		System.out.println("Blip2 Constructor");
//	}
	
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		System.out.println("Blip2 writeExternal!");
		
	}

	public Blip2() {
		System.out.println("Blip2 Constructor");
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		System.out.println("Blip2 readExternal!");
		
	}

}
