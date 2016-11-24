package l1.uml.diagrams.usecases;

import static utils.Tuple.newTuple;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import l1.uml.actors.Bayer;
import l1.uml.actors.SeniorCashier;
import l1.uml.usecases.BayProduct;
import l1.uml.usecases.RemoveCash;
import utils.ActorUtils;
import utils.Diagramm;
import utils.Tuple;
import utils.UseCaseUtils;

@Diagramm
public class UseCaseDiagram {

	private final Bayer bayer = new Bayer();

	private final SeniorCashier seniorCashier = new SeniorCashier();

	//private final Revisor revisor = new Revisor();

	private final List<Object> actors = new ArrayList<Object>();

	private final BayProduct bayProduct = new BayProduct();

	private final RemoveCash removeCash = new RemoveCash();

	private final List<Object> cases = new ArrayList<Object>();

	private List<Tuple<Object, Object>> relations = new ArrayList<Tuple<Object, Object>>();

	public UseCaseDiagram() {
		initializeActors(this, actors, ActorUtils::hasActorAnnotation);
		initializeActors(this, cases, UseCaseUtils::hasUserCaseAnnotation);

		buildModel();
	}

	private void buildModel() {
		bind(bayer, bayProduct);
		bind(seniorCashier, removeCash);
	}

	private void bind(Object actor, Object useCase) {
		relations.add(newTuple(actor, useCase));
	}

	public List<Tuple<Object, Object>> getRelations() {
		return relations;
	}

	// TODO move to other place
	private void initializeActors(UseCaseDiagram diagram, List<Object> actors, Predicate<Class<?>> p) {
		try {
			// FIXME Нужна тулза для получения всех объявленных пользовательских
			// полей в пределах иерархии классов. И без исключений
			for (final Field declaredField : this.getClass().getDeclaredFields()) {
				declaredField.setAccessible(true);
				final Class<?> declaredFieldClass = declaredField.get(diagram).getClass();
				if (p.test(declaredFieldClass)) {
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

	public List<Object> getUserCases() {
		return actors;
	}

	public static void main(String... args) throws Exception {
		UseCaseDiagram useCaseDiagram = new UseCaseDiagram();
		String outFileName = "useCaseDiagram.dot";
		try (PrintWriter out = new PrintWriter(new FileWriter(new File(outFileName)))) {
			out.println("digraph G{");
			for (final Tuple<Object, Object> t : useCaseDiagram.getRelations()) {
				out.println("  " + simpleName(t.getFst()) + " -> " + simpleName(t.getSnd()) + " ; ");
			}
			for (final Object actor : useCaseDiagram.getActors()) {
				out.println(" " + simpleName(actor) + "[shape=box]");
			}
			out.println("}");
		}

		try(PrintWriter out = new PrintWriter(new FileWriter(new File("UseCaseDiagram.html")))) {
			out.println("<img src='UseCaseDiagram.png'>");
		}
		
		Runtime.getRuntime().exec("c:\\wks\\prg\\Graphviz\\bin\\dot.exe -Tpng -oUseCaseDiagram.png " + outFileName);
	}

	private static String simpleName(Object fst) {
		return fst.getClass().getSimpleName();
	}

}
