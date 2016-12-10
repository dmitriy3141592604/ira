package uiserializer;

import java.io.File;

import org.i2g.ira.uibuilder.Attributes;
import org.i2g.ira.uibuilder.HTMLElements;

import logic.ConditionSimple;
import utils.Translation;
import utils.io.OnFileWriter;

public class ApplicationSerializer extends UIBuilderFactoryBuilder implements Attributes {

	private final static Class<Translation> trnsitionLabel = Translation.class;

	private final String rootPath = "./html";

	final OnFileWriter applictionHtml = new OnFileWriter(new File(rootPath + "/" + "application.html"));

	public ApplicationSerializer() {
		build();
	}

	public void process(Class<?> application) {

		final TypeNavigator navigator = new TypeNavigator(application);
		asHeader();
		{
			final HTMLElements body = getHtml().body();
			{
				final HTMLElements pages = body.div(klass("pages"));
				{

					{
						final HTMLElements ul = pages.ul();
						navigator.models().forEach(m -> ul.li().text(m.from(trnsitionLabel).value()));
					}

					for (final MethodModel method : navigator.models()) {

						final HTMLElements page = pages.div(klass(method.getName()));
						for (final MethodModel pageMethod : method.getReturnTypeNavigator().models()) {

							final String methodName = pageMethod.getName();

							final ConditionSimple isSetter = new ConditionSimple("isSetter", methodName.startsWith("set"));
							final ConditionSimple isGetter = new ConditionSimple("isGetter", methodName.startsWith("get"));
							final ConditionSimple isSubmit = new ConditionSimple("isSubmit", methodName.startsWith("submit"));

							isSetter.run(() -> {
								page.label().text(pageMethod.from(trnsitionLabel).value());
								page.input(type("text"));
							});

							isGetter.run(() -> {
								page.label().text(pageMethod.from(trnsitionLabel).value());
								page.input(type("text"));
							});

							isSubmit.run(() -> {
								page.input(type("submit"));
							});
						}

					}
				}
			}
		}

		root.visit(visitor);

		applictionHtml.accept(out -> out.write(getSb().toString()));

	}

	public void asHeader() {
		asSerializer(getHtml().head());
	}

	private void asSerializer(final HTMLElements head) {
		head.title().text("ККА система");
		head.meta(charset("utf-8"));
		head.link(rel("stylesheet"), href("reset200802.css"));
		head.link(rel("stylesheet"), href("style.css"));
	}

}
