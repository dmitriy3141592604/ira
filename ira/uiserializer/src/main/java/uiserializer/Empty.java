package uiserializer;

import org.i2g.ira.uibuilder.HTMLElements;

import utils.Responsibility;

@Responsibility("Позволяет создавать заглушки для нереализованного кода")
public class Empty extends ComponentBuilderBase {

	@Override
	public void render(HTMLElements html) {
	}

}
