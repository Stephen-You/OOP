package cn.yorick.externalization;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
/**
 * 
 * @author Yorick
 * version 1.0
 * derivation :
 * core �ɿ������л�����˳��:�޲ι��캯�� --->readObject
 * 2019��1��3��
 */
public class Blip3 implements Externalizable,Serializable {
	private transient int i;
	private String s;
	
	public Blip3() {
		System.out.println("Blip3 Constructor");
	}

	public Blip3(int i, String s) {
		super();
		System.out.println("Blip3(int i, String s)");
		this.i = i;
		this.s = s;
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		System.out.println("���л��ǻ���ø÷���,��Ҫע�����л������ݲ���Ҳ����������Ƶ�");
		out.writeObject(s);
		out.writeInt(i);
	}
	@Override
	public String toString() {
		return s + i;
	}
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		System.out.println("�����л�ʱ���ø÷���,��Ҫע���������,��һ�Ƿ����л��Ĳ���,Ҫ������ʵ��,�ڶ��Ƿ����л������л���˳��Ҫ��Ӧ!");
		s = (String) in.readObject();//���ע�͵�������,��ô�����л������һ���ֶ��ǳ�ʼ��ֵ�Ķ���,��s=null i=0
		i = in.readInt();
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		System.out.println("Constructing objects:");
		Blip3 blip3 = new Blip3(47, "A String");
		System.out.println(blip3);
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Blip3.out"));
		oos.writeObject(blip3);
		oos.close();
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Blip3.out"));
		System.out.println("Recovering blip3");
		blip3 = (Blip3) ois.readObject();
		System.out.println(blip3);
		
	}
}
