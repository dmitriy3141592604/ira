"bus.js"
var called = "not called" ;
bus.subscribe("queue", function() {
	called = true ;
});

bus.publish("queue", {});

called ? "Ok" : "not called";
