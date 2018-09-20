var host="";

$(document).ready(function(){
	Init();
});


function Init(){
	$.ajax({
		type:"get",
		url:host+"/deviceSetting/findAll",
		async:true,
		dataType: "json",
		success:function(data){
			$.each(data.data, function(index,element) {
				
				//alert(element.domain);
				$("#domain").val(element.domain);
				$("#port").val(element.port);
				$("#ip").val(element.ip);
			});
		}
		
	});
}
