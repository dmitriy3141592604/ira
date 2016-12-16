package uiserializer;

import java.io.File;
import java.io.PrintWriter;

import org.i2g.ira.uibuilder.Element;
import org.i2g.ira.uibuilder.HTMLElements;

import application.Application;
import utils.io.OnFileWriter;

public abstract class ApplicationSerializerBase {

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

	protected UIBuilder newUIBuilderFactory(StringBuilder stringBuilder) {
		Element element = new Element("html");
		UIBuilderBuilder r = new UIBuilderBuilder();
		r.setInterface(HTMLElements.class);
		r.setElement(element);
		r.setElement(element);
		return r.build();
	}

	protected Application newApplication(Class<Application> applicationClass) {
		return new InterfaceNavigationFactory().buildFrom(applicationClass);
	}

	protected FormSource formSource(Object cashForm) {
		return (FormSource) cashForm;
	}

	protected String getName(Object object) {
		return ((Named) object).getName();
	}

	protected abstract void build(final Application app, final ComponentBuilder cb);

	public void process(PrintWriter out, Class<Application> applicationClass) {
		final StringBuilder stringBuilder = new StringBuilder();
		final UIBuilder uiFactory = newUIBuilderFactory(stringBuilder);
		final Application app = newApplication(applicationClass);
		final ComponentBuilder cb = newBodyBuilder();

		build(app, cb);

		cb.render(uiFactory.getHtml());
		uiFactory.serializeContent(stringBuilder);

		final String sc = stringBuilder.toString();
		out.println(sc);
		System.out.println(sc);

		new OnFileWriter(new File(this.getClass().getSimpleName() + ".html")).accept(f -> f.println(sc));

	}

}
