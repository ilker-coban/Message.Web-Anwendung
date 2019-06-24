$(document).ready(domReady);
function domReady(){
	$("input").powerTip();
		$("#textfeld").keyup(function(){
		var len = $(this).val().length;
		$("#zaehler").val(len);
		});
};
