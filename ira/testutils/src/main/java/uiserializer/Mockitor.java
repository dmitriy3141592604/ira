package uiserializer;

import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

/** "Упрощает использование статических методов Mockito" **/
public interface Mockitor {

	default <T> T mock(Class<T> classToMock) {
		return Mockito.mock(classToMock);
	}

	default <T> T verify(T mock) {
		return Mockito.verify(mock);
	}

	default <T> OngoingStubbing<T> when(T methodCall) {
		return Mockito.when(methodCall);
	}

}
