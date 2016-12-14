package uiserializer;

import java.io.File;
import java.io.PrintWriter;

import application.Application;
import utils.io.OnFileWriter;

public abstract class ApplicationSerializerBase {

	protected Empty newEmpty() {
		return new Empty();
	}

	protected Header newHeader() {
		return new Header();
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

	protected UIBuilderFactoryBuilder newUIBuilderFactory() {
		return new UIBuilderFactoryBuilder().build();
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
		final UIBuilderFactoryBuilder uiFactory = newUIBuilderFactory();
		final Application app = newApplication(applicationClass);
		final ComponentBuilder cb = newBodyBuilder();

		build(app, cb);

		cb.render(uiFactory.getHtml());

		final String sc = uiFactory.serializeContentToInternalBuffer();
		out.println(sc);
		System.out.println(sc);

		new OnFileWriter(new File(this.getClass().getSimpleName() + ".html")).accept(f -> f.println(sc));

	}

}
