package uiserializer;

import application.Application;
import application.CashMashinePage;
import application.FoodPage;
import application.HelpPage;

public class ApplicationSerializer {

	public void process(Class<Application> applicationClass) {
		final UIBuilderFactoryBuilder uiFactory = new UIBuilderFactoryBuilder().build();

		final Application app = new InterfaceNavigationFactory().buildFrom(applicationClass);

		final ComponentBuilder cb = new BodyBuilder();

		cb.with(new VerticalBlock(), vb -> {
			vb.with(new Div(), d -> {
				d.with(new H1(), h1 -> {
					final CashMashinePage cashMashinePage = app.jumpToCashMashinePage();
					h1.add(((Named) cashMashinePage).getName());

					d.with(new Div(), page -> {
						page.with(new Form(), form -> {
							form.add(cashMashinePage.getCalculatedSumm());
						});
					});
				});

				d.with(new H1(), h1 -> {
					final FoodPage foodPage = app.jumpToFoodPage();
					h1.add(((Named) foodPage).getName());
				});

				d.with(new H1(), h1 -> {
					final HelpPage helpPage = app.jumpHelpPage();
					h1.add(((Named) helpPage).getName());

				});
			});
		});

		cb.render(uiFactory.getHtml());

		System.out.println(uiFactory.getSerializedContent());

	}

}
