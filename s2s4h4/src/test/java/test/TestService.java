package test;

import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.model.UtUser;
import com.service.MenuServiceI;
import com.service.UserServiceI;

public class TestService {

	
	private static final Logger logger = Logger.getLogger(TestService.class);
	@Test
	public void test(){
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"classpath:spring.xml"});
		MenuServiceI menuService = (MenuServiceI) ac.getBean("menuService");
	
//		
	}
}
