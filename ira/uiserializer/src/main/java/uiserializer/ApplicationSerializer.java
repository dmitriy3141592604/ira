package uiserializer;

import static uiserializer.Form.newForm;

import application.Application;
import application.CashMashinePage.CashForm;

public class ApplicationSerializer extends ApplicationSerializerBase<Application> {

	@Override
	protected void build(Application app, CashMashineBodyBuilder cb) {
		{
			final CashForm cashForm = app.goToCashMashinePage().getCashForm();
			cb.with(newForm(cashForm), form -> {
				form.add(cashForm.getCalculatedSumm());
				form.add(cashForm.submintSummCalculation());

			});
		}
	}

}
