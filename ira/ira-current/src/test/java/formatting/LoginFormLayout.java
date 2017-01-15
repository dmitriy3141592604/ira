package formatting;

class Model {
	Object login;
	Object password;
	Object loginLabel;
	Object passwordLabel;
	Object submit;

	Object name;
	Object nameLabel;
	Object family;
	Object familyLabel;
	Object zipCode;
	Object zipCodeLabel;
	Object inn;
	Object innLabel;
	Object kpp;
	Object kppLabel;
	Object legalAddress;
	Object legalAddressLabel;
	Object city;
	Object cityLabel;
	Object verified;
	Object verifiedLabel;
}

public class LoginFormLayout extends Model implements RuleSupport, fRuleSupport {

	public void sample$loginForm$v1() {
		$(__(loginLabel), ______(login));
		$(__(passwordLabel), ___(password));
		$(________________, __(submit));
	}

	public void sample$loginForm$v1$1() {
		$(__(loginLabel), ______(login), ______);
		$(__(passwordLabel), ___(password), ___);
		$(_______________, ___(submit), _____);
	}

	// May be this 2?
	public void sample$loginForm$v2() {
		$(_____(loginLabel), ___(login));
		$(__(passwordLabel), ___(password));
		$(_______________, ___(submit));
	}

	// May be this?
	public void sample$loginForm$v2_1() {
		$(_____(nameLabel), ___(name));
		$(__(familyLabel), ___(family));
		$(_______________, ___(submit));
	}

	public void sample$loginForm$v3() {
		$$(loginLabel).$$$$$$(login);
		$$(passwordLabel).$$$(password);
		$$_________________$$(submit);
	}

	public void sample$loginForm$v3_() {
		$$(loginLabel).$$$$$$(login);
		$$(passwordLabel).$$$(password);
		$$$$$$$$$$$$$$$$$$$$$(submit);
	}

	public void sample$loginForm$v4() {
		$$(loginLabel).____$$(login);
		$$(passwordLabel)._$$(password);
		$$_________________$$(submit);
	}

	public void sample$loginForm$f_1() {
		$$(f(loginLabel).ffff(login));
		$$(f(passwordLabel).f(password));
		$$(ffffffffffffffffff(submit));

	}

	public void sample$loginForm$f_2() {
		$$(ffff(loginLabel).f(login));
		$$(f(passwordLabel).f(password));
		$$(ffffffffffffffffff(submit));
	}

}

interface fRuleSupport {
	fRuleSupport f = null;
	fRuleSupport ff = null;
	fRuleSupport fff = null;

	default fRuleSupport f(Object o) {
		return null;
	}

	default fRuleSupport ff(Object o) {
		return null;
	}

	default fRuleSupport fff(Object o) {
		return null;
	}

	default fRuleSupport ffff(Object o) {
		return null;
	}

	default fRuleSupport fffff(Object o) {
		return null;
	}

	default fRuleSupport ffffff(Object o) {
		return null;
	}

	default fRuleSupport fffffff(Object o) {
		return null;
	}

	default fRuleSupport ffffffff(Object o) {
		return null;
	}

	default fRuleSupport fffffffff(Object o) {
		return null;
	}

	default fRuleSupport ffffffffff(Object o) {
		return null;
	}

	default fRuleSupport fffffffffff(Object o) {
		return null;
	}

	default fRuleSupport ffffffffffffffffff(Object o) {
		return null;
	}

}

interface RuleSupport {

	default RuleSupport ____$$(Object o) {
		return null;
	}

	default RuleSupport _$$(Object o) {
		return null;
	}

	default RuleSupport $$(Object o) {
		return null;
	}

	default RuleSupport $$$$$$$$$$$$$$$$$$$$$$$$$$$(Object o) {
		return null;
	}

	default RuleSupport $$_________________$$(Object o) {
		return null;
	}

	default RuleSupport $$$(Object o) {
		return null;
	}

	default RuleSupport $$$$$$(Object o) {
		return null;
	}

	default RuleSupport $$$$$$$$$$$$$$$$$$$$$(Object o) {
		return null;
	}

	public static Object __ = new Object();
	public static Object ___ = new Object();
	public static Object ____ = new Object();
	public static Object _____ = new Object();
	public static Object ______ = new Object();
	public static Object ___________________ = new Object();
	public static Object _______________ = new Object();
	public static Object ________________ = new Object();
	public static Object _________________ = new Object();
	public static Object __________________ = new Object();

	default Object __(Object... o) {
		return null;
	}

	default Object ____(Object... o) {
		return null;
	}

	default Object _____(Object... o) {
		return null;
	}

	default Object $(Object... o) {
		return null;
	}

	default Object ___(Object... o) {
		return null;
	}

	default Object ______(Object... o) {
		return null;
	}

	default Object _______(Object... o) {
		return null;
	}

	default Object $_(Object... o) {
		return null;
	}

	default Object $__(Object... o) {
		return null;
	}

	default Object $___(Object... o) {
		return null;
	}

	default Object $____(Object... o) {
		return null;
	}

	default Object $_____(Object... o) {
		return null;
	}

	default Object $______(Object... o) {
		return null;
	}

}
