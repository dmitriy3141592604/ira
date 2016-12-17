package uiserializer;

import org.mockito.Mockito;

// TODO move to test utils
public interface Mockitor {

	default <T> T mock(Class<T> classToMock) {
		return Mockito.mock(classToMock);
	}

}
