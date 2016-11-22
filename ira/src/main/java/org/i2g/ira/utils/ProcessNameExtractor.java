package org.i2g.ira.utils;

import org.springframework.util.Assert;

/**
 * Вспомогательный класс для извлечения полного имени процесса.
 *
 * Имя процесса это пара - основное имя процесса и действие
 *
 * TODO Добавить понятия полное имя процесса и основное имя процесса в тезаурус
 *
 */
public class ProcessNameExtractor {

	private final String[] prefixes;

	public ProcessNameExtractor(String prefix) {
		Assert.hasText(prefix);
		if (prefix.endsWith(".")) {
			throw new IllegalArgumentException();
		}
		this.prefixes = prefix.split("[.]");
	}

	public String getProcessName(String candidate) {
		final String[] candidateChunks = candidate.split("[.]");
		final int i = firstNotEqualIndex(candidateChunks);
		final int groupPartItems$groupName$actionItem = 2;
		final int processSimpleName = 1;
		final int restOfNotMatchedChunks = candidateChunks.length - i;
		if (restOfNotMatchedChunks > groupPartItems$groupName$actionItem + processSimpleName) {
			throw new IllegalArgumentException();
		}
		return candidateChunks[i] + "." + candidateChunks[i + processSimpleName];
	}

	private int firstNotEqualIndex(final String[] candidateChunks) {
		int i = 0;
		for (; i < prefixes.length && i < candidateChunks.length && prefixes[i].equals(candidateChunks[i]); ++i) {
			// Ничего не делаем, пропускаем равные значения
		}
		return i;
	}

	public static ProcessNameExtractor newInstance(String prefix) {
		return new ProcessNameExtractor(prefix);
	}

}
