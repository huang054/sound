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

//删除所有
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
									"url": "/audio/deleteById?id=" + id,
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

$(document).ready(function() {

//全部删除
dellallType();

	/*
		 添加专辑绑定
	 */
	$.ajax({
		type: "get",
		url: "/album/findAll",
		dataType: "json",
		success: function(response) {

			if(response.code == "000000") {
				var dd1 = $("#selectAlbum"); //列表框id
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

	/*
		 更新专辑绑定
	 */
	$.ajax({
		type: "get",
		url: "/album/findAll",
		dataType: "json",
		success: function(response) {

			if(response.code == "000000") {
				var dd1 = $("#selectAlbum2"); //列表框id
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
			"url": "./json/data.json",
			"type": "post",
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
			$("#addre").val(list.address);*/
		}
	});
	$("#allrecommended").click(function() {
		var list = $("input[name='item']");
		/*list.each(function(){
		    if($(this).checked==true) {
		    }
		});*/
		for(var i = 0; i < list.length; i++) {
			if(list[i].checked == true) {
				var id = list[i].value;
				$.ajax({
					"url": "./json/data.json",
					"type": "post",
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

	/*
	 * 添加图片上传
	 */
	$("#file1").fileinput({
		language: 'zh', //设置语言
		uploadUrl: "/audio/saveImg", //上传地址
		autoReplace: true,
		maxFileCount: 1, //表示允许同时上传的最大文件个数
		allowedFileExtensions: ["jpg", "png", "jpeg", "ico", "mp3"], //可接收的文件后缀
		browseClass: "btn btn-primary", //按钮样式
		dropZoneEnabled: false, //是否显示拖拽区域
		showUpload: false,
		showRemove: true,
		showCaption: false,
		zoomModalHeight: 600, //图片预览高度
		showPreview: true, //是否显示预览
		enctype: 'multipart/form-data', //上传的文件格式
		//		uploadExtraData: function(previewId, index) {
		//			var data = {
		//				"title": $("#title").val(),
		//				"albumName": $("#selectAlbum option:selected").text(),
		//				"author": $("#author").val(),
		//				"label": $("#selectTag option:selected").text(),
		//				"albumId": 1,
		//				"tagId": "1",
		//				"durantionSec": "1231231",
		//
		//			}
		//			return data;
		//		},
		previewFileIcon: "<i class='glyphicon glyphicon-king'></i>", //这个不要动
		layoutTemplates: { //这个控制预览图标
			actionUpload: "",
		}
	}).on("filebatchselected", function(event, files) { //异步上传返回结果处理
		console.log(files);
		$(this).fileinput("upload");
	}).on('fileuploaded', function(event, data, previewId, index) { //上传前
		var key1 = data.response.data;
		console.log(key1);
		for(var i = 0; i < key1.length; i++) {
			$("#key1").val(key1[0]);
			$("#fileName1").val(key1[1]);
			$("#md51").val(key1[2]);
			$("#isCacheLocal1").val(key1[3]);
			$("#durantionSec").val(key1[4]);
		}
	});

	/**
	 * 更新
	 */
	// upload file
	$("#filec").fileinput({
		language: 'zh', //设置语言
		uploadUrl: "/audio/saveImg", //上传地址
		autoReplace: true,
		maxFileCount: 1, //表示允许同时上传的最大文件个数
		allowedFileExtensions: ["jpg", "png", "jpeg", "ico", "mp3"], //可接收的文件后缀
		browseClass: "btn btn-primary", //按钮样式
		dropZoneEnabled: false, //是否显示拖拽区域
		showUpload: false,
		showRemove: true,
		showCaption: false,
		zoomModalHeight: 600, //图片预览高度
		showPreview: true, //是否显示预览
		enctype: 'multipart/form-data', //上传的文件格式
//		uploadExtraData: function(previewId, index) {
//			var data = {
//				"id": $("#id").val(),
//				"title": $("#titlec").val(),
//				"albumName": $("#selectAlbum2 option:selected").text(),
//				"author": $("#authorc").val(),
//				"label": $("#selectTag2 option:selected").text(),
//				"albumId": 1,
//				"tagId": 1,
//				"durantionSec": $("#durantionSec").val(),
//
//			}
//			return data;
//		},
		previewFileIcon: "<i class='glyphicon glyphicon-king'></i>", //这个不要动
		layoutTemplates: { //这个控制预览图标
			actionUpload: "",
		},
	}).on("filebatchselected", function(event, files) { //异步上传返回结果处理
		$(this).fileinput("upload");
	}).on('fileuploaded', function(event, data, previewId, index) { //上传前
		var key1 = data.response.data;
		console.log(key1);
		for(var i = 0; i < key1.length; i++) {
			$("#key2").val(key1[0]);
			$("#fileName2").val(key1[1]);
			$("#md52").val(key1[2]);
			$("#isCacheLocal2").val(key1[3]);
			$("#durantionSec2").val(key1[4]);
		}
	});

});

/* 
	 添加标签绑定
	 */
$.ajax({
	type: "get",
	url: "/tag/findAll",
	dataType: "json",
	success: function(response) {

		if(response.code == "000000") {
			var dd1 = $("#selectTag"); //列表框id
			//方法1：添加默认节点
			dd1.append("<option value='-1'>--请选择--</option>");
			//循环遍历 下拉框绑定
			$.each(response.data, function(index, value) {
				//第一种方法
				var opt = "<option value='" + value.id + "'>" + value.name + "</option>";
				dd1.append(opt);
				$("#selectTag").selectpicker('refresh');
			});
		}
	}
});

/* 
	 更新标签绑定
	 */
$.ajax({
	type: "get",
	url: "/tag/findAll",
	dataType: "json",
	success: function(response) {

		if(response.code == "000000") {
			var dd1 = $("#selectTag2"); //列表框id
			//方法1：添加默认节点
			dd1.append("<option value='-1'>--请选择--</option>");
			//循环遍历 下拉框绑定
			$.each(response.data, function(index, value) {
				//第一种方法
				var opt = "<option value='" + value.id + "'>" + value.name + "</option>";
				dd1.append(opt);
				$("#selectTag2").selectpicker('refresh');
			});
		}
	}
});

/**
 * 更改标签初始化
 */
function upTagInit() {
	$.ajax({
		async: false,
		type: "get",
		url: "/tag/findAll",
		dataType: "json",
		success: function(response) {
			if(response.code == "000000") {
				var dd1 = $("selectTag2"); //列表框id
				dd1.empty();
				//方法1：添加默认节点
				dd1.append("<option value='-1' disabled='true'>--请选择--</option>");

				//循环遍历 下拉框绑定
				$.each(response.data, function(index, value) {
					//第一种方法
					var opt = "<option value='" + value.id + "'>" + value.name + "</option>";
					dd1.append(opt);

				});

			}
			$("#selectTag2").selectpicker('refresh');
		}
	});
}  

/**
 * 更改专辑初始化
 */
function upAlbumInit() {
	$.ajax({
		type: "get",
		url: "/album/findAll",
		dataType: "json",
		success: function(response) {

			if(response.code == "000000") {
				var dd1 = $("#selectAlbum2"); //列表框id

				//方法1：添加默认节点
				dd1.append("<option value='-1'>--请选择--</option>");

				//循环遍历 下拉框绑定
				$.each(response.data, function(index, value) {
					//第一种方法
					var opt = "<option value='" + value.id + "'>" + value.name + "</option>";
					dd1.append(opt);

				});
			}
		}
	});
}

/**
 * 根据推荐等级查询
 */
$(document).ready(function() {
	$("#priority").change(function() {
		var priority = $(this).find("option:selected").val();
		if(priority == -1) {
			return;
		}
		$.ajax({
			type: "get",
			url: "/audio/findByPriority",
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
							element.title +
							"</td>" +
							"<td>" +
							element.albumName +
							"</td>" +
							"<td>" +
							element.author +
							"</td>" +
							"<td>" +
							"<select class='form-control'>" +
							"<option value='2'>不推荐</option>" +
							"<option value='1' >类型推荐</option>" +
							"<option value='0' >置顶推荐</option>" +
							"</select>" +
							"</td>" +
							"<td>" +
							commonTime +
							"</td>" +
							"<td>" +
							element.praiseCount +
							"</td>" +
							"<td>" +
							element.readCount +
							"</td>" +
							"<td>" +
							element.label +
							"</td>" +
							"<td>" +
							element.durantionSec +
							"</td>" +
							"<td>" +
							"<input type='hidden'  class='hid' value=" + element.id + ">" +
							"<input type='button' name='delete' value='删除' class='btn btn-danger'onclick=\"deleteById('" + element.id + "')\"/>&nbsp;" +
							"<input type='button' name='change' value='修改' class='btn btn-info' data-toggle='modal' data-target='#mytypechange'onclick=\"getSeatingCarMark('" + element.id + "')\"/>&nbsp;" +
							"<input type='hidden'  class='hid' value=" + element.reco + ">" +
							"<input type='hidden' class='form-control' id='minImage' value=" + element.filepath + " />" +
							"<input type='hidden' class='form-control' id='priority_value' value=" + element.priority + " />" +
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
});

//function addAudio() {

//	$("#addAudio").click(function() {
//
//		var arr = $("#selectTag option:selected");
//		var temp = new Array();
//		for(var i = 0; i < arr.length; i++) {
//			temp[i] = $(arr[i]).text();
//		}
//
//		var b = temp.join(",");
//		var obj = {
//			"title": $("#title").val(),
//			"albumName": $("#selectAlbum option:selected").text(),
//			"author": $("#author").val(),
//			"label": b,
//			"albumId": 1,
//			"tagId": "1",
//			"durantionSec": "1231231",
//			"filepath": $("#key1").val(),
//			"fileName":$("#fileName1").val(),
//			"isLocal":$("#isCacheLocal1").val(),
//			"md5":$("md51").val(),
//		}
//		$.ajax({
//			async: false,
//			type: "post",
//			url: "/audio/save",
//			dataType: "json",
//			data: obj,
//			success: function(response) {
//			}
//		});
//		window.location.reload();
//	});
//}

/**
 * 修改
 */
function upAudio() {

	$("#upAudio").click(function() {
		$.confirm({
			title: '提示',
			content: '您是否要对此条数据进行更改?',
			type: 'blue',
			typeAnimated: true,
			buttons: {
				tryAgain: {
					text: '确定',
					btnClass: 'btn-blue',
					action: function() {
						var arr = $("#selectTag2 option:selected");
						var temp = new Array();
						for(var i = 0; i < arr.length; i++) {
							temp[i] = $(arr[i]).text();
						}
						var b = temp.join(",");
						var obj = {
							"id": $("#id").val(),
							"title": $("#titlec").val(),
							"albumName": $("#selectAlbum2 option:selected").text(),
							"authorName": $("#authorc").val(),
							"label": b,
							"albumId": $("#selectAlbum2").val(),
							"filepath": $("#key2").val(),
							"fileName": $("#fileName2").val(),
							"isLocal": $("#isCacheLocal2").val(),
							"md5": $("#md52").val(),
							"tagId":1,
							"priority":0,
							"readCount":0,
							"praiseCount":0,
							"remark":"",
							"fileSize":0,
							"playCount":0,
							"ticket":"",
							"durantionSec":$("#durantionSec2").val()
						}
						$.ajax({
							async: false,
							type: "post",
							url: "/audio/save",
							dataType: "json",
							data: obj,
							success: function(response) {}
						});
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