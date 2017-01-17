package uiserializer;

import java.io.PrintWriter;

import org.i2g.ira.uibuilder.Element;
import org.i2g.ira.uibuilder.HTMLElements;

import uiserializer.components.HtmlHeadComponent;

public abstract class ApplicationSerializerBase<T> implements WithSimpleClassName {

	private static final Class<HTMLElements> INTERFACE_CLASS = HTMLElements.class;

	private final ApplicationSerializerConfiguration configuration = new ApplicationSerializerConfiguration(this);

	protected abstract void build(T app, CashMashineBodyBuilder cb);

	public void process(PrintWriter out, Class<T> applicationClass) {
		// TASK Рефакторинк ApplicationSerializerBase
		final StringBuilder sb = newStringBuilder();
		final UIBuilderBuilder uiBuilderBuilder = newUIBuilderBuilder();
		final Class<HTMLElements> interfaceClass = INTERFACE_CLASS;
		final Element element = newRootElement();
		final UIBuilder uiBuilder = uiBuilderBuilder.setInterface(interfaceClass).setElement(element).build();
		final T app = newApplication(applicationClass);
		final CashMashineBodyBuilder cb = new CashMashineBodyBuilder();

		build(app, cb);

		final HtmlHeadComponent htmlHead = newConfiguredHtmlHeadComponent();
		final HTMLElements html = uiBuilder.getHtml();
		{
			htmlHead.render(html);
			cb.render(html);
		}
		uiBuilder.serializeContent(sb);

		out.println(sb);

		configuration.getWriter().accept(f -> f.println(sb));
	}

	private HtmlHeadComponent newConfiguredHtmlHeadComponent() {
		final HtmlHeadComponent htmlHead = new HtmlHeadComponent();
		htmlHead.addStyle("../css/reset.css");
		return htmlHead;
	}

	private Element newRootElement() {
		return new Element("html");
	}

	private UIBuilderBuilder newUIBuilderBuilder() {
		return new UIBuilderBuilder();
	}

	private StringBuilder newStringBuilder() {
		return new StringBuilder();
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

	protected T newApplication(Class<T> applicationClass) {
		return new InterfaceNavigationFactory().buildFrom(applicationClass);
	}

}
