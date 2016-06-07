package org.i2g.ira;

import java.util.Collection;
import java.util.TreeSet;

import org.i2g.ira.utils.ProcessNameExtractor;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ProcessRunner<ContextType> extends ContextClass<ContextType> implements ApplicationContextAware {

	public void execute(ContextType t, String processName) {

		for (Object pt : getProcessors(processName)) {

			@SuppressWarnings("unchecked")
			utils.Process<ContextType> p = (utils.Process<ContextType>) pt;
			System.out.println(pt.getClass());
			p.execute(null);
		}
	}

	public Collection<Object> getProcessors(String processName) {
		Collection<Object> values = applicationContext.getBeansOfType(Object.class).values();
		TreeSet<Object> retVal = new TreeSet<Object>();
		for (Object p : values) {
			String targetProcess = new ProcessNameExtractor().getProcessName(p.toString());
			if (targetProcess != null) {
				retVal.add(p);
			}
		}

		return retVal;
	};

}
