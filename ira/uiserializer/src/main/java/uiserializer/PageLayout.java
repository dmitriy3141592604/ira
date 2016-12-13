package uiserializer;

import java.util.function.Consumer;

import org.i2g.ira.uibuilder.HTMLElements;

import uiserializer.components.Cmp;

public class PageLayout extends ComponentBuilder {

	private Cmp header;

	private Cmp body;

	@Override
	public void render(HTMLElements html) {
		final HTMLElements div = html.div();
		if (header != null) {
			header.render(div);
		}
		if (body != null)
			body.render(div.div());
	}

	public Cmp getHeader() {
		return header;
	}

	public <T extends Cmp> void setHeader(T header, Consumer<T> c) {
		this.header = header;
		c.accept(header);
	}

	public Cmp getBody() {
		return body;
	}

	public <T extends Cmp> void setBody(T body, Consumer<T> c) {
		this.body = body;
		c.accept(body);
	}

}
