package cn.yorick.serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;

public class Orientation implements Serializable{
	public static final Orientation HORIZONTAL = new Orientation(1);
	public static final Orientation VERTICAL = new Orientation(2);
	private int value;
	private Orientation(int v) {
		value = v;
	}
	protected Object readResolve() throws ObjectStreamException{
		if(value == 1) return Orientation.HORIZONTAL;
		if (value == 2) return Orientation.VERTICAL;
		return null;
	}
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Orientation original = Orientation.HORIZONTAL;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(original);
		oos.close();
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
		Orientation orientation = (Orientation) ois.readObject();
		if(orientation != original) {
			System.out.println("枚举类型受到破坏");
		}
		System.out.println("end");
	}
}
