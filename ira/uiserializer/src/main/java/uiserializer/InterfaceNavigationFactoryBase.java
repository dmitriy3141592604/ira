package uiserializer;

public class InterfaceNavigationFactoryBase {

	protected ClassLoader classLoader = getClass().getClassLoader();

	public void setClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

}
