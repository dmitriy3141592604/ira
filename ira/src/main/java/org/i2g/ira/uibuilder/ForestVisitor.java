package org.i2g.ira.uibuilder;

public interface ForestVisitor<ValueType> {

	void begin(ValueType value);

	void end(ValueType value);

}