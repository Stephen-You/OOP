package cn.yorick.serialization;
/**
 * 
 * @author Yorick
 * version 1.0
 * derivation :
 * core
 * 2019年1月3日
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
		//我们在这里改变了对象,并且重新序列化,反序列化后发现对象是变化前的对象,这是由于序列化机制,序列化时会
		//判断序列化的对象是否为同一个对象,判断依据是内存地址,如果相同那么只会序列化一次,
		//这也就是为什么我们重复序列化同一个对象,重复读取后是一样的,因为序列化后的文件中只有一份
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
