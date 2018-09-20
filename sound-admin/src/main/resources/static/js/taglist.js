/**
 * Created by Administrator on 2018/4/21.
 */
function checkAll(c) {
	var status = c.checked;
	var oItems = $("input[name='item']");
	for(var i = 0; i < oItems.length; i++) {
		oItems[i].checked = status;
	}
}  
 
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
                        //alert(333);
                        for(var j = 0; j < items.length; j++) {
                            if(items[j].checked) {
                                var id = items[j].value;
                                $.ajax({
                                    "url": host+"/tag/deleteById?id=" + id,
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
	window.location.reload();
}

$(document).ready(function() {
var host = "";
	/*
	 页面初始化
	 */
//	function init() {
//		$.ajax({
//			"url": host+"/tag/findAll",
//			"type": "get",
//			"async": false,
//			"dataType": "json",
//			"success": function(result) {
//				/*
//				 数据展示
//				 */
//				$.each(result.data, function(index, element) {
//					var unixTimestamp = new Date(element.createTime);
//					commonTime = unixTimestamp.toLocaleString();
//					var itemHTML = "<tr>" +
//						"<td>" +
//						"<input type='checkbox' name='item' value=" + element.id + ">" +
//						"</td>" +
//						"<td>" +
//						element.name +
//						"</td>" +
//						"<td>" +
//						commonTime +
//						"</td>" +
//						"<td>" +
//						"<input type='hidden'  class='hid' value=" + element.id + ">" +
//						"<input type='button' name='delete' value='删除' class='btn btn-danger'/>&nbsp;" +
//						"<input type='hidden'  class='hid' value=" + element.name + ">" +
//						"<input type='button' name='change' value='修改' class='btn btn-info' data-toggle='modal' data-target='#mytagchange'/>&nbsp;" +
//						"</td>" +
//						"</<tr>";
//					$("#listTable").append(itemHTML);
//				});
//			},
//			"error": function() {
//				alert("出现错误！");
//			}
//		});
//	}
//	init();

//	$("input[name='delete']").click(function() {
//		var id = $(this).prev().val();
//		var c = $(this).parent().parent();
//		$.confirm({
//			title: '警告',
//			content: '您是否要删除此条数据?',
//			type: 'red',
//			typeAnimated: true,
//			buttons: {
//				tryAgain: {
//					text: '确定',
//					btnClass: 'btn-red',
//					action: function() {
//						$.ajax({
//							"url": host+"/tag/deleteById?id=" + id,
//							"type": "get",
//							"data": id,
//							"dataType": "json",
//							success: function(response) {
//								var dd1 = $(this).parent(); //列表框id
//								dd1.hide();
//								dd1.remove();
//								window.location.reload();
//
//							}
//						});
//					}
//				},
//				close: function() {
//					text: '取消'
//				}
//			}
//		});
//	});

	/*修改*/
	$("input[name='change']").click(function() {
		var id = $(this).prev().prev().prev().val();
		var name = $(this).prev().val();
		$("#namec").val(name);
		$("#idc").val(id);
		json = {
			"id": id,
			"name": name
		};
	});

	$("#change").click(function() {
		var name = $("#namec").val();
		var id = $("#idc").val();
		data = {
			"id": id,
			"name": name
		};
		$.ajax({
			"url": host+"/tag/save",
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