package utils;

import org.i2g.ira.complaint.save.ComplaintSaveAction;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Executor {

	public static void main(String... args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ira.xml");
		ComplaintSaveAction bean = context.getBean(ComplaintSaveAction.class);
		Complaint complaint = null;
		bean.execute(complaint);
	}
}
