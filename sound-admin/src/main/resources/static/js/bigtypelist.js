/**
 * Created by Administrator on 2018/4/21.
 */
 var host = "";
function checkAll(c) {
	var status = c.checked;
	var oItems = $("input[name='item']");
	for(var i = 0; i < oItems.length; i++) {
		oItems[i].checked = status;
	}
}
/*添加提交ajax请求*/
$.validator.setDefaults({
	submitHandler: function() {
		$("#addBigType").click(function() {
			var name = $("#name").val();
			$.ajax({
				"url": host+"/bigType/save",
				"type": "post",
				"data": {
					"name": name
				},
				"dataType": "json",
				"success": function(result) {},
			});
			window.location.reload();
		});
	}
});
//delAll
function delAll() {
        var listTable = $('#listTable');
        var items = $("input[name='item']");
        $.confirm({
            title: '警告',
            content: '您是否要删除此条数据?',
            type: 'red',
            typeAnimated: true,
            buttons: {
                tryAgain: {
                    text: '确定',
                    btnClass: 'btn-red',
                    action: function() {
                        for(var j = 0; j < items.length; j++) {
                            if(items[j].checked) {
                                var id = items[j].value;
                                $.ajax({
                                    "url": host+"/bigType/deleteById?id=" + id,
                                    "type": "get",
                                    "data": id,
                                    "dataType": "json",
                                    "success": function(result) {},
                                });
                                items[j].parentNode.parentNode.remove();
                            }
                        }
                    }
                },
                close: function() {
                    text:'取消'
                }
            }
    });
}


$(document).ready(function() {
	

	// 在键盘按下并释放及提交后验证提交表单
	$("#defaultForm").validate({
		rules: {
			firstname: "required",
			typeName: {
				required: true,
				minlength: 2
			},
			agree: "required"
		},
		messages: {
			firstname: "请输入名称",
			typeName: {
				required: "请输入名称",
			},
		}
	});
	/*
	 页面初始化
	 */
	var itemHTML;
	$.ajax({
		url: host+"/bigType/findByPage",
		type: "get",
		data: {
			size: 5, //我把行数传回给服务器中
			page: 0 //显示第一页的数据
		},
		dataType: "json",
		success: function(result) {
			console.log(result);
			sumCount = result.data.totalElements;
			count = result.data.pageable.pageSize;
			$.each(result.data.content, function(index, element) {
				var unixTimestamp = new Date(element.createTime);
				commonTime = unixTimestamp.toLocaleString();
				itemHTML += "<tr>" +
					"<td>" +
					"<input type='checkbox' name='item' value=" + element.id + ">" +
					"</td>" +
					"<td>" +
					element.name +
					"</td>" +
					"<td>" +
					"<input type='hidden'  class='hid' value=" + element.id + ">" +
					"<input type='button' name='delete' value='删除' class='btn btn-danger'onclick=\"deleteById('" + element.id + "')\"/>" +
					"<input type='hidden'  class='hid' value=" + element.name + ">" +
					"<input type='button' name='change111111' value='修改' class='btn btn-info' data-toggle='modal' data-target='#mybigtypechange' onclick=\"getSeatingCarMark('" + element.id + "')\"/>&nbsp;" +
					"</td>" +
					"</<tr>";

			});
			$("#listTable").html(itemHTML);
			itemHTML = "";
			var pageCount = Math.ceil(sumCount / count); //这里50除以10等于5，所以一共有5页
			$('.M-box').pagination({
				pageCount: pageCount,
				jump: true,
				coping: true,
				homePage: '首页',
				endPage: '末页',
				prevContent: '上页',
				nextContent: '下页',
				callback: function(api) {
					console.log(result);
					$.ajax({
						url: host+"/bigType/findByPage",
						type: "get",
						data: {
							size: 5, //依然显示10条数据
							page: api.getCurrent() - 1 // 非常关键的一步，这里就要用到api接口的方法中获取当前页
						},
						dataType: "json",
						success: function(result) {
							console.log(result);
							var itemHTML1;
							$.each(result.data.content, function(index, element) {
								var unixTimestamp = new Date(element.createTime);
								commonTime = unixTimestamp.toLocaleString();
								itemHTML1 += "<tr>" +
									"<td>" +
									"<input type='checkbox' name='item' value=" + element.id + ">" +
									"</td>" +
									"<td>" +
									element.name +
									"</td>" +
									"<td>" +
									"<input type='hidden'  class='hid' value=" + element.id + ">" +
									"<input type='button' name='delete' value='删除' class='btn btn-danger'onclick=\"deleteById('" + element.id + "')\"/>" +
									"<input type='hidden'  class='hid' value=" + element.name + ">" +
									"<input type='button' name='change111111' value='修改' class='btn btn-info' data-toggle='modal' data-target='#mybigtypechange'onclick=\"getSeatingCarMark('" + element.id + "')\"/>&nbsp;" +
									"</td>" +
									"</<tr>";

							});
							$("#listTable").html(itemHTML1);
						}

					});
				}

			});
		}
	});

	//	$("input[name='recommended']").click(function() {
	//
	//		var id = $(this).prev().val();
	//		if(id == 1) {
	//			$(this).val("未推荐");
	//			$(this).removeClass("btn btn-info").addClass("btn btn-danger");
	//			$(this).prev().val("2");
	//		} else {
	//			$(this).val("已推荐");
	//			$(this).removeClass("btn btn-danger").addClass("btn btn-info");
	//			$(this).prev().val("1");
	//		}
	//		$.ajax({
	//			"url": "./json/data.json",
	//			"type": "put",
	//			"data": id,
	//			"dataType": "json",
	//			"success": function(result) {
	//				DisplayListItems(result);
	//			},
	//			"error": function() {
	//				alert("出现错误！");
	//			}
	//		});
	//
	//		function DisplayListItems(list) {}
	//	});

	//	$("#allrecommended").click(function() {
	//		var list = $("input[name='item']");
	//		for(var i = 0; i < list.length; i++) {
	//			if(list[i].checked == true) {}
	//		}
	//	});
	$("#change").click(function() {
		var name = $("#namec").val();
		var id = $("#idc").val();
		data = {
			"id": id,
			"name": name
		};
		$.ajax({
			"url": host+"/bigType/save",
			"type": "post",
			"data": data,
			"dataType": "json",
			"success": function(result) {
				DisplayListItems(result);
			},
			"error": function() {}
		});
		window.location.reload();

		function DisplayListItems(list) {
			alert("修改成功！")
		}
	});
});

function getSeatingCarMark(element) {
	$.ajax({
		"url": host+"/bigType/findById?id=" + element,
		"type": "get",
		"dataType": "json",
		"success": function(result) {
			DisplayListItems(result);
		},
		"error": function() {
			alert("出现错误！");
		}
	});

	function DisplayListItems(result) {
		$("#idc").val(result.data.id);
		$("#namec").val(result.data.name);
	}
}

function deleteById(element) {
	var id = $(this).prev().val();
	var c = $(this).parent().parent();
	$.confirm({
		title: '警告',
		content: '您是否要删除此条数据?',
		type: 'red',
		typeAnimated: true,
		buttons: {
			tryAgain: {
				text: '确定',
				btnClass: 'btn-red',
				action: function() {
					$.ajax({
						"url": host+"/bigType/deleteById?id=" + element,
						"type": "get",
						"dataType": "json",
						success: function(response) {
							var dd1 = $(this).parent(); //列表框id
							dd1.hide();
							dd1.remove();
							window.location.reload();

						}
					});
				}
			},
			close: function() {
				text: '取消'
			}
		}
	});
};