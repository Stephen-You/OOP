package cn.yorick.hessian;

import java.io.Serializable;

public class Employee implements Serializable{
	 private static final long serialVersionUID = 1L;
	    private int employeeId;
	    private String employeeName;
	    /**
	     * 使用transient关键字，表示该字段不序列化
	     */
	    private transient String department;
	 
	    public int getEmployeeId() {
	        return employeeId;
	    }
	    public void setEmployeeId(int employeeId) {
	        this.employeeId = employeeId;
	    }
	    public String getEmployeeName() {
	        return employeeName;
	    }
	    public void setEmployeeName(String employeeName) {
	        this.employeeName = employeeName;
	    }
	    public String getDepartment() {
	        return department;
	    }
	    public void setDepartment(String department) {
	        this.department = department;
	    }
	 
	    @Override
	    public String toString() {
	        return "Employee{" +
	                "employeeId=" + employeeId +
	                ", employeeName='" + employeeName + '\'' +
	                ", department='" + department + '\'' +
	                '}';
	    }
}
