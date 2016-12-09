var bus = (function() {

	var bus = {};

	var forTheme = function(theme) {
		return bus[theme] ? bus[theme] : bus[theme] = [];
	}

	var busImpl = {
		subscribe : function(theme, listener) {
			forTheme(theme).push(listener);
		},
		publish : function(theme, event) {
			if (!bus[theme]) {
				throw {
					message : theme + " not exists"
				};
			}
			var theme = forTheme(theme);
			for (var i = 0; i < theme.length; ++i) {
				theme[i](event);
			}
		}
	};

	return busImpl;
})();
