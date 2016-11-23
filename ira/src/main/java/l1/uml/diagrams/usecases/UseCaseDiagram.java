package l1.uml.diagrams.usecases;

import static utils.ActorUtils.hasActorAnnotation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import l1.uml.actors.Bayer;
import utils.Diagramm;

@Diagramm
public class UseCaseDiagram {

	private final Bayer bayer = new Bayer();

	private final List<Object> actors = new ArrayList<Object>();

	public UseCaseDiagram() {
		initializeActors(this, actors);
	}

	private void initializeActors(UseCaseDiagram diagram, List<Object> actors) {
		try {
			// FIXME Нужна тулза для получения всех объявленных пользовательских полей в пределах иерархии классов. И без исключений
			for (final Field declaredField : this.getClass().getDeclaredFields()) {
				declaredField.setAccessible(true);
				final Class<?> declaredFieldClass = declaredField.get(diagram).getClass();
				if (hasActorAnnotation(declaredFieldClass)) {
					actors.add(declaredField.get(diagram));
				}
			}
		} catch (final Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	public List<Object> getActors() {
		return actors;
	}

}
