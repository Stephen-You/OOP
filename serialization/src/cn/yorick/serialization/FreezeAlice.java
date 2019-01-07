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
 * derivation :Java���˼��  18.12.1
 * core Java�����л���Ҫ������ԭ�����ļ��Ļ�����,��û�н����ܷ����л�
 * 2019��1��3��
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
