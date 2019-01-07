package cn.yorick.serialization;

import java.io.Serializable;

/**
 * 
 * @author Yorick
 * version 1.0
 * core  ¹·Éþ
 * 2019Äê1ÔÂ2ÈÕ
 */
public class Collar implements Serializable{
	
	private int size;
	private String color;
	
	public Collar(int size, String color) {
		super();
		this.size = size;
		this.color = color;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getSize() + getColor();
	}
}
