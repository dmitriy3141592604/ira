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

	public void execute(String processName) {
		try {
			for (final Object pt : getProcessors(processName)) {

				final Method execcute = findExecutor(pt);

				final LinkedList<Object> arguments = fillArguments(execcute);

				execcute.invoke(pt, arguments.toArray());

			}
		} catch (final ReflectiveOperationException e) {
			throw new RuntimeException(e);
		}
	}

	private LinkedList<Object> fillArguments(Method execcute) {
		final Class<?>[] parameterTypes = execcute.getParameterTypes();

		final LinkedList<Object> arguments = new LinkedList<Object>();
		final Object ctx = IraProcessorsContextHolder.getValue();
		for (final Class<?> type : parameterTypes) {
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
		final String methodName = "execute";
		for (final Method method : pt.getClass().getMethods()) {
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
		final Collection<Object> values = applicationContext.getBeansOfType(Object.class).values();
		final TreeSet<Object> retVal = new TreeSet<Object>();
		for (final Object p : values) {
			final String targetProcess = new ProcessNameExtractor().getProcessName(p.toString());
			if (targetProcess != null) {
				retVal.add(p);
			}
		}

		return retVal;
	};

}
