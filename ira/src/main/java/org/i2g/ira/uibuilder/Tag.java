package org.i2g.ira.uibuilder;

public interface Tag {

	public void start(StringBuilder log);

	public void body(StringBuilder log);

	public void end(StringBuilder log);

}
