package cn.yorick.serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerialCloneTest implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static void main(String[] args) throws CloneNotSupportedException {
		Employee2 harry = new Employee2("Harry Hacker", 35000, 1989, 10, 1);
		Employee2 harry2 = (Employee2)harry.clone();
		harry.raiseSalary(10);
		System.out.println(harry);
		System.out.println(harry2);
	}

}

class SerialClonable implements Cloneable,Serializable{
	@Override
	protected Object clone() throws CloneNotSupportedException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(bos);
			oos.writeObject(this);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
			return ois.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			CloneNotSupportedException e2 = new CloneNotSupportedException();
			e2.initCause(e);//将此throwable的原因初始化为指定值
			throw e2;
		}
		return null;
	}
}
