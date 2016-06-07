package org.i2g.ira;

import java.util.Collection;
import java.util.TreeSet;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public abstract class AbstractAction<ContextType, ProcessorType> extends ContextClass<ContextType>
		implements utils.Process<ContextType>, ApplicationContextAware {

	

	private Class<ProcessorType> processorsBaseClass;

	protected AbstractAction(Class<ProcessorType> processorsBaseClass) {
		this.processorsBaseClass = processorsBaseClass;
	}

	public void execute(ContextType t) {
		for (ProcessorType pt : getProcessors()) {
			utils.Process p = (utils.Process) pt;
			System.out.println(pt.getClass());
			p.execute(null);
		}
	}

	public Collection<ProcessorType> getProcessors() {
		Collection<ProcessorType> values = applicationContext.getBeansOfType(processorsBaseClass).values();
		TreeSet<ProcessorType> retVal = new TreeSet<ProcessorType>();
		retVal.addAll(values);
		return retVal;
	};

}
