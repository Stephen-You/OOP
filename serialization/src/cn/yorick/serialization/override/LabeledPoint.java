package cn.yorick.serialization.override;

import java.awt.geom.Point2D;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringBufferInputStream;

public class LabeledPoint implements Serializable {
	private String label;
	private transient Point2D.Double point;
	
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		out.writeDouble(point.getX());
		out.writeDouble(point.getY());
		PrintWriter pw = new PrintWriter("test.txt","utf-8");
		pw.print("write test!");
		pw.flush();
	}
	
	public void redaObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
		in.defaultReadObject();
		double x = in.readDouble();
		double y = in.readDouble();
		point = new Point2D.Double(x, y);
		PrintWriter pw = new PrintWriter("test.txt","utf-8");
		pw.print("write test!");
		pw.flush();
	}
	@Override
	public String toString() {
		
		return label + " : x = " + point.getX() + "; y = " + point.getY();
	}
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		LabeledPoint point = new LabeledPoint();
		point.label = "那年冬天,风和日丽,你如清泉入我心间!";
		point.point = new Point2D.Double(2.4, 3.0);
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("point.out"));
		oos.writeObject(point);
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("point.out"));
		LabeledPoint point2 = (LabeledPoint) ois.readObject();
//		double x = ois.readDouble();
		System.out.println(point2.point==null?"point=null":"point !=null");
//		System.out.println(point2.label + x);
//		System.out.println(point2);
	}
}
