package test.uibuilder;

import org.i2g.ira.uibuilder.Forest;
import org.junit.Assert;
import org.junit.Before;

public class ForestTestBase extends Assert {

	protected Forest<String> root;

	protected StringBuilder log;

	@Before
	public final void setUpForestTestBase() {
		this.root = newForest("root");
		this.log = new StringBuilder();
	}

	protected Forest<String> newForest(String string) {
		return new Forest<String>("root");
	}

}