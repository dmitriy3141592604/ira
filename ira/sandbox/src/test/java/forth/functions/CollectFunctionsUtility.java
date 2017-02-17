package forth.functions;

import static utils.io.OnFileWriter.onFileWriter;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import forth.FOperation;

public class CollectFunctionsUtility {

	public static void main(String... args) throws Exception {
		final String currentPackageName = CollectFunctionsUtility.class.getPackage().getName();
		final List<String> linkedList = new LinkedList<>();
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(currentPackageName)) {
			final Map<String, FOperation> beansOfType = context.getBeansOfType(FOperation.class);
			for (final FOperation operation : beansOfType.values()) {
				final String name = operation.getClass().getName();
				// System.out.println(name);
				linkedList.add(name);
			}
		}
		onFileWriter("out", pw -> linkedList.forEach(str -> pw.println(str)));
	}
}
