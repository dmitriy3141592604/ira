/**
 * 
 */
var errors = [];

var failures = [];

var success = [];

var test = function(testName, testMethod) {
	try {
		testMethod();
		success.push(testName);
	} catch (e) {
		errors.push(testName + ":" + e);
	}
};

var assertEquals = function(left, right) {
	if (left == right) {
		return;
	}
	throw ("Not equals: " + left + " != " + right);
};

var assertFailure = function(message) {
	throw message ;
}