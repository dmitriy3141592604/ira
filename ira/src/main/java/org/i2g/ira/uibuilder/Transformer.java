package org.i2g.ira.uibuilder;

public interface Transformer<FromType, ToType> {

	public ToType transform(FromType from, Object[] args);

}
