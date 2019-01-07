package cn.yorick.serialization;

public class Circle extends Shape {
	private static int color = RED;
	public Circle(int xPos, int yPos, int dimension) {
		super(xPos, yPos, dimension);
	}

	@Override
	public void setColor(int newColor) {
		this.color = newColor;
	}

	@Override
	public int getColor() {
		return color;
	}

}
