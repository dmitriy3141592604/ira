package uiserializer;

import java.io.File;
import java.lang.reflect.Method;

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

	private TagVisitorSerializer visitor;

	{
		UIBuilderFactory factory;
		sb = new StringBuilder();
		documentRoot = new Element("html");
		factory = new UIBuilderFactory(documentRoot, new DefaultMethodTransformer());
		html = factory.create(HTMLElements.class);
		visitor = new TagVisitorSerializer(sb) {

			@Override
			public void beforeElement() {
				sb.append("\n");
				super.beforeElement();
			}

		};
	}

	private final String rootPath = "./html/";

	final OnFileWriter applictionHtml = new OnFileWriter(new File(rootPath + "application.html"));

	public void process(Class<?> application) {

		final TypeNavigator navigator = new TypeNavigator(application);
		{
			/** Создаем блок head **/
			final HTMLElements head = html.head();
			head.title().text("ККА система");
			head.meta(charset("utf-8"));
			head.link(rel("stylesheet"), href("reset200802.css"));
			head.link(rel("stylesheet"), href("style.css"));
		}
		{
			final HTMLElements body = html.body();
			{
				final HTMLElements pages = body.div(klass("pages"));
				{
					final HTMLElements ul = pages.ul();
					final HTMLElements pageContent = pages;
					for (final Method method : navigator.methods()) {
						final String value = method.getAnnotation(Translation.class).value();
						ul.li().text(value);
					}
					for (final Method method : navigator.methods()) {
						final HTMLElements page = pageContent.div(klass(method.getName()));
						final Class<?> pageItems = method.getReturnType();
						for (final Method pageMethod : pageItems.getMethods()) {
							final String methodName = pageMethod.getName();
							if (methodName.startsWith("set") || methodName.startsWith("get") || methodName.startsWith("submit")) {

								try {
									final Translation annotation = pageMethod.getAnnotation(Translation.class);
									page.label().text(annotation.value());

									page.input(type("text"));

								} catch (final Exception exception) {
									System.err.println("Errory by processing: " + pageItems + " " + pageMethod);
									throw new RuntimeException(exception);
								}
							}
						}
					}
				}
			}
		}

		documentRoot.visit(visitor);

		applictionHtml.accept(out -> out.write(sb.toString()));

	}

}
