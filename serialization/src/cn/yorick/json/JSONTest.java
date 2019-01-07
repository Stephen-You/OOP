package cn.yorick.json;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

public class JSONTest {
	//JSON字符串简单对象
	private static final String JSON_OBJ_STR = "{\"studentName\":\"lily\",\"studentAge\""
			+ ":12}";
	//JSON字符串数组类型
	private static final String JSON_ARRAY_STR = "[{\"studentName\":\"lily\",\"studentAge\":12},"
			+ "{\"studentName\":\"licy\",\"studentAge\":15}]";
	//复杂格式JSON字符串
	private static final String COMPLEX_JSON_STR = "{\"teacherName\":\"crystall\",\"teacherAge\":27,"
			+ "\"course\":{\"courseName\":\"English\",\"code\":1270},\"students\":"
			+ "[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]}";
	@Test
	public void jsonObjectAndJsonStringTest() {
		//JSON字符串转变为JSON对象
		JSONObject jsonObject = JSONObject.parseObject(JSON_OBJ_STR);
		System.out.println("studentName:" + jsonObject.getString("studentName"));
		//从JSON对象到JSON字符串
		String jsonString = jsonObject.toJSONString();
		String jsonStringWithParameter = jsonObject.toJSONString(jsonObject);
		System.out.println("从JSONObject到JSON字符串:" + jsonString);
		System.out.println(jsonStringWithParameter);
	}
	@Test
	public void jsonObjectAndJsonArrayTest() {
		//JSON字符串转变为JSON数组
		JSONArray jsonArray = JSONObject.parseArray(JSON_ARRAY_STR);
		int size = jsonArray.size();
		JSONObject jsonObject;
		for (int i = 0; i < size; i++) {
			jsonObject = jsonArray.getJSONObject(i);
			System.out.println(jsonObject.toString());
		}
		//另一种遍历方式
		for (Object object : jsonArray) {
			jsonObject = (JSONObject) object;
			System.out.println(jsonObject.getInteger("studentAge"));
		}
		//将JSONArray转变为JSON字符串数组
		String jsonArrayString = JSONArray.toJSONString(jsonArray);
		System.out.println(jsonArrayString);
	}
	@Test
	public void complexJSONObjectAndJSONStringTest() {
		//复杂JSON字符串转变为JSON对象
		JSONObject jsonObject = JSONObject.parseObject(COMPLEX_JSON_STR);
		System.out.println(jsonObject.toJSONString());
		//下面不会成功,会报出异常java.lang.ClassCastException: com.alibaba.fastjson.JSONArray cannot be cast to com.alibaba.fastjson.JSONObject
//		JSONObject array = jsonObject.getJSONObject("course");
		/*JSONArray array = jsonObject.getJSONArray("students");
		System.out.println(array.toString());空指针异常*/
		JSONArray jsonArray = jsonObject.getJSONArray("course");
		String course1 = jsonArray.getString(0);
		System.out.println(course1);
//		jsonArray = jsonArray.getJSONArray(1);  IndexOutOfBoundsException
		jsonObject = jsonArray.getJSONObject(0);
		jsonArray = jsonObject.getJSONArray("students");
		//JSON对象不管是普通Object对象还是数组,通过toString都可以得到一个JSOnString对象
		System.out.println(jsonArray.toJSONString());
	}
	//json字符串到JavaBean对象的互相转换--前提是我们要有一个JavaBean类
	@Test
	public void jsonStringToJavaBeanTest() {
		JSONObject jsonObject = JSONObject.parseObject(JSON_OBJ_STR);
		//第一种方式
		String studentName = jsonObject.getString("studentName");
		String studentAge = jsonObject.getString("studentAge");
		Student student = new Student(studentName, studentAge);
		System.out.println(student);
		//第二种方式:使用TypeReference<T>类,由于其构造方法使用protected进行修饰,故创建其子类,这里要注意fastjson版本
		Student student2 = JSON.parseObject(JSON_OBJ_STR, new TypeReference<Student>() {});
//				jsonObject.parseObject(JSON_OBJ_STR, new TypeReference<Student>() {});  等同上句
		
		System.out.println(student2);
		//第三种方式,使用Gson思想
		Student student3 = JSON.parseObject(JSON_OBJ_STR, Student.class);
		System.out.println(student3);
	}
	@Test
	public void JavaBeanToJsonStringTest() {
		Student student = new Student("Yorick", "20");
		String jsonString = JSONObject.toJSONString(student);
		System.out.println(jsonString);
		JSONObject jsonObject = (JSONObject) JSON.toJSON(student);//JavaBean转变为JSONObject或JSONArray
		System.out.println(jsonObject.toString());
	}
	//JSONArrayString到JavaBean的转变
	@Test
	public void jsonArrayStringToJavaBeanTest() {
		//第一种方式
		JSONArray jsonArray = JSONArray.parseArray(JSON_ARRAY_STR);
		List<Student> list = new ArrayList<>();	
		Student student = null;
		for (Object object : jsonArray) {
//			student = (Student) object;  ClassCastException  JSONObject cannot be cast to student
			JSONObject jsonObject = (JSONObject) object;
			String studentName = jsonObject.getString("studentName");
			int studentAge = jsonObject.getInteger("studentAge");
			student = new Student(studentName, String.valueOf(studentAge));
			list.add(student);
		}
		System.out.println("students:" + list);
		//第二种方式使用TypeReference<T>类
		List<Student> list2 = JSONArray.parseObject(JSON_ARRAY_STR, new TypeReference<ArrayList<Student>>() {});
		System.out.println("students: " + list2);
		//第三种使用Gson的思想
		List<Student> list3 = JSONArray.parseArray(JSON_ARRAY_STR, Student.class);
		System.out.println("students: " + list3);
	}
	//JavaBeanList到json数组的转换
	@Test
	public void javaBeanListToJsonArrayTest() {
		Student student1 = new Student("lily", "15");
		Student student2 = new Student("Lucy", "16");
		List<Student> list = new ArrayList<>();
		list.add(student1);
		list.add(student2);
		String jsonArrayString = JSONArray.toJSONString(list);//若list中没有元素,那么返回[]
		System.out.println(jsonArrayString);
	}
	//复杂的JSON字符串到JavaBean的转变
	@Test
	public void ComplexJsonStringToJavaBeanTest() {
		 //第一种方式,使用TypeReference<T>类,由于其构造方法使用protected进行修饰,故创建其子类
		Teacher teacher = JSONObject.parseObject(COMPLEX_JSON_STR, new TypeReference<Teacher>() {});
		String teacherName = teacher.getTeacherName();
		String teacherAge = teacher.getTeacherAge() + "";
		Course course = teacher.getCourse();
		List<Student> students = teacher.getStudents();
		System.out.println(COMPLEX_JSON_STR);
		System.out.println(teacherName + ":" + teacherAge + course + students);
		System.out.println(teacher);
		//第二种方式使用Gson思想
		Teacher teacher2 = JSON.parseObject(COMPLEX_JSON_STR, Teacher.class);
		System.out.println(teacher);
	}
	//复杂的JavaBean到JSON字符串的转变
	@Test
	public void ComplexToJsonStringTest() {
		Teacher teacher = JSON.parseObject(COMPLEX_JSON_STR, Teacher.class);
		String jsonTeacherString = JSON.toJSONString(teacher);
		System.out.println(teacher);
		System.out.println(jsonTeacherString);
	}
	//进行JavaBean和JSONObject之间的转变
	@Test
	public void javaBeanAndJSONObjectTest() {
		Student student = JSON.parseObject(JSON_OBJ_STR, Student.class);
		List<Student> students = JSON.parseArray(JSON_ARRAY_STR, Student.class);
		Teacher teacher = JSON.parseObject(COMPLEX_JSON_STR, Teacher.class);
		//方式一就是先将JavaBean转变为对应的字符串,然后再转变为JSONObject
		String studentJSON = JSON.toJSONString(student);
		String studentsJSON = JSON.toJSONString(students);
		String teacherJSON = JSON.toJSONString(teacher);
		JSONObject studentJSONObject = JSONObject.parseObject(studentJSON);
//		JSONObject studentsJSONObject = JSONObject.parseObject(studentsJSON);注意数组只能转变为JSONArray
		JSONArray studentsJOSNArray = JSONArray.parseArray(studentsJSON);
		JSONObject teacherJSONObject = JSONObject.parseObject(teacherJSON);
		System.out.println(studentJSONObject);
		System.out.println(studentsJOSNArray);
		System.out.println(teacherJSONObject);
	    //方式二直接向JOSN转变,然后强转
		studentJSONObject = (JSONObject) JSONObject.toJSON(student);
		studentsJOSNArray = (JSONArray) JSONArray.toJSON(students);
		teacherJSONObject = (JSONObject) JSONObject.toJSON(teacher);
	    System.out.println(studentJSONObject);
		System.out.println(studentsJOSNArray);
		System.out.println(teacherJSONObject);
	}
	//将JSONObject转变为JavaBean
	@Test
	public void jsonObjectToJavaBeanTest() {
		JSONObject studentJSONObject = JSONObject.parseObject(JSON_OBJ_STR);
		JSONArray studentsJOSNArray = JSONArray.parseArray(JSON_ARRAY_STR);
		JSONObject teacherJSONObject = JSONObject.parseObject(COMPLEX_JSON_STR);
		//将JSONObject抓变为JAvaBean和将json字符串转变为JavaBean相同,都有三种方式,我们下面来介绍一下
		Student student = JSONObject.parseObject(studentJSONObject.toJSONString(), new TypeReference<Student>() {});
		Student student2 = JSONObject.parseObject(studentJSONObject.toJSONString(), Student.class);
		List<Student> list = JSONArray.parseObject(studentsJOSNArray.toJSONString(), new TypeReference<ArrayList<Student>>() {});
		List<Student> list2 = JSONArray.parseArray(studentsJOSNArray.toJSONString(),Student.class);
		System.out.println(student);
		System.out.println(student2);
		System.out.println(list);
		System.out.println(list2);
	}
}
