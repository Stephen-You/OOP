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
 * derivation : Java���˼��  18.12.2
 * core  ʵ��Serializable�ӿ�,Ҳ������дwriteObject��readObject����
 * 2019��1��3��
 */
public class SerialCtl implements Serializable {
	private String a;
	private transient String b;//����֪��transient���εĳ�Ա�ǲ��ܱ�ʵ������,�������ǿ���ͨ����д���л�����,��ʹ�øó�ԱҲ���Ա����л�
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
	//ע����д���л��ͷ����л������ķ�ǩ�������ǹ̶���
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		//�����������л�,�򽫻Ὣtransient���εĳ�Ա���л�,ע�ͺ����л�
		out.writeObject(b);
//		out.defaultWriteObject();
	}
	
	public void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
		in.defaultReadObject();
		//����������ע��,��ô�������
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
