package cn.yorick.serialization;

import java.io.Serializable;

public class Animal implements Serializable {
	private String name;
	private House preferredHouse;
	public Animal(String nm, House h) {
		super();
		this.name = nm;
		this.preferredHouse = h;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name + "[" + super.toString() + "], " + preferredHouse + "\n";
	}
	
}
