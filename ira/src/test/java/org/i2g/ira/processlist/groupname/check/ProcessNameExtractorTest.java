package org.i2g.ira.processlist.groupname.check;

import static org.junit.Assert.assertEquals;

import org.i2g.ira.processlist.complaint.save.p01CorrectCheckingDate;
import org.i2g.ira.utils.ProcessNameExtractor;
import org.junit.Test;

public class ProcessNameExtractorTest {

	@Test
	public void test() {
		ProcessNameExtractor extractor = new ProcessNameExtractor();
		assertEquals("groupname.check", extractor.getProcessName(new DummyProcess().toString()));
	}

	@Test
	public void test$2() {
		ProcessNameExtractor extractor = new ProcessNameExtractor();
		assertEquals("complaint.save", extractor.getProcessName(new p01CorrectCheckingDate().toString()));
	}

}
