package model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import utils.Behavior;

public class NodeTestToString {

	@Test
	@Behavior("toString содержит имя узла")
	public void test$toStringReturnNodeName() {
		assertEquals(true, new Node("foo").toString().contains("foo"));
	}

}
