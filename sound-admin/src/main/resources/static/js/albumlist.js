var page = 0;
var size = 5;
var count = 0;
var courrentpage = 1;
var test;
/**22
 * 添加初始化
 */

function addInit() {
	/**
	 类型绑定
	 */
	$.ajax({
		type: "get",
		url: "/type/findAll",
		dataType: "json",
		success: function(response) {

			if(response.code == "000000") {
				var dd1 = $("#a_type"); //列表框id

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
	/**
	 * 标签绑定
	 */
	$.ajax({
		type: "get",
		url: "/tag/findAll",
		dataType: "json",
		success: function(response) {
			if(response.code == "000000") {
				var dd1 = $("#a_tag"); //列表框id

				//方法1：添加默认节点
				dd1.append("<option value='-1' disabled='true'>--请选择--</option>");

				//循环遍历 下拉框绑定
				$.each(response.data, function(index, value) {
					//第一种方法
					var opt = "<option value='" + value.id + "'>" + value.name + "</option>";
					dd1.append(opt);
					$("#a_tag").selectpicker('refresh');
				});

			}
		}
	});

	/**
	 * 专辑大封面上传
	 */
	// upload file
	$("#file-1").fileinput({
		language: 'zh', //设置语言
		uploadUrl: "/album/saveOneImg", //上传地址
		autoReplace: true, //自动替换
		maxFileCount: 1, //表示允许同时上传的最大文件个数
		allowedFileExtensions: ["jpg", "png", "jpeg", "ico"], //可接收的文件后缀
		browseClass: "btn btn-primary", //按钮样式
		dropZoneEnabled: false, //是否显示拖拽区域
		showUpload: false, //是否显示上传按钮
		showRemove: false, //是否显示移除按钮
		showCaption: false, //是否显示文件标题
		zoomModalHeight: 300, //图片预览高度
		showPreview: true, //是否显示预览
		maxImageWidth:350,
		maxImageHeight:350,
		enctype: 'multipart/form-data', //上传的文件格式
		/*uploadExtraData: function(previewId, index) {			//附带参数
			var obj = {};
			$('#addForm').find('input').each(function() {
				var id = $(this).attr('id'),
					val = $(this).val();
				obj[id] = val;
			});
			return obj;
		},*/
		previewFileIcon: "<i class='glyphicon glyphicon-king'></i>", //这个不要动
		layoutTemplates: { //这个控制预览图标
			actionUpload: "",
		}
	}).on("filebatchselected", function(event, files) { //异步上传返回结果处理
		$(this).fileinput("upload");
	}).on("fileuploaded", function(event, data, previewId, index) {
		var res = data.response;
		//alert(res.data);
		$("#file-1_src").val(res.data);
	});

	/**
	 * 专辑小封面
	 */
	$("#file-2").fileinput({
		language: 'zh', //设置语言
		uploadUrl: "/album/saveOneImg", //上传地址
		autoReplace: true, //自动替换
		ansyc: true,
		maxFileCount: 1, //表示允许同时上传的最大文件个数
		allowedFileExtensions: ["jpg", "png", "jpeg", "ico"], //可接收的文件后缀
		browseClass: "btn btn-primary", //按钮样式
		dropZoneEnabled: false, //是否显示拖拽区域
		showUpload: false, //是否显示上传按钮
		showRemove: true, //是否显示移除按钮
		showCaption: false, //是否显示文件标题
		zoomModalHeight: 300, //图片预览高度
		showPreview: true, //是否显示预览
		maxImageWidth:350,
		maxImageHeight:350,
		enctype: 'multipart/form-data', //上传的文件格式
		/*uploadExtraData: function(previewId, index) { //附带参数
			var arr = $("#a_tag option:selected");

			var temp = new Array();
			for(var i = 0; i < arr.length; i++) {
				temp[i] = $(arr[i]).text();
			}
			var b = temp.join(",");
			var obj = {
				"name": $("#a_name").val(),
				"title": $("#a_name").val(),
				"authorId": $("#a_author").val(),
				"type": $("#a_type option:selected").text(),
				"typeId": $("#a_type option:selected").val(),
				"tag": b,
				"imgMaxUrl": $("#file-1_src").val(),
				"imgMinUrl": "t",
				"summary": $("#a_summary").val(),
				"priority": 0,
				"playCount": 0,
				"audioCount": 0
			};
			return obj;
		},*/
		previewFileIcon: "<i class='glyphicon glyphicon-king'></i>", //这个不要动
		layoutTemplates: { //这个控制预览图标
			actionUpload: "",
		}
	}).on("filebatchselected", function(event, files) { //异步上传返回结果处理
		$(this).fileinput("upload");
	}).on("fileuploaded", function(event, data, previewId, index) {
		var res = data.response;
		$("#file2_src").val(res.data);
		//alert(res.data);
		//alert(res.code);
	});

}

function pageInit() {
	$.ajax({
		"url": "/album/findByPage",
		"type": "get",
		"data": "",
		"async": false,
		"dataType": "json",
		"success": function(result) {
			count = result.data.length;
			/*alert(Math.floor(count/size))
			alert((count%size>0?1:0))*/
			//alert(Math.floor(count/size)+(count%size>0?1:0))
			$("#nextPageval").val(Math.floor(count / size) + (count % size > 0 ? 1 : 0));
		}
	});
}

$(document).ready(function() {
	Init(page, size);
	findByPriority();
	addInit();
	upAlbum();
	addAlbum();
	deleteAlbum();
	tagInit();
	updatePriority();
	updateInit();
	$("#homePage").click(function() {
		$("#listTable").html("");
		page = 0;
		courrentpage = 1;
		Init(0, size);
	});
	$("#preciousPage").click(function() {
		var nowpage = parseInt($("#now").text());
		if(nowpage <= 1) {
			return;
		} else {
			page = nowpage - 1;
		}
		courrentpage = nowpage - 1;
		$("#listTable").html("");
		Init(page, size);
		updatePriority();
	});
	$("#nextPage").click(function() {
		var nowpage = parseInt($("#now").text());
		//alert(nowpage+"ssssssssss");
		if(nowpage >= $("#nextPageval").val()) {
			return;
		} else {
			page = nowpage + 1;
		}
		//alert(page);
		courrentpage = nowpage + 1;
		$("#listTable").html("");
		//alert(page+"ssssssss"+size);
		Init(page, size);
	});
	$("#trailerPage").click(function() {
		$("#listTable").html("");
		page = $("#endPage").val() - 1;
		courrentpage = $("#nextPageval").val();
		Init(page, size);
	});
	
	/**
	 * 第一个数字点击
	 */
	$("#nowL").click(function() {
		var nowpage = parseInt($("#nowL").text());
		//alert(nowpage+"ssssssssss");
		//alert(page);
		courrentpage = nowpage + 1;
		$("#listTable").html("");
		//alert(page+"ssssssss"+size);
		Init(nowpage, size);
	});
	
	/**
	 * 第二个数字点击
	 */
	$("#nowN").click(function() {
		var nowpage = parseInt($("#nowN").text());
		//alert(nowpage+"ssssssssss");
		//alert(page);
		courrentpage = nowpage + 1;
		$("#listTable").html("");
		//alert(page+"ssssssss"+size);
		Init(nowpage, size);
	});
	
	/**
	 * 前三点点击
	 */
	$("#precious").click(function() {
		var nowpage = parseInt($("#now").text());
		//alert(nowpage+"ssssssssss");
		//alert(page);
		courrentpage = nowpage -3;
		$("#listTable").html("");
		//alert(page+"ssssssss"+size);
		Init(courrentpage, size);
	});
	
	/**
	 * 后三点点击
	 */
	$("#next").click(function() {
		var nowpage = parseInt($("#now").text());
		//alert(nowpage+"ssssssssss");
		//alert(page);
		courrentpage = nowpage + 3;
		$("#listTable").html("");
		//alert(page+"ssssssss"+size);
		Init(courrentpage, size);
	});
});

function updateInit() {
	$("[name='change']").click(function() {
		//alert(222);
		var hosts = "http://p81lqfddq.bkt.clouddn.com/";
		var maxSrc = $(this).next().next().val();
		//alert(maxSrc);
		$("#file-3_src").val(maxSrc);
		var minSrc = $(this).next().next().next().val();
		$("#file-4_src").val(minSrc);
		var summary = $(this).next().next().next().next().val();
		var priority = $(this).next().next().next().next().next().val();
		var display = $(this).next().next().next().next().next().next().val();
		//alert(display);
		//var playCount=$(this).next().next().next().next().next().val();
		//var audioCount=$(this).next().next().next().next().next().val();
		maxSrc = hosts + maxSrc;
		minSrc = hosts + minSrc;
		//alert(maxSrc + "--" + minSrc);
		//骄傲的我不想写注释
		upTagInit();

		upTypeInit();

		upImage(maxSrc, minSrc);

		//$("#u_tag").selectpicker('val', 12);
		var test = $(this).parent().parent().children("td");
		var trs = test.eq(1).text();
		if(display == true) {
			$("#optionsRadios2").attr('checked', 'checked');
		} else {
			$("#optionsRadios1").attr('checked', 'checked');
		}
		$("#u_name").val(test.eq(2).text());
		$("#priority_value").val(priority);
		$("#u_author").val(test.eq(3).text());
		$("#u_summary").val(summary);
		$("#u_playCount").val(test.eq(7).text());
		$("#u_audioCount").val(test.eq(8).text());
		//alert(test.eq(7).text() + "=======" + test.eq(8).text());
		var type = test.eq(6).text();
		//alert(type);
		var all_type = $("#u_type > option");
		//alert(all_type.length);
		for(var i = 0; i < all_type.length; i++) {
			if(type == $(all_type[i]).text()) {
				//alert(111111111);
				$(all_type[i]).attr("selected", "selected");
				break;
			}
		}
		var tag = $(this).next().val();
		if(tag != null) {
			var tags = tag.split(",");
			var te = new Array();
			var all_select = $("#u_tag > option");

			for(var i = 0; i < tags.length; i++) {
				//alert(tags[i]);
				for(var j = 0; j < all_select.length; j++) {

					if(tags[i] == $(all_select[j]).text()) {
						//alert(222);
						te[i] = $(all_select[j]).val();
						break;
					}
				}
			}
			$("#u_tag").selectpicker('val', te);
		}
		//$("#ma").val(trs);
		$("#u_id").val(trs);
	});
}

function tagInit() {
	var dd1 = $("#tagListTable");
	$("a").click(function() {
		$("#tagListTable").empty();
		var tag = $(this).next().val();
		//alert(tag);
		if(tag != null) {

			var tags = tag.split(",");
			for(var i = 0; i < tags.length; i++) {
				var htmls = "<button type='button' class='btn btn-default btn-sm'>" +
					"<span class='glyphicon glyphicon-tag'>" + tags[i] + "</span>" +
					"</button>";
				dd1.append(htmls);
			}
		}
	})
}

/**
 * 更新图片初始化
 */
function upImage(MaxImgUrl, MinImgUrl) {
	$("#file-3").fileinput('destroy');
	$("#file-3").fileinput({
		language: 'zh', //设置语言
		uploadUrl: "/album/saveOneImg", //上传地址
		autoReplace: true, //自动替换
		maxFileCount: 1, //表示允许同时上传的最大文件个数
		allowedFileExtensions: ["jpg", "png", "jpeg", "ico"], //可接收的文件后缀
		browseClass: "btn btn-primary", //按钮样式
		dropZoneEnabled: false, //是否显示拖拽区域
		showUpload: false, //是否显示上传按钮
		showRemove: false, //是否显示移除按钮
		showCaption: false, //是否显示文件标题
		zoomModalHeight: 300, //图片预览高度
		showPreview: true, //是否显示预览	
		maxImageWidth:350,
		maxImageHeight:350,
		maxFileSize:100,
		enctype: 'multipart/form-data', //上传的文件格式
		/*uploadExtraData: function(previewId, index) {			//附带参数
			var obj = {};
			$('#addForm').find('input').each(function() {
				var id = $(this).attr('id'),
					val = $(this).val();
				obj[id] = val;
			});
			return obj;
		},*/
		previewFileIcon: "<i class='glyphicon glyphicon-king'></i>", //这个不要动
		layoutTemplates: { //这个控制预览图标
			actionUpload: "",
		},
		initialPreview: "<img src=" + MaxImgUrl + " class='file-preview-image' width='213px' height='239px'/>", // IMAGE RAW MARKUP 
		initialPreviewConfig: [{
			width: '213px',
			height: '239px'
		}]
	}).on("filebatchselected", function(event, files) { //异步上传返回结果处理
		$(this).fileinput("upload");
	}).on("fileuploaded", function(event, data, previewId, index) {
		var res = data.response;
		//alert(res.data);
		$("#file-3_src").val(res.data);
	});

	/**
	 * 专辑小封面
	 */
	$("#file-4").fileinput('destroy');
	$("#file-4").fileinput({
		language: 'zh', //设置语言
		uploadUrl: "/album/saveOneImg", //上传地址
		autoReplace: true, //自动替换
		maxFileCount: 1, //表示允许同时上传的最大文件个数
		allowedFileExtensions: ["jpg", "png", "jpeg", "ico"], //可接收的文件后缀
		browseClass: "btn btn-primary", //按钮样式
		dropZoneEnabled: false, //是否显示拖拽区域
		showUpload: false, //是否显示上传按钮
		showRemove: true, //是否显示移除按钮
		showCaption: false, //是否显示文件标题
		zoomModalHeight: 300, //图片预览高度
		showPreview: true, //是否显示预览
		maxImageWidth:350,
		maxImageHeight:350,
		maxFileSize:100,
		enctype: 'multipart/form-data', //上传的文件格式
		initialPreview: "<img src=" + MinImgUrl + " class='file-preview-image' width='213px' height='239px'/>", // IMAGE RAW MARKUP
		/*uploadExtraData: function(previewId, index) { //附带参数
			var arr = $("#u_tag option:selected");
			var temp = new Array();
			for(var i = 0; i < arr.length; i++) {
				temp[i] = $(arr[i]).text();
			}
			var b = temp.join(",");
			var obj = {
				"id":$("#u_id").val(),
				"name": $("#u_name").val(),
				"title": $("#u_name").val(),
				"authorId": $("#u_author").val(),
				"type": $("#u_type option:selected").val(),
				"tag": b,
				"imgMaxUrl": $("#file-1_src").val(),
				"summary":$("#u_summary").val(),
				"playCount":$("#u_playCount").val(),
				"audioCount":$("#u_audioCount").val()
			};
			return obj;
		},*/
		previewFileIcon: "<i class='glyphicon glyphicon-king'></i>", //这个不要动
		layoutTemplates: { //这个控制预览图标
			actionUpload: "",
		}
	}).on("filebatchselected", function(event, files) { //异步上传返回结果处理
		$(this).fileinput("upload");
	}).on("fileuploaded", function(event, data, previewId, index) {
		var res = data.response;
		$("#file-4_src").val(res.data);
		//alert(res.code);
	});
}
/**
 * 更改标签初始化
 */
function upTagInit() {
	//alert(2222);
	$.ajax({
		async: false,
		type: "get",
		url: "/tag/findAll",
		dataType: "json",
		success: function(response) {
			if(response.code == "000000") {
				var dd1 = $("#u_tag"); //列表框id
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
			$("#u_tag").selectpicker('refresh');
			//alert($("#u_tag option").length);
		}
	});
}

/**
 * 更改类型初始化
 */
function upTypeInit() {
	$.ajax({
		type: "get",
		url: "/type/findAll",
		async: false,
		dataType: "json",
		success: function(response) {

			if(response.code == "000000") {
				var dd1 = $("#u_type"); //列表框id

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
 * 推荐
 */
function updatePriority() {
	$("[id='pri']").change(function() {
		//alert(222);
		var id = $(this).parent().parent().children("td").eq(1).text();
		var priority = $(this).find("option:selected").val();
		var albumId = $(this).parent().parent().children("td").eq(1).text();
		var bigimgUrl = $(this).parent().parent().children("td").eq(11).children("input").eq(3).val();
		var bigImgName = "XXX.jpg";
		if(priority == 0 || priority == 1) {
			$.ajax({
				type: "get",
				url: "/recommend/deleteByAlbumId",
				dataType: "json",
				data: {
					"albumId": albumId
				},
				success: function(response) {
					//alert(response.code);
					if(response.code == "000000") {
						//alert(response.code);
					};
				}
			});
			$.ajax({
				type: "get",
				url: "/album/updatePriorityById",
				dataType: "json",
				data: {
					"id": id,
					"priority": priority,
				},
				success: function(response) {
					//alert(response.code);
					if(response.code == "000000") {
						//alert(response.code);
					};
				}
			});
		} else if(bigimgUrl == "/") {
			//			$.ajax({
			//				type: "get",
			//				url: "/recommend/deleteByAlbumId",
			//				dataType: "json",
			//				data: {
			//					"albumId": albumId
			//				},
			//				success: function(response) {
			//					//alert(response.code);
			//					if(response.code == "000000") {
			//						//alert(response.code);
			//					};
			//				}
			//			});
			alert("抱歉,该专辑没有Banner图,请上传一张格式为300*130的专辑Banner图");
			window.location.reload();
			return;
		}

		$.ajax({
			type: "post",
			url: "/recommend/save",
			dataType: "json",
			data: {
				"albumId": albumId,
				"priority": priority,
				"bigimgUrl": bigimgUrl,
				"bigImgName": bigImgName
			},
			success: function(response) {
				//alert(response.code);
				if(response.code == "000000") {
					//alert(response.code);
				};
			}
		});
		$.ajax({
			type: "get",
			url: "/album/updatePriorityById",
			dataType: "json",
			data: {
				"id": id,
				"priority": priority,
			},
			success: function(response) {
				//alert(response.code);
				if(response.code == "000000") {
					//alert(response.code);
				};
			}
		});
		window.location.reload();
	});
}

/**
 * 数据显示
 */
function Init(page, size) {
	if (page<=0) {
		$("#nowL").text(0);
		page=0;
	}else{
		$("#nowL").text(page - 1);
	}
	if (page>=$("#endPage").val()) {
		$("#nowN").text($("#endPage").val());
		page=$("#endPage").val()-1;
	}else{
		$("#nowN").text(page + 1);
	}
	$("#listTable").empty();
	$.ajax({
		"url": "/album/findByPage?page=" + page + "&size=" + size,
		"type": "get",
		"async": false,
		"dataType": "json",
		"success": function(result) {
			
			
			
			$("#now").text(page);
			
			$("#endPage").val(result.data.totalPages);
			//console.log(result.data);
			$.each(result.data.content, function(index, element) {

				var unixTimestamp = new Date(element.createTime);
				var commonTime = unixTimestamp.toLocaleString();
				var itemHTML = "<tr>" +
					"<td>" +
					"<input type='checkbox' name='item' value=" + element.id + ">" +
					"</td>" +
					"<td>" +
					element.id +
					"</td>" +
					"<td>" +
					element.name +
					"</td>" +
					"<td>" +
					element.authorName +
					"</td>" +
					"<td>" +
					"<a class='btn btn-primary' data-toggle='modal' data-target='#tagList'>" +
					"点击查看" +
					"</a>" +
					"<input type='hidden' class='form-control' id='tag' value=" + element.tag + " />" +
					"</td>" +
					"<td>" +
					"<select class='form-control' id='pri'>" +
					"<option value='0' >不推荐</option>" +
					"<option value='1' >类型推荐</option>" +
					"<option value='2' >置顶推荐</option>" +
					"</select>" +
					"</td>" +
					"<td>" +
					element.type +
					"</td>" +
					"<td>" +
					element.playCount +
					"</td>" +
					"<td>" +
					element.audioCount +
					"</td>" +
					"<td>" +
					commonTime +
					"</td>" +
					"<td class='display'>" +
					"</td>" +
					"<td>" +
					"<input type='button' name='delete' value='删除' class='btn btn-danger'/>&nbsp;" +
					"<input type='button' name='change' value='修改' class='btn btn-info' data-toggle='modal' data-target='#uAlbum'/>&nbsp;" +
					"<input type='hidden' class='form-control' id='tags' value=" + element.tag + " />" +
					"<input type='hidden' class='form-control' id='maxImage' value=" + element.imgMaxUrl + " />" +
					"<input type='hidden' class='form-control' id='minImage' value=" + element.imgMinUrl + " />" +
					"<input type='hidden' class='form-control' id='summary' value=" + element.summary + " />" +
					"<input type='hidden' class='form-control' id='priority_value' value=" + element.priority + " />" +
					"<input type='hidden' class='form-control' id='display' value=" + element.display + " />" +
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

				if(element.display) {
					$("#listTable tr:last-child td").eq(10).html("展示中");
				} else {
					$("#listTable tr:last-child td").eq(10).html("<p style='color:#DCDCDC'>未展示</p>");
				}
			});

		},
		"error": function() {
			//alert("出现错误！");
		}
	});
}
/**
 * 删除操作
 */
function deleteAlbum() {

	$("[name='delete']").click(function() {
		var id = $(this).parent().parent().children("td").eq(1).text();
		//alert(22222);
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
						$.ajax({
							async: false,
							type: "get",
							url: "/album/deleteById",
							dataType: "json",
							data: {
								"id": id
							},
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
	});
}

/**
 * 根据推荐等级查询
 */
function findByPriority() {
	$("#priority").change(function() {
		var priority = $(this).find("option:selected").val();
		//alert(priority);
		if(priority == -1) {
			Init();
			return;
		}
		$.ajax({
			type: "get",
			url: "/album/findByPriority",
			dataType: "json",
			data: {
				"priority": priority
			},
			success: function(response) {
				//alert(response.code);
				if(response.code == "000000") {
					//alert(response.code);
					$("#listTable").children().remove();
					$.each(response.data, function(index, element) {
						var unixTimestamp = new Date(element.createTime);
						var commonTime = unixTimestamp.toLocaleString();
						var itemHTML = "<tr>" +
							"<td>" +
							"<input type='checkbox' name='item' value=" + element.id + ">" +
							"</td>" +
							"<td>" +
							element.id +
							"</td>" +
							"<td>" +
							element.name +
							"</td>" +
							"<td>" +
							element.authorName +
							"</td>" +
							"<td>" +
							"<a class='btn btn-primary' data-toggle='modal' data-target='#tagList'>" +
							"点击查看" +
							"</a>" +
							"<input type='hidden' class='form-control' id='tag' value=" + element.tag + " />" +

							"</td>" +
							"<td>" +
							"<select class='form-control' name='pri'>" +
							"<option value='0' >不推荐</option>" +
							"<option value='1' >类型推荐</option>" +
							"<option value='2' >置顶推荐</option>" +
							"</select>" +
							"</td>" +
							"<td>" +
							element.type +
							"</td>" +
							"<td>" +
							element.playCount +
							"</td>" +
							"<td>" +
							element.audioCount +
							"</td>" +
							"<td>" +
							commonTime +
							"</td>" +
							"<td class='display'>" +
							"</td>" +
							"<td>" +
							"<input type='button' name='delete' value='删除' class='btn btn-danger'/>&nbsp;" +
							"<input type='button' name='change' value='修改' class='btn btn-info' data-toggle='modal' data-target='#uAlbum'/>&nbsp;" +
							"<input type='hidden' class='form-control' id='tags' value=" + element.tag + " />" +
							"<input type='hidden' class='form-control' id='maxImage' value=" + element.imgMaxUrl + " />" +
							"<input type='hidden' class='form-control' id='minImage' value=" + element.imgMinUrl + " />" +
							"<input type='hidden' class='form-control' id='summary' value=" + element.summary + " />" +
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
						if(element.display) {
							$("#listTable tr:last-child td").eq(10).text("展示中");
						} else {
							$("#listTable tr:last-child td").eq(10).text("未展示");
						}
					});
				};
			}
		});
	});
}
/**
 * 修改
 */
function upAlbum() {
	/**
	 *输入验证 
	 */
	$("#defaultForm").validate({
		rules: {
			firstname: "required",
			lastname: "required",
			u_name: {
				required: true,
				minlength: 2
			},
			u_summary: {
				required: true,
				minlength: 2
			},
			u_authorName: {
				required: true,
				minlength: 2
			},
		},
		messages: {
			firstname: "请输入标题",
			title: {
				required: "请输入标题",
			},
			firstname: "请输入节目作者",
			authorName: {
				required: "请输入节目作者",
			},
		}
	});

	$("#upAlbum").click(function() {
		//var id = $(this).parent().parent().children("td").eq(1).text();
		//alert($("#u_id").val());
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
						var arr = $("#u_tag option:selected");
						var temp = new Array();
						for(var i = 0; i < arr.length; i++) {
							temp[i] = $(arr[i]).text();
						}
						var b = temp.join(",");
						var obj = {
							"id": $("#u_id").val(),
							"name": $("#u_name").val(),
							"title": $("#u_name").val(),
							"authorName": $("#u_author").val(),
							"authorId": 666,
							"type": $("#u_type option:selected").text(),
							"typeId": $("#u_type option:selected").val(),
							"tag": b,
							"display": $("input[name='u_display']:checked").val(),
							"imgMaxUrl": $("#file-3_src").val(),
							"imgMinUrl": $("#file-4_src").val(),
							"summary": $("#u_summary").val(),
							"playCount": $("#u_playCount").val(),
							"audioCount": $("#u_audioCount").val(),
							"priority": $("#priority_value").val()
						}
						$.ajax({
							async: false,
							type: "post",
							url: "/album/save",
							dataType: "json",
							data: obj,
							success: function(response) {
								//alert(response.code);
							}
						});
					}
				},
				close: function() {
					text: '取消'
				}
			}
		});
	});

}

function addAlbum() {
	/**
	 *输入验证 
	 */
	$("#addAlbumForm").validate({
		rules: {
			firstname: "required",
			lastname: "required",
			a_name: {
				required: true,
				minlength: 2,
			},
			a_author: {
				required: true,
				minlength: 2
			},
		},
		messages: {
			firstname: "请输入标题",
			a_name: {
				required: "请输入标题",
			},
			firstname: "请输入节目作者",
			a_author: {
				required: "请输入节目作者",
			},
		}
	});
	$("#addAlbum").click(function() {
		//var id = $(this).parent().parent().children("td").eq(1).text();
		//alert($("#u_id").val());
		//alert(22222);
		var arr = $("#a_tag option:selected");
		var temp = new Array();
		for(var i = 0; i < arr.length; i++) {
			temp[i] = $(arr[i]).text();
		}

		//alert($("#file2_src").val()+"11111111111src2");
		var b = temp.join(",");
		var obj = {
			"name": $("#a_name").val(),
			"title": $("#a_name").val(),
			"authorName": $("#a_author").val(),
			"authorId": 666,
			"type": $("#a_type option:selected").text(),
			"typeId": $("#a_type option:selected").val(),
			"tag": b,
			"display": $("input[name='a_display']:checked").val(),
			"imgMaxUrl": $("#file-1_src").val(),
			"imgMinUrl": $("#file2_src").val(),
			"summary": $("#a_summary").val(),
			"priority": 0,
			"playCount": 0,
			"audioCount": 0 
		}
		$.ajax({
			async: false,
			type: "post",
			url: "/album/save",
			dataType: "json",
			data: obj,
			success: function(response) {
				//alert(response.code);
			}
		});
	});
}