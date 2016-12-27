package uiserializer;

import org.mockito.Mockito;

// TODO move to test utils
public interface Mockitor {

	default <T> T mock(Class<T> classToMock) {
		return Mockito.mock(classToMock);
	}

	default <T> T verify(T mock) {
		return Mockito.verify(mock);
	}

}
