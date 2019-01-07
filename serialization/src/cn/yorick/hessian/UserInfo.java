package cn.yorick.hessian;

import java.io.IOException;

public class UserInfo extends User {
    private String username;
    public static void main(String[] args) throws IOException {
    	UserInfo user = new UserInfo();
        user.setUsername("hello world");
        user.setPassword("buzhidao");
        user.setAge(21);
        byte[] bytes = HessianTest.serialize(user);
        UserInfo info = (UserInfo) HessianTest.deSerialize(bytes);
        System.out.println(user);
        System.out.println(info);
	}
    
}