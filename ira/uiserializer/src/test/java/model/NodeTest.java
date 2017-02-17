package model;

import org.junit.Before;

public abstract class NodeTest extends NodeTestBase {

	/** Узел foo. Реальное имя не предсказуемо **/
	protected String foo;
	protected String bar;
	protected String baz;

	@Before
	public final void setUpNodeTest() {
		// TODO Вернуть random 2017.02.18
		foo = "foo"; // randomString();
		bar = "bar"; // randomString();
		baz = "baz"; // randomString();
	}

}
