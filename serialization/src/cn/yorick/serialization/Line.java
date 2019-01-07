package cn.yorick.serialization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Line extends Shape {
	private static int color = RED;
	
	public static void serializeStaticState(ObjectOutputStream out) throws IOException {
		out.writeInt(color);
	}
	//怎么序列化静态成员
	public static void deserializeStaticState(ObjectInputStream os) throws IOException {
		color = os.readInt();
	}
	public Line(int xPos, int yPos, int dimension) {
		super(xPos, yPos, dimension);
	}

	@Override
	public void setColor(int newColor) {
		color = newColor;
	}

	@Override
	public int getColor() {
		// TODO Auto-generated method stub
		return color;
	}

}
