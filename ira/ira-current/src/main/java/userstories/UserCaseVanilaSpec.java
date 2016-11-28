package userstories;

@SpecDescription("Базовый сценарий авторизации пользователя")
public class UserCaseVanilaSpec extends UserLoginSpec {

	public void execute() {
		openLoginPage();
		setLogin("alisa");
		setPassword("passwd");

		submitLoginForm();

		checkCurrentLoginedUser("alisa");
	}
}
