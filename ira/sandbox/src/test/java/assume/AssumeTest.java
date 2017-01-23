package assume;

import static org.junit.Assume.assumeThat;

import java.io.File;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class AssumeTest {

	@Test
	public void filenameIncludesUsername() {
		assumeThat(File.separatorChar, CoreMatchers.is('\\'));
	}

}
