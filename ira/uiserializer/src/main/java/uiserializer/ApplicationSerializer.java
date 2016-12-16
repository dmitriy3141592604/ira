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
	protected void build(final Application app, final ComponentBuilder cb) {

		cb.with(newPagesList(), pages -> {

			pages.with(newPageLayout(), pageLayout -> {
				final CashMashinePage cashMashinePage = app.goToCashMashinePage();
				final CashForm cashForm = cashMashinePage.getCashForm();
				pageLayout.setHeader(newHeader(), h -> {
					h.set(getName(cashMashinePage));
				});
				pageLayout.setBody(newForm(formSource(cashForm)), form -> {
					form.add(cashForm.getCalculatedSumm());
					form.add(cashForm.getPayment());
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
				pageLayout.setBody(newForm((formSource(searchForm))), form -> {
					form.add(searchForm.searchString());
					form.add(searchForm.search());
				});
			});
		});
	}

}
