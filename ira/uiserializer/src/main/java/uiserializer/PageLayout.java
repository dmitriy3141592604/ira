package uiserializer;

import java.util.function.Consumer;

import org.i2g.ira.uibuilder.HTMLElements;

import uiserializer.components.Component;

public class PageLayout extends ComponentBuilderBase {

	private Component header;

	private Component body;

	@Override
	public void render(HTMLElements html) {
		final HTMLElements div = html.div();
		if (header != null) {
			header.render(div);
		}
		if (body != null)
			body.render(div.div());
	}

	public Component getHeader() {
		return header;
	}

	public <T extends Component> void setHeader(T header, Consumer<T> c) {
		this.header = header;
		c.accept(header);
	}

	public Component getBody() {
		return body;
	}

	public <T extends Component> void setBody(T body, Consumer<T> c) {
		this.body = body;
		c.accept(body);
	}

}
