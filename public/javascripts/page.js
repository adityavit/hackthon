$(function(){
	
	$(".tabUnselected").click(
	 function(){
	 	$(this).parent().children().removeClass("tabDivSelected").addClass("tabDivUnSelected");
		var id = $(this).attr('id').split("tab")[1];
		alert(id);
		$("#tabDiv"+id).addClass("tabDivSelected").removeClass("tabUnDivSelected");
	 }
	)
	
	
});

