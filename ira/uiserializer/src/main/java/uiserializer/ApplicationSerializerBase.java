package uiserializer;

import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.i2g.ira.uibuilder.Element;
import org.i2g.ira.uibuilder.HTMLElements;

import application.support.WithName;
import utils.io.OnFileWriter;

public abstract class ApplicationSerializerBase<T> implements WithSimpleClassName {

	public static class ApplicationSerializerConfiguration {

		private final OnFileWriter fileWriter;

		public ApplicationSerializerConfiguration(WithName namedClass) {
			// TODO Добавить getDefaultFileSystem#getPath
			final Path path = FileSystems.getDefault().getPath("target", namedClass.name() + ".html");
			this.fileWriter = new OnFileWriter(path);
		}

		public OnFileWriter getWriter() {
			return fileWriter;
		}

	}

	private final ApplicationSerializerConfiguration configuration = new ApplicationSerializerConfiguration(this);

	protected abstract void build(T app, ComponentBuilder cb);

	public void process(PrintWriter out, Class<T> applicationClass) {
		final StringBuilder stringBuilder = new StringBuilder();
		final UIBuilderBuilder uiBuilderBuilder = new UIBuilderBuilder();
		final Class<HTMLElements> interfaceClass = HTMLElements.class;
		final Element element = new Element("html");
		final UIBuilder uiBuilder = uiBuilderBuilder.setInterface(interfaceClass).setElement(element).build();
		final T app = newApplication(applicationClass);
		final ComponentBuilder cb = newBodyBuilder();

		build(app, cb);

		cb.render(uiBuilder.getHtml());
		uiBuilder.serializeContent(stringBuilder);

		final String sc = stringBuilder.toString();
		out.println(sc);

		configuration.getWriter().accept(f -> f.println(sc));
	}

	protected Empty newEmpty() {
		return new Empty();
	}

	protected SectionName newHeader() {
		return new SectionName();
	}

	protected PagesList newPagesList() {
		return new PagesList();
	}

	protected PageLayout newPageLayout() {
		return new PageLayout();
	}

	protected BodyBuilder newBodyBuilder() {
		return new BodyBuilder();
	}

	protected T newApplication(Class<T> applicationClass) {
		return new InterfaceNavigationFactory().buildFrom(applicationClass);
	}

}
