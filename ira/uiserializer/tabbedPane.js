
(function(){
	bus.subscribe('tab.pane.selected', function(event){
		console.log("listener invoced: ", event);
	});
})();

$(document).ready( function(){

	$(".tabbed-pane").each(function(tabbedPaneIndex) {
		var $tabbedPane = $(this);
		var topicId = "tabbedPane[" + tabbedPaneIndex + "].selected";
		$tabbedPane.
			find("> ul > li").
			each(function (tabIndex) {
				var $tab = $(this);
				var tabId = $tab.attr("id");
				console.log("tab with id: " + tabId + " found. with index: " + tabIndex);
				$tab.click(function(){
					bus.publish(
						topicId					,	{ 
							target: tabId
						,	tabIndex : tabIndex
						}
					);
				});
			});

		$tabbedPane.
			find(">div").
			each(function(paneIndex){
				var $pane = $(this);
				var paneId = $pane.attr("id");
				console.log("Pane for tabbedPane found with id: " + paneId + " with index: " + paneIndex);
				bus.subscribe(
					topicId
				,	function(event){ 
						console.log(paneId + " clicked")
						if(event.tabIndex == paneIndex) {
							console.log("This is for me showing");
							$pane.css("display", "block");
						} else {
							console.log("This is for me hidding");
							$pane.css("display", "none");
						}
					}
				);
			});

	});
});
