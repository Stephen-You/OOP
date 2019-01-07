package cn.yorick.serialization.practice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import cn.yorick.serialization.Alien;
import cn.yorick.serialization.FreezeAlice;

public class ThawAlice {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("X.file"));
		Object alice = ois.readObject();
		FreezeAlice fa = (FreezeAlice) ois.readObject();
		System.out.println(alice.getClass());
		System.out.println(fa.getClass());
		System.out.println(alice.getClass().getResource("/"));
	}

}
