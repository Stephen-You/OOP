package cn.inheritance.statics;

public class FieldInheritance {
	public static void main(String[] args) {
		Son.name = "yorick";
		System.out.println(Father.name);
	}
}
