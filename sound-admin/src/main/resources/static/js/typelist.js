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
function dellallType() {
	$("#delall").click(function() {
		var listTable = $('#listTable');
		var items = $("input[name='item']");
		var code;
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
									"url": "/type/deleteById?id=" + id,
									"type": "get",
									"data": "",
									"dataType": "json",
									"success": function(result) {
										code = result.code;
									},
									"error": function() {
										//alert("出现错误！");
									}
								});
								items[j].parentNode.parentNode.remove();
							}
						}
						window.location.reload();
					}
				},
				close: function() {
					text: '取消'
				}
			}
		});
	});
}
/*
 * 页面初始化
 */
$(document).ready(function() {
	
	//全部删除
	dellallType();
	
	var host = "";

	/*
	 大类型绑定
	 */
	$.ajax({
		type: "get",
		url: host + '/bigType/findAll',
		dataType: "json",
		success: function(response) {

			if(response.code == "000000") {
				var dd1 = $("#bt"); //列表框id
				var dd2 = $("#bta");
				//方法1：添加默认节点
				dd1.append("<option value='-1'>--请选择--</option>");
				dd2.append("<option value='-1'>--请选择--</option>");
				//循环遍历 下拉框绑定
				$.each(response.data, function(index, value) {
					//第一种方法
					var opt = "<option value='" + value.id + "'>" + value.name + "</option>";
					dd1.append(opt);
					dd2.append(opt);
				});
			}
		}
	});

	/*推荐*/
	$("input[name='recommended']").click(function() {

		var id = $(this).prev().val();
		if(id == 1) {
			$(this).val("未推荐");
			$(this).removeClass("btn btn-info").addClass("btn btn-danger");
			$(this).prev().val("2");
		} else {
			$(this).val("已推荐");
			$(this).removeClass("btn btn-danger").addClass("btn btn-info");
			$(this).prev().val("1");
		}
		$.ajax({
			"url": "/json/data.json",
			"type": "get",
			"data": id,
			"dataType": "json",
			"success": function(result) {
				DisplayListItems(result);
			},
			"error": function() {
				alert("出现错误！");
			}
		});

		function DisplayListItems(list) {
			/*$("#num").val(list.name);
			$("#file-1").html(list.path);
			$("#username").val(list.auther);
			$("#pwd").val(list.titl);
			$("#typ").val(list.type);
			$("#addre").val(list.address);
			$("#hid").show();*/
		}
	});
	$("#allrecommended").click(function() {
		var list = $("input[name='item']");
		/*list.each(function(){
		});*/
		for(var i = 0; i < list.length; i++) {
			if(list[i].checked == true) {
				var id = list[i].value;
				$.ajax({
					"url": "/json/data.json",
					"type": "get",
					"data": id,
					"dataType": "json",
					"success": function(result) {},
					"error": function() {
						alert("出现错误！");
					}
				});
			}
		}
	});

	/**
	 * 添加图片上传
	 */
	// upload file
	$("#file1").fileinput({
		language: 'zh', //设置语言
		uploadUrl: "/type/saveImg", //上传地址
		autoReplace: true,
		maxFileCount: 1, //表示允许同时上传的最大文件个数
		allowedFileExtensions: ["jpg", "png", "jpeg"], //可接收的文件后缀
		browseClass: "btn btn-primary", //按钮样式
		dropZoneEnabled: false, //是否显示拖拽区域
		showUpload: false,
		showRemove: true,
		showCaption: false,
		showPreview: true, //是否显示预览
		//maxImageWidth:300, //上传图片最大宽度
		//maxImageHeight:130, //上传图片最大高度
		maxFileSize:100,	//上传图片的最大kb
		enctype: 'multipart/form-data', //上传的文件格式
		previewFileIcon: "<i class='glyphicon glyphicon-king'></i>", //这个不要动
		layoutTemplates: { //这个控制预览图标
			actionUpload: "",
		}
	}).on("filebatchselected", function(event, files) { //异步上传返回结果处理
		$(this).fileinput("upload");
	}).on('fileuploaded', function(event, data, previewId, index) { //上传后
		var form = data.form;
		var files = data.files;
		var extra = data.extra;
		var response = data.response;
		var reader = data.reader;

		var name = $("#name").val();
		$("#key1").val(data.response.data);
	});

	/*图片更新*/
	$("#filea").fileinput('destroy');
	$("#filea").fileinput({
		language: 'zh', //设置语言
		uploadUrl: "/type/saveImg", //上传地址
		autoReplace: true,
		maxFileCount: 1, //表示允许同时上传的最大文件个数
		allowedFileExtensions: ["jpg", "png", "jpeg", "ico"], //可接收的文件后缀
		browseClass: "btn btn-primary", //按钮样式
		dropZoneEnabled: false, //是否显示拖拽区域
		showUpload: false,
		showRemove: true,
		showCaption: false,
		showPreview: true, //是否显示预览
		maxFileSize:100,	//上传图片的最大kb
		enctype: 'multipart/form-data', //上传的文件格式
//		uploadExtraData: function(previewId, index) {
//			var data = {
//				"id": $("#id").val(),
//				"name": $("#namea").val(),
//				"bigTypeId": $("#bta").val(),
//				"cloId": 1
//			};
//			return data;
//		},
		previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
		layoutTemplates: { //这个控制预览图标
			actionUpload: "",
		},
		initialPreview: "<img style='width:213px;height:131px' src='' class='file-preview-image'/>"
	}).on("filebatchselected", function(event, files) { //异步上传返回结果处理
		$(this).fileinput("upload");
	}).on('fileuploaded', function(event, data, previewId, index) { //上传前
		var key1 = data.response.data;
		console.log(key1);
		$("#key2").val(key1);
	});

});

//function addType() {

//	$("#addType").click(function() {
//		var arr = $("#selectTag option:selected");
//		var temp = new Array();
//		for(var i = 0; i < arr.length; i++) {
//			temp[i] = $(arr[i]).text();
//		}
//
//		var b = temp.join(",");
//		var obj = {
//			"title": $("#title").val(),
//			"bigTypeName": $("#bt option:selected").text(),
//			"logoUrl": $("#key1").val(),
//			"logoName": $("#logoName").val(),
//		}
//		console.log(logoName);
//		$.ajax({
//			async: false,
//			type: "post",
//			url: "/type/save",
//			dataType: "json",
//			data: obj,
//			success: function(response) {}
//		});
//		window.location.reload();
//	});
//}


/**
 * 根据推荐类型查询
 */
$(document).ready(function() {
	$("#priority").change(function() {
		var priority = $(this).find("option:selected").val();
		if(priority == -1) {
			return;
		}
		$.ajax({
			type: "get",
			url: "/type/findByPriority",
			dataType: "json",
			data: {
				"priority": priority
			},
			success: function(result) {
				if(result.code == "000000") {
					$("#listTable").children().remove();
					$.each(result.data, function(index, element) {
						var unixTimestamp = new Date(element.createTime);
						commonTime = unixTimestamp.toLocaleString();
						var itemHTML = "<tr>" +
							"<td>" +
							"<input type='checkbox' name='item' value=" + element.id + ">" +
							"</td>" +
							"<td>" +
							element.name +
							"</td>" +
							"<td>" +
							element.bigTypeId +
							"</td>" +
							"<td>" +
							commonTime +
							"</td>" +
							"<td>" +
							"<select class='form-control'>" +
							"<option value='2'>不推荐</option>" +
							"<option value='1' >类型推荐</option>" +
							"<option value='0' >置顶推荐</option>" +
							"</select>" +
							"</td>" +
							"<td>" +
							"<input type='hidden'  class='hid' value=" + element.id + ">" +
							"<input type='button' name='delete' value='删除' class='btn btn-danger'/>&nbsp;" +
							"<input type='hidden'  class='hid' value=" + element.name + ">" +
							"<input type='button' name='change' value='修改' class='btn btn-info' data-toggle='modal' data-target='#change'/>&nbsp;" +
							"<input type='hidden'  class='hid' value=" + element.reco + ">" +
							"<input type='hidden' class='form-control' id='minImage' value=" + element.logoUrl + " />" +
							"</td>" +
							"</<tr>";
						$("#listTable").append(itemHTML);
						switch(parseInt(element.priority)) {
							case 0:
								$("#listTable tr:last-child td option").eq(0).attr("selected", "selected"); //不推荐
								break;
							case 1:
								$("#listTable tr:last-child td option").eq(1).attr("selected", "selected"); //类型
								break;
							case 2:
								$("#listTable tr:last-child td option").eq(2).attr("selected", "selected"); //置顶
								break;
							default:
								$("#priority option").eq(0).attr("selected", "selected"); //默认不推荐
								break;
						}
					});
				};
			}
		});
	});

	/**
	 * 修改类型推荐
	 */
	$("[id='select']").change(function() {
		var priority = $(this).find("option:selected").val();
		var id = $(this).parent().parent().children("td").eq(0).children().val();
		var name = $(this).parent().parent().children("td").eq(1).text();
		var bigTypeId = $(this).parent().parent().children("td").eq(2).text();
		var logoUrl = $(this).parent().parent().children("td").eq(5).children().eq(5).val();
		$.ajax({
			type: "post",
			url: "/type/updatePriority",
			dataType: "json",
			data: {
				"id": id,
				"name": name,
				"bigTypeId": bigTypeId,
				"logoUrl": logoUrl,
				"priority": priority,
				"cloId": 1,
				"logoName": "图片"
			},
			success: function(response) {
				if(response.code == "000000") {
					alert(123);
				};
			}
		});
	});
});

//$(document).ready(function() {
//	upType();
//
//});