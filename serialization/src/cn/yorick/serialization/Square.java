package cn.yorick.serialization;

public class Square extends Shape {
	private static int color;
	public Square(int xPos, int yPos, int dimension) {
		super(xPos, yPos, dimension);
		color = RED;
	}

	@Override
	public void setColor(int newColor) {
		color = newColor;
	}

	@Override
	public int getColor() {
		return color;
	}

}
