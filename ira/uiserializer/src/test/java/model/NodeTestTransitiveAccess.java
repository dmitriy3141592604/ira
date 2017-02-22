package model;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

import utils.Behavior;

public class NodeTestTransitiveAccess extends NodeTest {

	@Test
	@Behavior("Узел без ребер достигает только себя")
	public void test$nodeWithoutEdges() {
		final Iterator<Node> accessibleNodesIterator = new Node(foo).transitiveAccess().iterator();

		assertEquals(new Node(foo), accessibleNodesIterator.next());
		assertEquals(false, accessibleNodesIterator.hasNext());
	}

	@Test
	@Behavior("Узел, указывающий сам на себя, достигает только себя")
	public void test$nodeWithCycle() {
		final Node fooNode = new Node(foo);
		fooNode.bindedWith(fooNode, null);

		final Iterator<Node> accessibleNodesIterator = fooNode.transitiveAccess().iterator();

		assertEquals(new Node(foo), accessibleNodesIterator.next());
		assertEquals(false, accessibleNodesIterator.hasNext());
	}

	// TODO: Если имена равны, но это разные объекты, то что делать?

	@Test
	@Behavior("Если узел указывает на другой узел, то тот достижим")
	public void test$bindedNodeIsAccessible() {
		final Node fooNode = new Node(foo);
		fooNode.bindedWith(new Node(bar), null);
		final Set<Node> transitiveAccess = fooNode.transitiveAccess();
		assertEquals(true, transitiveAccess.contains(new Node(bar)));
	}

	@Test
	@Behavior("Если узел указывает на другой узел через промежуточное ребто, то тот достижим")
	public void test$bindedNodeIsAccessibleThroughtAdditionalEdge() {
		final Node fooNode = new Node(foo);
		fooNode.bindedTo(new Node(bar), "edgeName3").bindedTo(new Node(baz), "bar-baz");

		final Set<Node> transitiveAccess = fooNode.transitiveAccess();
		assertEquals(true, transitiveAccess.contains(new Node(baz)));
	}

	@Test
	public void todo() {
		// TODO Проверить:
		// a -> b -> c -> b
		// a -> b -> c -> a
		// a -> b -> g; b-> h; b -> b; g -> h ; h -> g;
		// Построить матирицу 5x5 с частичным заполнением переходов ? Сомнительно, т.к сейчас не могу предсказать.
		// Сделать контроль по узлам.
		// Реализовать алгоритм другим способом. Поставить на дублирование ответа (Выводить предупреждение, что медленная обработка

		// fail("expected testing");
	}
}
