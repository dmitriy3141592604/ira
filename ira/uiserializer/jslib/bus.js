var bus = (function(){
	var bus = {};
	return {
		subscribe: function(theme, listener) {
			if(bus[theme] == null) {
				bus[theme] = [] ;
			}
			bus[theme].push(listener);
		},
		publish: function(theme, event) {
			if(bus[theme] == null) {
				bus[theme] = [] ;
			}
			var theme = bus[theme];
			for( var i = 0 ; i < theme.length ; ++i) {
				theme[i](event);
			}
		}
	};
})();
