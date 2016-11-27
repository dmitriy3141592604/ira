package org.i2g.ira.uibuilder;

import java.util.LinkedList;
import java.util.List;

public class Forest<ValueType extends Tag> {

	private final ValueType value;

	private final List<Forest<ValueType>> children = new LinkedList<Forest<ValueType>>();

	public Forest(ValueType value) {
		this.value = value;
	}

	public ValueType getValue() {
		return value;
	}

	public Forest<ValueType> addChield(ValueType childValue) {
		final Forest<ValueType> newChield = new Forest<ValueType>(childValue);
		children.add(newChield);
		return newChield;
	}

	public List<Forest<ValueType>> children() {
		return children;
	}

	public void visit(ForestVisitor<ValueType> visitor) {
		visitor.begin(value);
		children.forEach(child -> child.visit(visitor));
		visitor.end(value);
	}
}