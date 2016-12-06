
(function(){
	bus.subscribe('tab.pane.selected', function(event){
		console.log("listener invoced: ", event["target"]);
	});
})();

$(document).ready( function(){
	$(".tabbed-pane > ul > li").click(
		function(){
			console.log($(this).attr("id"));
			bus.publish('tab.pane.selected', {target: $(this).attr("id")}) ;
		});
	$(".tabbed-pane > div").each(function(div){
		bus.subscribe("tab.pane.selected", function(event) {
			// console.log($(div) , "subscribed");
			console.log($(div));
			if($(div).attr("id") == event["target"] + "-tab") {
				$(div).css("display","block");
			}else{
				$(div).css("display","none");
			}
		})});
	
});
