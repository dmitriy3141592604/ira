package uiserializer;

import static uiserializer.Form.newForm;

import application.Application;
import application.CashMashinePage;
import application.CashMashinePage.CashForm;
import application.FoodPage;
import application.HelpPage;
import application.HelpPage.HelpSearchForm;

public class ApplicationSerializer extends ApplicationSerializerBase<Application> {

	@Override
	protected void build(Application app, ComponentBuilder cb) {

		cb.with(newPagesList(), pages -> {

			pages.with(newPageLayout(), pageLayout -> {
				final CashMashinePage cashMashinePage = app.goToCashMashinePage();

				pageLayout.setHeader(newHeader(), h -> {
					h.set(getName(cashMashinePage));
				});

				final CashForm cashForm = cashMashinePage.getCashForm();
				pageLayout.setBody(newForm(cashForm), form -> {
					form.add(cashForm.getCalculatedSumm());
					form.add(cashForm.submintSummCalculation());
				});
			});

			pages.with(newPageLayout(), pageLayout -> {
				final FoodPage foodPage = app.goToFoodPage();
				pageLayout.setHeader(newHeader(), h -> {
					h.set(getName(foodPage));
				});
				pageLayout.setBody(newEmpty(), e -> {
				});
			});

			pages.with(newPageLayout(), pageLayout -> {
				final HelpPage helpPage = app.goToHelpPage();
				final HelpSearchForm searchForm = helpPage.getHelpSearchForm();
				pageLayout.setHeader(newHeader(), h -> {
					h.set(getName(helpPage));
				});
				pageLayout.setBody(newForm(searchForm), form -> {
					form.add(searchForm.searchString());
					form.add(searchForm.search());
				});
			});
		});
	}

}
