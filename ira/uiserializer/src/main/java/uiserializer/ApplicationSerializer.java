package uiserializer;

import java.io.File;

import org.i2g.ira.uibuilder.Attributes;
import org.i2g.ira.uibuilder.Element;
import org.i2g.ira.uibuilder.HTMLElements;
import org.i2g.ira.uibuilder.UIBuilderFactory;

import test.uibuilder.DefaultMethodTransformer;
import test.uibuilder.TagVisitorSerializer;
import utils.Translation;
import utils.io.OnFileWriter;

public class ApplicationSerializer implements Attributes {

	private StringBuilder sb;

	private Element documentRoot;

	private HTMLElements html;

	final Class<Translation> trnsitionLabel = Translation.class;

	private TagVisitorSerializer visitor;

	{
		sb = new StringBuilder();
		documentRoot = new Element("html");
		final UIBuilderFactory factory = new UIBuilderFactory(documentRoot, new DefaultMethodTransformer());
		html = factory.create(HTMLElements.class);
		visitor = new IndentTagVisitorSerializer(sb);
	}

	private final String rootPath = "./html/";

	final OnFileWriter applictionHtml = new OnFileWriter(new File(rootPath + "application.html"));

	public void process(Class<?> application) {

		final TypeNavigator navigator = new TypeNavigator(application);
		asHeader();
		{
			final HTMLElements body = html.body();
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

							if (methodName.startsWith("set")) {
								page.label().text(pageMethod.from(trnsitionLabel).value());
								page.input(type("text"));
							}

							if (methodName.startsWith("get")) {
								page.label().text(pageMethod.from(trnsitionLabel).value());
								page.input(type("text"));
							}
							if (methodName.startsWith("submit")) {
								page.input(type("submit"));
							}
						}

					}
				}
			}
		}

		documentRoot.visit(visitor);

		applictionHtml.accept(out -> out.write(sb.toString()));

	}

	public void asHeader() {
		asSerializer(html.head());
	}

	private void asSerializer(final HTMLElements head) {
		head.title().text("ККА система");
		head.meta(charset("utf-8"));
		head.link(rel("stylesheet"), href("reset200802.css"));
		head.link(rel("stylesheet"), href("style.css"));
	}

}
