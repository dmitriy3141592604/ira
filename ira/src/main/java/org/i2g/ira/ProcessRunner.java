package org.i2g.ira;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedList;
import java.util.TreeSet;

import org.i2g.ira.utils.ProcessNameExtractor;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import utils.IraProcessorsContextHolder;

@Component
public class ProcessRunner extends ContextClass implements ApplicationContextAware {

	// Добавить тип в IraProcessorsContextHolder
	public void execute(String processName) {
		try {
			for (Object pt : getProcessors(processName)) {

				Method execcute = findExecutor(pt);

				LinkedList<Object> arguments = fillArguments(execcute);

				execcute.invoke(pt, arguments.toArray());

			}
		} catch (ReflectiveOperationException e) {
			throw new RuntimeException(e);
		}
	}

	private LinkedList<Object> fillArguments(Method execcute) {
		Class<?>[] parameterTypes = execcute.getParameterTypes();

		LinkedList<Object> arguments = new LinkedList<Object>();
		Object ctx = IraProcessorsContextHolder.getValue();
		for (Class<?> type : parameterTypes) {
			if (type.equals(ctx.getClass())) {
				arguments.add(ctx);
			} else {
				arguments.add(applicationContext.getBean(type));
			}
		}
		return arguments;
	}

	private Method findExecutor(Object pt) {
		Method execute = null;
		String methodName = "execute";
		for (Method method : pt.getClass().getMethods()) {
			if (methodName.equals(method.getName())) {
				if (execute != null) {
					throw new IllegalArgumentException("Many " + methodName + " methods");
				}
				execute = method;
				break;
			}
		}
		if (execute == null) {
			throw new IllegalArgumentException(methodName + " not found");
		}
		return execute;
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
