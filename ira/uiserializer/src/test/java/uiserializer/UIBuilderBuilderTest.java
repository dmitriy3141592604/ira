package uiserializer;

import static org.junit.Assert.assertSame;

import org.junit.Test;

import test.uibuilder.DefaultMethodTransformer;

public class UIBuilderBuilderTest extends UIBuilderBuilderTestBase {

	@Test
	public void test$setValueTransformer$vanila() {
		assertSame(bb, bb.setValueTransformer(mock(DefaultMethodTransformer.class)));
	}

}
