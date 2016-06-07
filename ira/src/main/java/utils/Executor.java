package utils;

import org.i2g.ira.ProcessRunner;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Executor {

	public static void main(String... args) {
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ira.xml")) {
			ProcessRunner bean = context.getBean(ProcessRunner.class);
			Complaint complaint = null;
			bean.execute(complaint, "complaint.save");
		}
	}
}
