package org.i2g.ira;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

public class ContextClass<T> {
	
	protected ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	};

}