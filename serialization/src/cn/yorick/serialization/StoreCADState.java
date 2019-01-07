package cn.yorick.serialization;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class StoreCADState {

	public static void main(String[] args) throws IOException {
		List<Class<? extends Shape>> shapeTypes = new ArrayList<>();
		shapeTypes.add(Circle.class);
		shapeTypes.add(Square.class);
		shapeTypes.add(Line.class);
		List<Shape> shapes = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			shapes.add(Shape.randomFactory());
		}
		for (int i = 0; i < 10; i++) {
			shapes.get(i).setColor(Shape.GREEN);
		}
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("CADState.out"));
		oos.writeObject(shapeTypes);
		Line.serializeStaticState(oos);
		oos.writeObject(shapes);
		System.out.println(shapes);
	}

}
