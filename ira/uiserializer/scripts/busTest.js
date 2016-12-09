"bus.js"

test("called", function() {
	// var called = "not called";
	//
	// bus.subscribe("queue", function() {
	// called = true;
	// });
	//
	// bus.publish("queue", {});
	//
	// assertEquals(true, called);

});

test("test queue creating", function() {
	var qName = "queue2";
	try {

		bus.publish(qName, {});
	} catch (e) {
		assertEquals(qName + " not exists", e.message);
		return;
	}
	assertFailure("Expected exception not throwed by publishing in to " + qName);
});