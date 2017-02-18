package uiserializer.components.write.bootstrap.buttons;

import org.i2g.ira.uibuilder.Attributes;
import org.i2g.ira.uibuilder.HTMLElements;

import uiserializer.components.Component;
import uiserializer.components.HtmlHeadComponent;

public class BootstrapButtonComponent implements Component, Attributes {

	private final HtmlHeadComponent headComponent = new HtmlHeadComponent() {

		{
			addStyle("../css/bootstrap.min.css");
		}
	};

	@Override
	public void render(HTMLElements html) {
		headComponent.render(html);
		final HTMLElements ex = html.body().div();

		ex.a(href("#"), klass("btn btn-default"), role("button")).text("Link");
		ex.button(klass("btn btn-default"), type("submit")).text("Button");
		ex.input(klass("btn btn-default"), type("button"), value("Input"));
		ex.input(klass("btn btn-default"), type("submit"), value("Submit"));
	}

}