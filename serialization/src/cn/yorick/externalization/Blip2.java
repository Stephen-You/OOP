package cn.yorick.externalization;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Blip2 implements Externalizable {

	int i;
//	 Blip2() {
//		System.out.println("Blip2 Constructor");
//	}
//	
	
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		System.out.println("Blip2 writeExternal!");
		
	}

//	public Blip2(int i) {
//	super();
//	this.i = i;
//}

	public Blip2() {
		System.out.println("Blip2 Constructor");
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		System.out.println("Blip2 readExternal!");
		
	}

}
