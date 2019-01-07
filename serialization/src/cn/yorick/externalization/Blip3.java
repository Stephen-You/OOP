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
 * core 可控制序列化调用顺序:无参构造函数 --->readObject
 * 2019年1月3日
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
		System.out.println("序列化是会调用该方法,但要注意序列化的数据操作也是在这里控制的");
		out.writeObject(s);
		out.writeInt(i);
	}
	@Override
	public String toString() {
		return s + i;
	}
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		System.out.println("反序列化时调用该方法,需要注意的有两点,第一是反序列化的操作,要在这里实现,第二是反序列化和序列化的顺序要对应!");
		s = (String) in.readObject();//如果注释掉这两句,那么反序列化后就是一个字段是初始化值的对象,即s=null i=0
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
