package test.uibuilder;

import java.lang.reflect.Method;

import org.i2g.ira.uibuilder.Forest;
import org.i2g.ira.uibuilder.Transformer;
import org.i2g.ira.uibuilder.UIBuilderFactory;
import org.junit.Assert;
import org.junit.Before;

public abstract class UIBuilderTestBase<ValueType> extends Assert {

	protected UIBuilderFactory<ValueType> factory;

	protected Forest<ValueType> productRoot;

	@Before
	public void setUIBuilderFactoryBase() {
		productRoot = new Forest<ValueType>(newRootValueType());
		factory = new UIBuilderFactory<ValueType>(productRoot, newMethodTransformer());
	}

	protected abstract Transformer<Method, ValueType> newMethodTransformer();

	protected abstract ValueType newRootValueType();

}