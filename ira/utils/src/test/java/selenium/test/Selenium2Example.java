package selenium.test;

/**
 * http://selenium2.ru/docs/webdriver.html#htmlunit-driver
 * @author fdv.741
 *
 */
public class Selenium2Example {
	/**<code>
	
	public static void main(String[] args) {
		// Notice that the remainder of the code relies on the interface,
		// not the implementation.
		final ChromeOptions options = new ChromeOptions();
		//options.setBinary("c:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
		System.setProperty("webdriver.chrome.driver", "c:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
		final WebDriver driver = new ChromeDriver(options);
	
		// And now use this to visit Google
		driver.get("http://www.google.com");
		// Alternatively the same thing can be done like this
		// driver.navigate().to("http://www.google.com");
	
		// Find the text input element by its name
		final WebElement element = driver.findElement(By.name("q"));
	
		// Enter something to search for
		element.sendKeys("Cheese!");
	
		// Now submit the form. WebDriver will find the form for us from the element
		element.submit();
	
		// Check the title of the page
		System.out.println("Page title is: " + driver.getTitle());
	
		// Google's search is rendered dynamically with JavaScript.
		// Wait for the page to load, timeout after 10 seconds
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver d) {
				return d.getTitle().toLowerCase().startsWith("cheese!");
			}
		});
	
		// Should see: "cheese! - Google Search"
		System.out.println("Page title is: " + driver.getTitle());
	
		//Close the browser
		driver.quit();
	}
	</code>
	**/
}