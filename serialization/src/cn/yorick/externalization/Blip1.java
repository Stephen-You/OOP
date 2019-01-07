package cn.yorick.externalization;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
/**
 * 
 * @author Yorick
 * version 1.0
 * derivation :Java编程思想  18.12.2
 * core 序列化控制
 * 2019年1月3日
 */
public class Blip1 implements Externalizable {

	public Blip1() {
		System.out.println("Blip1 Constructor");
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		System.out.println("Blip1 writeExternal!");
		
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		System.out.println("Blip1 readExternal!");
		
	}

	

}
