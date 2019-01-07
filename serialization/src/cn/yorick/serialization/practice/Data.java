package cn.yorick.serialization.practice;

import java.io.Serializable;
/**
 * 
 * @author Yorick
 * version 1.0
 * derivation : Java编程思想 18.12
 * core
 * 2019年1月3日
 */
public class Data implements Serializable {
	private int n;

	public Data(int n) {
		super();
		this.n = n;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return Integer.toString(n);
	}
}
