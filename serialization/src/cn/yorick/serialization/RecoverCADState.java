package cn.yorick.serialization;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class RecoverCADState {
	@SuppressWarnings({ "unused", "unchecked" })
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("CADState.out"));
		List<Class<? extends Shape>> shaperTypes = (List<Class<? extends Shape>>) in.readObject();//��ʱ�ڴ����Ѿ������������,��һ������
		Line.deserializeStaticState(in);
		List<Shape> shapes = (List<Shape>) in.readObject();
//		Object object = in.readObject();
		System.out.println(shapes);
	}
}
