package org.i2g.ira.uibuilder;

public interface Transformer<FromType, ToType> {

	public ToType applay(FromType from, Object[] args);

}
