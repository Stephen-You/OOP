package cn.yorick.serialization;
/**
 * 
 * @author Yorick
 * version 1.0
 * derivation :
 * core
 * 2019��1��3��
 */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MyWorld {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		House house = new House();
		List<Animal> animals = new ArrayList<>();
		animals.add(new Animal("Bosco the dog", house));
		animals.add(new Animal("Ralph the hamster", house));
		animals.add(new Animal("Molly the cat", house));
		System.out.println("animals: " + animals);
		ByteArrayOutputStream bos1 = new ByteArrayOutputStream();
		ObjectOutputStream oos1 = new ObjectOutputStream(bos1);
		oos1.writeObject(animals);
		oos1.writeObject(animals);
//		System.out.println(bos1.size());
//		oos1.writeObject(animals);
//		System.out.println(bos1.size());
//		animals.add(new Animal("Molly the cats", house));
//		System.out.println(animals);
//		oos1.writeObject(animals);
//		System.out.println(bos1.size());
		ByteArrayOutputStream bos2 = new ByteArrayOutputStream();
		ObjectOutputStream oos2 = new ObjectOutputStream(bos2);
		oos2.writeObject(animals);
		
		ObjectInputStream ois1 = new ObjectInputStream(new ByteArrayInputStream(bos1.toByteArray()));
		//����������ı��˶���,�����������л�,�����л����ֶ����Ǳ仯ǰ�Ķ���,�����������л�����,���л�ʱ��
		//�ж����л��Ķ����Ƿ�Ϊͬһ������,�ж��������ڴ��ַ,�����ͬ��ôֻ�����л�һ��,
		//��Ҳ����Ϊʲô�����ظ����л�ͬһ������,�ظ���ȡ����һ����,��Ϊ���л�����ļ���ֻ��һ��
		ObjectInputStream ois2 = new ObjectInputStream(new ByteArrayInputStream(bos2.toByteArray()));
		List<Animal> animals1 = (List<Animal>) ois1.readObject();
		List<Animal> animals2 = (List<Animal>) ois1.readObject();
		
//		List<Animal> animals4 = (List<Animal>) ois1.readObject();
		List<Animal> animals3 = (List<Animal>) ois2.readObject();
//		List<Animal> animals5 = (List<Animal>) ois1.readObject();
		System.out.println("animals1: " + animals1);
		System.out.println("animals2: " + animals2);
		System.out.println("animals3: " + animals3);
//		System.out.println("animals4: " + animals4);
//		System.out.println("animals5: " + animals5);
	}
}
