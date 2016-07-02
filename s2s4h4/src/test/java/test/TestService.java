package test;

import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.model.DbUser;
import com.service.UserServiceI;

public class TestService {

	
	private static final Logger logger = Logger.getLogger(TestService.class);
	@Test
	public void test(){
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"classpath:spring.xml"});
		UserServiceI userService = (UserServiceI) ac.getBean("userService");
		userService.test();
		DbUser u = new DbUser();
//		u.setName("lil2e2asdi3");
//		u.setPassword("123123");
//		u.setCreatetime(new Date());
//		userService.saveUser(u);
		u = userService.findUser("lilei");
//		userService.delUser("123");
		logger.info(u.getName());
//		
	}
}
