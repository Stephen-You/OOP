package cn.yorick.serialization;
/**
 * 
 * @author Yorick
 * version 1.0
 * derivation :
 * core
 * 2019Äê1ÔÂ3ÈÕ
 */

import java.io.Serializable;
import java.util.Random;

public abstract class Shape implements Serializable{
	public static final int RED = 1, BLUE = 2, GREEN = 3;
	private int xPos, yPos, dimension;
	private static Random random = new Random(47);
	private static int counter = 0;
	public abstract void setColor(int newColor);
	public abstract int getColor();
	public Shape(int xPos, int yPos, int dimension) {
		super();
		this.xPos = xPos;
		this.yPos = yPos;
		this.dimension = dimension;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getClass() + "color[" + getColor() + "] xPos[" +
				xPos + "] yPos[" + yPos + "] dim[" + dimension + "]\n";
	}
	
	public static Shape randomFactory() {
		int xVal = random.nextInt(100);
		int yVal = random.nextInt(100);
		int dimension = random.nextInt(100);
		switch (counter++ % 3) {
			case 0: return new Circle(xVal,yVal,dimension);
			case 1: return new Square(xVal,yVal,dimension);
			case 2: return new Line(xVal,yVal,dimension);
		}
		return null;
	}
}
