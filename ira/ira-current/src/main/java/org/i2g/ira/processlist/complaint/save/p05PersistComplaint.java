package org.i2g.ira.processlist.complaint.save;

import org.i2g.ira.FileDao;
import org.i2g.ira.ProcessRunner;
import org.springframework.stereotype.Component;

import utils.Complaint;
import utils.ComplaintSaveProcessBase;

@Component
public class p05PersistComplaint extends ComplaintSaveProcessBase {

	public void execute(Complaint t, FileDao fileDao, ProcessRunner runner) {
		System.out.println(this.toString() + "   " + fileDao.sizeByName("name"));
	}
}
