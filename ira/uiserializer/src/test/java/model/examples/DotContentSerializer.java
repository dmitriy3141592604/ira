package model.examples;

import static utils.Quietly.quietly;
import static utils.io.OnFileWriter.dumpToFile;

import utils.Value;

public class DotContentSerializer {

	public static int mainSerializeDotContent(String dotFileName, String dotContent) {
		final Value<Integer> retVal = Value.newValue();
		quietly(() -> {
			{

				dumpToFile(dotFileName, dotContent + ".dot");
			}

			final String htmlFileName = dotFileName + ".html";
			final String imageFileName = dotFileName + ".png";
			{
				dumpToFile(htmlFileName, "<img src='" + imageFileName + "'>");
			}

			{
				final String dotUtilPath = "c:\\wks\\prg\\Graphviz\\bin\\dot.exe";
				final StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(dotUtilPath);
				stringBuilder.append(" -T");
				stringBuilder.append("png");
				stringBuilder.append(" -o");
				stringBuilder.append(imageFileName);
				stringBuilder.append("  ");
				stringBuilder.append(dotFileName);
				final Process exec = Runtime.getRuntime().exec(stringBuilder.toString());
				final int waitFor = exec.waitFor();
				retVal.setValue(waitFor);
			}
		});
		return retVal.getValue();
	}
}