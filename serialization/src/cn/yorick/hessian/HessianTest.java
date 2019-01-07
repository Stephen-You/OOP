package cn.yorick.hessian;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;

import org.junit.Test;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

public class HessianTest {

	public static void main(String[] args) throws IOException {
		Employee employee = new Employee();
		employee.setEmployeeId(90);
		employee.setEmployeeName("Yorick");
		employee.setDepartment("���л�");
		byte[] bytes = serialize(employee);
		Employee employee2 = (Employee) deSerialize(bytes);
		if(employee != employee2) {
			System.out.println(employee);
			System.out.println(employee2);
		}
	}
	//Hessianʵ�����л�
	public static byte[] serialize(Object object) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		HessianOutput hop = new HessianOutput(bos);
		hop.writeObject(object);
		return bos.toByteArray();
	}
	
	//Hessianʵ�ַ����л�
	public static Object deSerialize(byte[] objectBytes) throws IOException {
		ByteArrayInputStream bis = new ByteArrayInputStream(objectBytes);
		HessianInput his = new HessianInput(bis);
		return his.readObject();
	}
}
