package cn.yorick.serialization.override;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
/**
 * 
 * @author Yorick
 * version 1.0
 * derivation : Java编程思想  18.12.2
 * core  实现Serializable接口,也可以重写writeObject和readObject方法
 * 2019年1月3日
 */
public class SerialCtl implements Serializable {
	private String a;
	private transient String b;//我们知道transient修饰的成员是不能被实例化的,但是我们可以通过重写序列化方法,来使得该成员也可以被序列化
	public SerialCtl(String a, String b) {
		super();
		this.a = "Not Transient : " + a;
		this.b = "Transient : " + b;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return a + "\n" + b;
	}
	//注意重写序列化和反序列化方法的发签名特征是固定的
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		//若将下面序列化,则将会将transient修饰的成员序列化,注释后不序列化
		out.writeObject(b);
//		out.defaultWriteObject();
	}
	
	public void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
		in.defaultReadObject();
		//若下面依据注释,那么不会读出
//		b = (String) in.readObject();
//		in.defaultReadObject();
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		SerialCtl ctl = new SerialCtl("Test1", "Test2");
		System.out.println("Before:\n" + ctl);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(ctl);
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
		SerialCtl ctl2 = (SerialCtl) ois.readObject();
		String b = (String) ois.readObject();
		System.out.println("After:\n" + ctl2);
		System.out.println(b);
	}
}
