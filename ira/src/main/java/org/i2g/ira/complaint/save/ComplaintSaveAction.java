package org.i2g.ira.complaint.save;

import org.i2g.ira.AbstractAction;
import org.springframework.stereotype.Component;

import utils.Complaint;
import utils.ComplaintSaveProcessBase;

@Component
public class ComplaintSaveAction extends AbstractAction<Complaint, ComplaintSaveProcessBase> {

	protected ComplaintSaveAction() {
		super(ComplaintSaveProcessBase.class);
	}

	public int compareTo(Object o) {
		// TODO fdv удалить метод
		throw new RuntimeException("not supported");
	}

}
