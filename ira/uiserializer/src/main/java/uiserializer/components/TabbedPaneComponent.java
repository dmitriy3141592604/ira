package uiserializer.components;

import org.i2g.ira.uibuilder.Attributes;
import org.i2g.ira.uibuilder.HTMLElements;

import utils.Responsibility;

@Responsibility("Создает UI элемент с заголовками, которые переключают содержимое основной панели")
public class TabbedPaneComponent implements Attributes, Component {

	@Override
	public void render(HTMLElements html) {
		final HTMLElements header = html.ul();
		header.li(klass("tab-headers"));
		header.div(klass("tab-panes"));
	}

}
