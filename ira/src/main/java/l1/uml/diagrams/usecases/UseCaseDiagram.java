package l1.uml.diagrams.usecases;

import static utils.Tuple.newTuple;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import l1.uml.actors.Bayer;
import l1.uml.actors.SeniorCashier;
import l1.uml.usecases.BayProduct;
import l1.uml.usecases.RemoveCash;
import utils.Actor;
import utils.ActorUtils;
import utils.Diagramm;
import utils.Tuple;
import utils.UseCase;
import utils.UseCaseUtils;

@Diagramm
public class UseCaseDiagram {

	private final Bayer bayer = new Bayer();

	private final SeniorCashier seniorCashier = new SeniorCashier();

	private final List<Actor> actors = new ArrayList<Actor>();

	private final BayProduct bayProduct = new BayProduct();

	private final RemoveCash removeCash = new RemoveCash();

	private final List<UseCase> cases = new ArrayList<UseCase>();

	private final List<Tuple<Object, Object>> relations = new ArrayList<Tuple<Object, Object>>();

	public UseCaseDiagram() {
		collectItems(this, actors, ActorUtils::hasActorAnnotation);
		collectItems(this, cases, UseCaseUtils::hasUserCaseAnnotation);

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

	private <T> void collectItems(UseCaseDiagram diagram, List<T> actors, Predicate<Class<?>> p) {
		new ItemsCollector().collectFields(diagram, actors, p);
	}

	public List<Actor> getActors() {
		return actors;
	}

	public List<UseCase> getUserCases() {
		return cases;
	}

	public static void main(String... args) throws Exception {
		final UseCaseDiagram useCaseDiagram = new UseCaseDiagram();
		final String outFileName = "useCaseDiagram.dot";
		try (final PrintWriter out = new PrintWriter(new FileWriter(new File(outFileName)))) {
			out.println("digraph G{");
			for (final Tuple<Object, Object> t : useCaseDiagram.getRelations()) {
				out.println("  " + simpleName(t.getFst()) + " -> " + simpleName(t.getSnd()) + " ; ");
			}
			for (final Object actor : useCaseDiagram.getActors()) {
				out.println(" " + simpleName(actor) + "[shape=box]");
			}
			out.println("}");
		}

		try (PrintWriter out = new PrintWriter(new FileWriter(new File("UseCaseDiagram.html")))) {
			out.println("<img src='UseCaseDiagram.png'>");
		}

		Runtime.getRuntime().exec("c:\\wks\\prg\\Graphviz\\bin\\dot.exe -Tpng -oUseCaseDiagram.png " + outFileName);
	}

	private static String simpleName(Object fst) {
		return fst.getClass().getSimpleName();
	}

}
