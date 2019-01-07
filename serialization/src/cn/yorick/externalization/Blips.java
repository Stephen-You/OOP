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
//		blip2 = (Blip2) ois.readObject();//注意序列化和反序列化时存放对象和取出对象书序要一致,否则很容易发生ClassCastException
		blip1 = (Blip1) ois.readObject();
		/**
		 * 此时会出现异常 java.io.InvalidClassException: cn.yorick.externalization.Blip2; no valid constructor
		 * 这是因为在序列化是调用构造函数,但是Blip2的构造函数不是public的,而可控制序列化需要首先调用公共无参的构造函数,Blip2没有
		 * 解决办法是添加一个:公共的无参构造函数
		 */
		blip2 = (Blip2) ois.readObject();
				
	}

}
