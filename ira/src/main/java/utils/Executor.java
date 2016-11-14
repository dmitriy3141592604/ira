package utils;

import org.i2g.ira.FileDao;
import org.i2g.ira.ProcessRunner;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Executor {

	public static void main(String... args) {
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ira.xml")) {

			FileDao fileDao = context.getBean(FileDao.class);
			System.out.println(fileDao.sizeByName("name"));

			ProcessRunner bean = context.getBean(ProcessRunner.class);

			Complaint complaint = new Complaint() {
			};
			IraProcessorsContextHolder.setValue(complaint);

			bean.execute("complaint.save");

        }
	}
}
