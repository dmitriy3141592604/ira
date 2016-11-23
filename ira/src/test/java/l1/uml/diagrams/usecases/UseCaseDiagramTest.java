package l1.uml.diagrams.usecases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.TreeSet;

import org.junit.Test;

import utils.ActorUtils;

public class UseCaseDiagramTest extends UseCaseDiagramTestBase {

	@Test
	public void test$checkActorsCount() {
		final String message = "В системе должен быть зарегистрирован хотя-бы один актор. Сейчас: " + diagram.getActors().size();
		assertFalse(message, diagram.getActors().isEmpty());
	}

	@Test
	public void test$checkActorsCount_() {

		for (final Object actorCandiate : diagram.getActors()) {
			final Class<?> actorCandidateClass = actorCandiate.getClass();
			if (!ActorUtils.hasActorAnnotation(actorCandidateClass)) {
				uniqueClassAccamulator.add(actorCandidateClass);
			}
		}

		assertEquals(new TreeSet<Class<?>>(), uniqueClassAccamulator);
	}

	@Test
	public void test$checkActorsCount__() {

		for (final Object actorCandiate : diagram.getActors()) {
			final Class<?> actorCandidateClass = actorCandiate.getClass();
			if (!ModelElement.class.isAssignableFrom(actorCandidateClass)) {
				uniqueClassAccamulator.add(actorCandidateClass);
			}
		}

		assertEquals(new TreeSet<Class<?>>(), uniqueClassAccamulator);
	}

}