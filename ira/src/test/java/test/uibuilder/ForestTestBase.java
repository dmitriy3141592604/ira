package test.uibuilder;

import org.i2g.ira.uibuilder.Element;
import org.i2g.ira.uibuilder.Forest;
import org.i2g.ira.uibuilder.Tag;
import org.junit.Before;

import utils.IraTest;

public class ForestTestBase extends IraTest {

	protected Forest<Tag> root;

	protected StringBuilder log;

	@Before
	public final void setUpForestTestBase() {
		this.root = newForest("root");
		this.log = new StringBuilder();
	}

	protected Forest<Tag> newForest(String string) {
		return new Forest<Tag>(new Element("root"));
	}

}