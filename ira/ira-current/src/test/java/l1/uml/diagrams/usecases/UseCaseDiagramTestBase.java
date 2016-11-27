package l1.uml.diagrams.usecases;

import java.util.TreeSet;

import org.junit.Before;

import utils.IraTest;

public class UseCaseDiagramTestBase extends IraTest {

	protected UseCaseDiagram diagram;

	protected TreeSet<Class<?>> uniqueClassAccamulator;

	@Before
	public final void setUpUseCaseDiagramTestBase() {
		diagram = new UseCaseDiagram();
		uniqueClassAccamulator = new TreeSet<Class<?>>();
	}

}