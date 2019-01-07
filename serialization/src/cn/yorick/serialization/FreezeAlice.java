package cn.yorick.serialization;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import cn.yorick.Alien;

/**
 * 
 * @author Yorick
 * version 1.0
 * derivation :Java编程思想  18.12.1
 * core Java反序列化是要建立在原有类文件的基础上,若没有将不能反序列化
 * 2019年1月3日
 */
public class FreezeAlice implements Serializable{
	public static void main(String[] args) throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("X.file"));
//		Alice alice = new Alice();
		FreezeAlice fa = new FreezeAlice();
		cn.yorick.serialization.Alien alien = new cn.yorick.serialization.Alien();
		oos.writeObject(alien);
		oos.writeObject(fa);
		oos.close();
	}
}
