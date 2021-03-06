package uiserializer;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import org.junit.Test;

import application.Application;

public class ApplicationSerializerRunner {

	public static void main(String... args) {
		new ApplicationSerializer().process(new PrintWriter(new OutputStreamWriter(System.out)), Application.class);
	}

	@Test
	public void execute() {
		main();
	}
}
