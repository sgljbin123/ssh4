package test;

import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.model.UtUser;
import com.service.UserServiceI;

public class TestService {

	
	private static final Logger logger = Logger.getLogger(TestService.class);
	@Test
	public void test(){
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"classpath:spring.xml"});
		UserServiceI userService = (UserServiceI) ac.getBean("userService");
//		userService.test();
		UtUser u = new UtUser();
		u.setName("23s3");
		u.setPassword("123asdas#d1454562@487843");
		u.setCreatetime(new Date());
//		userService.saveUser(u);
//		u = userService.findUser("lilei");
//		userService.delUser("123");
//		logger.info(u.getName());
//		
	}
}
