package org.konghao.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class TestEncode {

	@Test
	public void testEncode() {
		System.err.println(new Md5Hash("123", "user"));
	}

	private Subject login(String username,String password) {
		SecurityManager manager = new IniSecurityManagerFactory("classpath:shiro.ini").getInstance();
		SecurityUtils.setSecurityManager(manager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		subject.login(token);
		return subject;
	}
	
	@Test
	public void TestPasswordservice() {
		DefaultPasswordService service = new DefaultPasswordService();
		String str = service.encryptPassword("123");
		String str1 = service.encryptPassword("123");
		System.err.println(str.equals(str1));
		System.err.println(service.passwordsMatch("123", str));
		System.err.println(service.passwordsMatch("123", str1));
	}
	
	@Test
	public void testPasswordTestRealm() {
		login("user", "123");
	}
}
