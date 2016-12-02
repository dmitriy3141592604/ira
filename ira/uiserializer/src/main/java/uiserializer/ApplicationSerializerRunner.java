package uiserializer;

import application.Application;

public class ApplicationSerializerRunner {

	public static void main(String... args) {
		new ApplicationSerializer().process(Application.class);
	}

}
