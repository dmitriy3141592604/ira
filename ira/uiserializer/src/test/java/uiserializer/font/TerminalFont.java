package uiserializer.font;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class TerminalFont {
	private final TreeMap<String, List<String>> symbols = new TreeMap<String, List<String>>();

	public TerminalFont(String fontResource) {
		try {
			final InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fontResource);
			try (InputStreamReader reader = new InputStreamReader(inputStream, "utf-8");) {
				try (BufferedReader buffer = new BufferedReader(reader);) {
					String key = null;
					for (String line = buffer.readLine(); line != null; line = buffer.readLine()) {
						if (line.length() == 1) {
							key = line;
							final List<String> previousValue = symbols.put(key, new ArrayList<String>());
							if (previousValue != null) {
								throw new IllegalArgumentException("key: " + key + " dublicated in resource: " + fontResource);
							}

						} else {
							symbols.get(key).add(line);
						}
					}
				}
			}

		} catch (final IOException e) {
			throw new RuntimeException(e);
		}

	}

	public List<String> get(char ch) {
		return symbols.get(String.valueOf(ch));
	}

}
