package org.i2g.ira.uibuilder;

public interface Tag {

	public Tag addChield(Tag tag);

	public void visit(TagVisitor visitor);

}
