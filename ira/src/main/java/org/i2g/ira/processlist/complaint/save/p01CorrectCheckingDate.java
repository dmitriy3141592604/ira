package org.i2g.ira.processlist.complaint.save;

import org.springframework.stereotype.Component;

import utils.Complaint;
import utils.ComplaintSaveProcessBase;

@Component
public class p01CorrectCheckingDate extends ComplaintSaveProcessBase {

	public void execute(Complaint t) {
		System.out.println(this.toString());
	}

}
