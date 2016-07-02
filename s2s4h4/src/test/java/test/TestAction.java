package test;

import org.apache.struts2.StrutsTestCase;

import com.opensymphony.xwork2.ActionProxy;

public class TestAction extends StrutsTestCase{

	
	public void test() throws Exception{
		 ActionProxy proxy = null;
		 TestAction test = null;
		 proxy = getActionProxy("/userAction!saveUser.action");
		test = (TestAction) proxy.getAction();
		String result = proxy.execute();
	}
}
