package cn.yorick.json;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

public class JSONTest {
	//JSON�ַ����򵥶���
	private static final String JSON_OBJ_STR = "{\"studentName\":\"lily\",\"studentAge\""
			+ ":12}";
	//JSON�ַ�����������
	private static final String JSON_ARRAY_STR = "[{\"studentName\":\"lily\",\"studentAge\":12},"
			+ "{\"studentName\":\"licy\",\"studentAge\":15}]";
	//���Ӹ�ʽJSON�ַ���
	private static final String COMPLEX_JSON_STR = "{\"teacherName\":\"crystall\",\"teacherAge\":27,"
			+ "\"course\":{\"courseName\":\"English\",\"code\":1270},\"students\":"
			+ "[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]}";
	@Test
	public void jsonObjectAndJsonStringTest() {
		//JSON�ַ���ת��ΪJSON����
		JSONObject jsonObject = JSONObject.parseObject(JSON_OBJ_STR);
		System.out.println("studentName:" + jsonObject.getString("studentName"));
		//��JSON����JSON�ַ���
		String jsonString = jsonObject.toJSONString();
		String jsonStringWithParameter = jsonObject.toJSONString(jsonObject);
		System.out.println("��JSONObject��JSON�ַ���:" + jsonString);
		System.out.println(jsonStringWithParameter);
	}
	@Test
	public void jsonObjectAndJsonArrayTest() {
		//JSON�ַ���ת��ΪJSON����
		JSONArray jsonArray = JSONObject.parseArray(JSON_ARRAY_STR);
		int size = jsonArray.size();
		JSONObject jsonObject;
		for (int i = 0; i < size; i++) {
			jsonObject = jsonArray.getJSONObject(i);
			System.out.println(jsonObject.toString());
		}
		//��һ�ֱ�����ʽ
		for (Object object : jsonArray) {
			jsonObject = (JSONObject) object;
			System.out.println(jsonObject.getInteger("studentAge"));
		}
		//��JSONArrayת��ΪJSON�ַ�������
		String jsonArrayString = JSONArray.toJSONString(jsonArray);
		System.out.println(jsonArrayString);
	}
	@Test
	public void complexJSONObjectAndJSONStringTest() {
		//����JSON�ַ���ת��ΪJSON����
		JSONObject jsonObject = JSONObject.parseObject(COMPLEX_JSON_STR);
		System.out.println(jsonObject.toJSONString());
		//���治��ɹ�,�ᱨ���쳣java.lang.ClassCastException: com.alibaba.fastjson.JSONArray cannot be cast to com.alibaba.fastjson.JSONObject
//		JSONObject array = jsonObject.getJSONObject("course");
		/*JSONArray array = jsonObject.getJSONArray("students");
		System.out.println(array.toString());��ָ���쳣*/
		JSONArray jsonArray = jsonObject.getJSONArray("course");
		String course1 = jsonArray.getString(0);
		System.out.println(course1);
//		jsonArray = jsonArray.getJSONArray(1);  IndexOutOfBoundsException
		jsonObject = jsonArray.getJSONObject(0);
		jsonArray = jsonObject.getJSONArray("students");
		//JSON���󲻹�����ͨObject����������,ͨ��toString�����Եõ�һ��JSOnString����
		System.out.println(jsonArray.toJSONString());
	}
	//json�ַ�����JavaBean����Ļ���ת��--ǰ��������Ҫ��һ��JavaBean��
	@Test
	public void jsonStringToJavaBeanTest() {
		JSONObject jsonObject = JSONObject.parseObject(JSON_OBJ_STR);
		//��һ�ַ�ʽ
		String studentName = jsonObject.getString("studentName");
		String studentAge = jsonObject.getString("studentAge");
		Student student = new Student(studentName, studentAge);
		System.out.println(student);
		//�ڶ��ַ�ʽ:ʹ��TypeReference<T>��,�����乹�췽��ʹ��protected��������,�ʴ���������,����Ҫע��fastjson�汾
		Student student2 = JSON.parseObject(JSON_OBJ_STR, new TypeReference<Student>() {});
//				jsonObject.parseObject(JSON_OBJ_STR, new TypeReference<Student>() {});  ��ͬ�Ͼ�
		
		System.out.println(student2);
		//�����ַ�ʽ,ʹ��Gson˼��
		Student student3 = JSON.parseObject(JSON_OBJ_STR, Student.class);
		System.out.println(student3);
	}
	@Test
	public void JavaBeanToJsonStringTest() {
		Student student = new Student("Yorick", "20");
		String jsonString = JSONObject.toJSONString(student);
		System.out.println(jsonString);
		JSONObject jsonObject = (JSONObject) JSON.toJSON(student);//JavaBeanת��ΪJSONObject��JSONArray
		System.out.println(jsonObject.toString());
	}
	//JSONArrayString��JavaBean��ת��
	@Test
	public void jsonArrayStringToJavaBeanTest() {
		//��һ�ַ�ʽ
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
		//�ڶ��ַ�ʽʹ��TypeReference<T>��
		List<Student> list2 = JSONArray.parseObject(JSON_ARRAY_STR, new TypeReference<ArrayList<Student>>() {});
		System.out.println("students: " + list2);
		//������ʹ��Gson��˼��
		List<Student> list3 = JSONArray.parseArray(JSON_ARRAY_STR, Student.class);
		System.out.println("students: " + list3);
	}
	//JavaBeanList��json�����ת��
	@Test
	public void javaBeanListToJsonArrayTest() {
		Student student1 = new Student("lily", "15");
		Student student2 = new Student("Lucy", "16");
		List<Student> list = new ArrayList<>();
		list.add(student1);
		list.add(student2);
		String jsonArrayString = JSONArray.toJSONString(list);//��list��û��Ԫ��,��ô����[]
		System.out.println(jsonArrayString);
	}
	//���ӵ�JSON�ַ�����JavaBean��ת��
	@Test
	public void ComplexJsonStringToJavaBeanTest() {
		 //��һ�ַ�ʽ,ʹ��TypeReference<T>��,�����乹�췽��ʹ��protected��������,�ʴ���������
		Teacher teacher = JSONObject.parseObject(COMPLEX_JSON_STR, new TypeReference<Teacher>() {});
		String teacherName = teacher.getTeacherName();
		String teacherAge = teacher.getTeacherAge() + "";
		Course course = teacher.getCourse();
		List<Student> students = teacher.getStudents();
		System.out.println(COMPLEX_JSON_STR);
		System.out.println(teacherName + ":" + teacherAge + course + students);
		System.out.println(teacher);
		//�ڶ��ַ�ʽʹ��Gson˼��
		Teacher teacher2 = JSON.parseObject(COMPLEX_JSON_STR, Teacher.class);
		System.out.println(teacher);
	}
	//���ӵ�JavaBean��JSON�ַ�����ת��
	@Test
	public void ComplexToJsonStringTest() {
		Teacher teacher = JSON.parseObject(COMPLEX_JSON_STR, Teacher.class);
		String jsonTeacherString = JSON.toJSONString(teacher);
		System.out.println(teacher);
		System.out.println(jsonTeacherString);
	}
	//����JavaBean��JSONObject֮���ת��
	@Test
	public void javaBeanAndJSONObjectTest() {
		Student student = JSON.parseObject(JSON_OBJ_STR, Student.class);
		List<Student> students = JSON.parseArray(JSON_ARRAY_STR, Student.class);
		Teacher teacher = JSON.parseObject(COMPLEX_JSON_STR, Teacher.class);
		//��ʽһ�����Ƚ�JavaBeanת��Ϊ��Ӧ���ַ���,Ȼ����ת��ΪJSONObject
		String studentJSON = JSON.toJSONString(student);
		String studentsJSON = JSON.toJSONString(students);
		String teacherJSON = JSON.toJSONString(teacher);
		JSONObject studentJSONObject = JSONObject.parseObject(studentJSON);
//		JSONObject studentsJSONObject = JSONObject.parseObject(studentsJSON);ע������ֻ��ת��ΪJSONArray
		JSONArray studentsJOSNArray = JSONArray.parseArray(studentsJSON);
		JSONObject teacherJSONObject = JSONObject.parseObject(teacherJSON);
		System.out.println(studentJSONObject);
		System.out.println(studentsJOSNArray);
		System.out.println(teacherJSONObject);
	    //��ʽ��ֱ����JOSNת��,Ȼ��ǿת
		studentJSONObject = (JSONObject) JSONObject.toJSON(student);
		studentsJOSNArray = (JSONArray) JSONArray.toJSON(students);
		teacherJSONObject = (JSONObject) JSONObject.toJSON(teacher);
	    System.out.println(studentJSONObject);
		System.out.println(studentsJOSNArray);
		System.out.println(teacherJSONObject);
	}
	//��JSONObjectת��ΪJavaBean
	@Test
	public void jsonObjectToJavaBeanTest() {
		JSONObject studentJSONObject = JSONObject.parseObject(JSON_OBJ_STR);
		JSONArray studentsJOSNArray = JSONArray.parseArray(JSON_ARRAY_STR);
		JSONObject teacherJSONObject = JSONObject.parseObject(COMPLEX_JSON_STR);
		//��JSONObjectץ��ΪJAvaBean�ͽ�json�ַ���ת��ΪJavaBean��ͬ,�������ַ�ʽ,��������������һ��
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
