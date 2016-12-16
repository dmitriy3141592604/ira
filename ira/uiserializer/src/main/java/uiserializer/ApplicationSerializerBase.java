package uiserializer;

import java.io.File;
import java.io.PrintWriter;

import org.i2g.ira.uibuilder.Element;
import org.i2g.ira.uibuilder.HTMLElements;

import utils.io.OnFileWriter;

public abstract class ApplicationSerializerBase<T> {

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

	protected UIBuilder newUIBuilderFactory() {
		return new UIBuilderBuilder().setInterface(HTMLElements.class).setElement(new Element("html")).build();
	}

	protected T newApplication(Class<T> applicationClass) {
		return new InterfaceNavigationFactory().buildFrom(applicationClass);
	}

	protected FormSource formSource(Object cashForm) {
		return (FormSource) cashForm;
	}

	protected String getName(Object object) {
		return ((Named) object).getName();
	}

	protected abstract void build(T app, ComponentBuilder cb);

	public void process(PrintWriter out, Class<T> applicationClass) {
		final StringBuilder stringBuilder = new StringBuilder();

		final UIBuilder uiFactory = newUIBuilderFactory();
		final T app = newApplication(applicationClass);
		final ComponentBuilder cb = newBodyBuilder();

		build(app, cb);

		cb.render(uiFactory.getHtml());
		uiFactory.serializeContent(stringBuilder);

		final String sc = stringBuilder.toString();
		out.println(sc);

		new OnFileWriter(new File(getClass().getSimpleName() + ".html")).accept(f -> f.println(sc));

	}

}
