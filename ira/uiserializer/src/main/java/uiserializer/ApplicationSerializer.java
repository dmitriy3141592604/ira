package uiserializer;

import application.Application;
import application.CashMashinePage;
import application.CashMashinePage.CashForm;
import application.FoodPage;
import application.HelpPage;
import application.HelpPage.HelpSearchForm;

public class ApplicationSerializer {

	public void process(Class<Application> applicationClass) {
		final UIBuilderFactoryBuilder uiFactory = new UIBuilderFactoryBuilder().build();

		final Application app = new InterfaceNavigationFactory().buildFrom(applicationClass);

		final ComponentBuilder cb = new BodyBuilder();

		cb.with(new VerticalBlock(), vb -> {
			vb.with(new Div(), d -> {
				d.with(new H1(), h1 -> {
					final CashMashinePage cashMashinePage = app.goToCashMashinePage();
					h1.add(getName(cashMashinePage));

					d.with(new Div(), page_ -> {
						final CashForm cashForm = cashMashinePage.getCashForm();
						page_.with(Form.newForm(formSource(cashForm)), form -> {
							form.add(cashForm.getCalculatedSumm());
							form.add(cashForm.getPayment());
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
					final HelpSearchForm helpSearchForm = helpPage.getHelpSearchForm();

					d.with(new Div(), page_ -> {
						page_.with(Form.newForm(formSource(helpSearchForm)), form -> {
							form.add(helpSearchForm.searchString());
							form.add(helpSearchForm.search());
						});
					});

				});
			});
		});

		cb.render(uiFactory.getHtml());

		System.out.println(uiFactory.getSerializedContent());

	}

	private FormSource formSource(Object cashForm) {
		return (FormSource) cashForm;
	}

	private String getName(Object object) {
		return ((Named) object).getName();
	}

}
