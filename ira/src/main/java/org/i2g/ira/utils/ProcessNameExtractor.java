package org.i2g.ira.utils;

public class ProcessNameExtractor {

	public String getProcessName(String candidate) {

		// TODO fdv поправить
		String retVal = candidate.replaceAll("^org[.]i2g[.]ira[.]processlist[.]", "");
		if (candidate.equals(retVal)) {
			return null;
		}

		retVal = retVal.replaceAll("(\\w+)[.](\\w+).*$", "$1.$2");
		return retVal;
	}

}
