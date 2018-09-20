/**
 * Created by Administrator on 2018/4/21.
 */
/**
 * 选中所有的资讯
 * @param c
 */
function checkAll(c) {
	var status = c.checked;
	var oItems = $("input[name='item']");
	for(var i = 0; i < oItems.length; i++) {
		oItems[i].checked = status;
	}
}
/**
 * 选中所有的评论
 * @param c
 */
function checkAllpl(c){
	var status = c.checked;
	var oItems = $("input[name='itempl']");
	for(var i = 0; i < oItems.length; i++) {
		oItems[i].checked = status;
	}
};
$(document).ready(function() {
	var host = "";
	var json;
    var imageu;
    var imageNameu;
    var imagec;
    var imageNamec;
    var page = 0;
    var size = 5;
    var count = 0;
    var courrentpage = 1;
    var  P_page = 0;
    var  P_size = 5;
    var  P_count = 0;
    var  P_courrentpage = 1;
    var id;
    var boo = false;
    var boo1 = false;
    var boo2 = false;
    var boo3 = false;
    var boo4 = false;
    var boo5 = false;
    var boo6 = false;
    var booAll = false;
	/**
	 * 删除所有的评论
	 */
	var imgurl;
	function dellall() {
        $("#delAllpl").click(function() {
            var items = $("input[name='itempl']");
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
                                        "url": host+"/comment/deleteById?id="+id,
                                        "type": "get",
                                        "data": "",
                                        "async":false,
                                        "dataType": "json",
                                        "success": function(result) {
                                        },
                                        "error": function() {
                                            alert("出现错误");
                                        }
                                    });
                                    items[j].parentNode.parentNode.remove();
                                    window.location.reload();
                                }
                            }
                        }
                    },
                    close: function() {
                        text:'取消'
                    }
                }
            });
            /* window.location.reload();*/
        });
    }
	/*
	*删除多条资讯
 	*/
	function deleteAllPl() {
        $("#deleteall").click(function(){
            var listTable = $('#listTable');
            var items = $("input[name='item']");
            var info;
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
                                        "url": host+"/news/deleteById?id="+id,
                                        "type": "get",
                                        "data": "",
                                        "async":false,
                                        "dataType": "json",
                                        "success": function(result) {
                                            if(result.code=="00000"){
                                                info = true;
                                            }else{
                                                info = false;
                                            }
                                        },
                                        "error": function() {
                                            alert("出现错误");
                                        }
                                    });
                                    window.location.reload();
                                }
                            }
                        }
                    },
                    close: function() {
                        text:'取消'
                    }
                }
            });
            /* window.location.reload();*/
        });
    }
	/*
	 页面初始化
	 */
	function init() {
        pageInit();
		$.ajax({
			"url": host+"/news/findByPage?page="+page+"&size="+size,
			"type": "get",
			"data": "",
			"async": false,
			"dataType": "json",
			"success": function(result) {
				/*
				 数据展示
				 */
                $("#head").html("");
                $("#head").append("<td><input type='checkbox' onclick='checkAll(this)' /></td><td colspan='7'></td><td><a href='javascript:void(0)' class='btn btn-danger' role='button' id='deleteall'>全部删除</a>&nbsp;<button id='showadd' class='btn btn-primary' data-toggle='modal' data-target='#mynews'>添加</button></td>");
                $.each(result.data.content, function(index, element) {
					var itemHTML = "<tr>" +
						"<td>" +
						"<input type='checkbox' name='item' value=" + element.id + ">" +
						"</td>" +
						"<td>" +
						element.title +
						"</td>" +
						"<td>" +
						element.content.substring(0,10)+"..." +
						"</td>" +
						"<td>" +
						element.summary.substring(0,5)+"..." +
						"</td>" +
						"<td>" +
						element.readCount +
						"</td>" +
						"<td>" +
						element.praiseCount +
						"</td>" +
						"<td>" +
						new Date(element.createTime).toLocaleString() +
						"</td>" +
						"<td>" +
						element.commentCount +
						"</td>" +
						"<td>" +
						"<input type='hidden'  class='hid' value=" + element.coverURL + ">" +
						"<input type='hidden'  class='hid' value=" + element.id + ">" +
						"<input type='button' name='delete' value='删除' class='btn btn-danger'/>&nbsp;" +
						"<input type='button' name='change' value='修改' class='btn btn-info' data-toggle='modal' data-target='#mynewschange'/>&nbsp;" +
						"<input type='hidden'  class='hid' value=" + element.id + ">" +
						"<input type='button' name='show' value='查看评论' class='btn btn-info' data-toggle='modal' data-target='#comments'/>" +
						"</td>" +
						"</<tr>";
					$("#listTable").append(itemHTML);
				});
                $("#now").text(courrentpage);
                deleteOne();
                initNewsPl();
                deleteAllPl();
                changeInit();
			},
			"error": function() {
				alert("出现错误！");
			}
		});
		$("input[name='show']").click(function () {
        id = $(this).prev().val();
        $("#hidden").val(id);
        initNewsPl();
    });
	}
	init();
    function pageInit() {
        $.ajax({
            "url":host+"/news/findAll",
            "type": "get",
            "data": "",
            "async":false,
            "dataType": "json",
            "success": function (result) {
                count = result.data.length;
                /*alert(Math.floor(count/size))
                alert((count%size>0?1:0))*/
                $("#nextPageval").val(Math.floor(count/size)+(count%size>0?1:0));
            }
        });
    }
	/**
	 * 资讯上传
	 */
	$("#file1").fileinput({
		language: 'zh', //设置语言
		uploadUrl: host+"/news/saveImage", //上传地址
		autoReplace: true,
		maxFileCount: 1, //表示允许同时上传的最大文件个数
		allowedFileExtensions: ["jpg"], //可接收的文件后缀
		browseClass: "btn btn-primary", //按钮样式
		dropZoneEnabled: false, //是否显示拖拽区域
		showUpload: false,
		showRemove: true,
		showCaption: false,
		showPreview: true, //是否显示预览
		enctype: 'multipart/form-data', //上传的文件格式
		maxFileSize: 599,
		/*uploadExtraData: function(previewId, index) {
		 var obj = {
			"title":$("#title").val(),
			 "content":$("#content").val(),
			 "author":$("#author").val(),
			 "summary":$("#summary").val(),
			 "columnName":$("#columnName").val(),
			 "contentId":"1",
			 "cloId":1,
			 "readCount":0,
			 "praiseCount":0,
			 "commentCount":0,
			 "ticket":"123",
			 "coverName":"qwe",
			 "coverURL":"123",
			 "tag":"1,2,3,4"
		 };
		 return obj;
		 },*/
		previewFileIcon: "<i class='glyphicon glyphicon-king'></i>", //这个不要动
		layoutTemplates: { //这个控制预览图标
			actionUpload: "",
		},
		/*initialPreview: "<img style='width:213px;height: 131px' src='' class='file-preview-image'/>" // IMAGE RAW MARKUP*/
	}).on("filebatchselected", function(event, files) { //异步上传返回结果处理
        $(this).fileinput("upload");
    }).on("fileuploaded", function(e, data) { //异步上传返回结果处理
        imageu = data.response.data[0];
        imageNameu = data.response.data[1];
        $("#filepath").val(data.response.data[0]);
        $("#filehead").val(data.response.data[1]);
    });
	/*$("#submit").click(function () {
       json={
           "title":$("#title").val(),
           "content":$("#content").val(),
           "author":$("#author").val(),
           "summary":$("#summary").val(),
           "columnName":$("#columnName").val(),
           "contentId":"1",
           "cloId":1,
           "readCount":0,
           "praiseCount":0,
           "commentCount":0,
           "ticket":"123",
           "coverName":"qwe",
           "coverURL":"123",
           "tag":"1,2,3,4"
       }
    });*/
    /**
     * 标签初始化
     */
    $("#showadd").click(function () {
        upTagInit();
        upColumnInit();
    });
	function upTagInit() {
	//alert(2222);
	$.ajax({
		async: false,
		type: "get",
		url: host+"/tag/findAll",
		dataType: "json",
		success: function(response) {
			if(response.code == "000000") {
				var dd1 = $("#u_tag1"); //列表框id
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
			$("#u_tag1").selectpicker('refresh');
			//alert($("#u_tag option").length);
		}
	});
}

    /**
     * 栏目初始化
     */
    function upColumnInit() {
        //alert(2222);
        $.ajax({
            async: false,
            type: "get",
            url: host+"/column/findAll",
            dataType: "json",
            success: function(response) {
                if(response.code == "000000") {
                    var dd1 = $("#u_tag2"); //列表框id
                    dd1.empty();
                    //方法1：添加默认节点
                    dd1.append("<option value='-1' disabled='true'>--请选择--</option>");
                    //循环遍历 下拉框绑定
                    $.each(response.data, function(index, value) {
                        //第一种方法
                        var opt = "<option value='" + value.id + "'>" + value.colName + "</option>";
                        dd1.append(opt);
                    });
                }
                $("#u_tag2").selectpicker('refresh');
                //alert($("#u_tag option").length);
            }
        });
    }

    /**
     * 修改标签初始化
     */
    function cTagInit() {
        //alert(2222);
        $.ajax({
            async: false,
            type: "get",
            url: host+"/tag/findAll",
            dataType: "json",
            success: function(response) {
                if(response.code == "000000") {
                    var dd1 = $("#c_tag1"); //列表框id
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
                $("#c_tag1").selectpicker('refresh');
                //alert($("#u_tag option").length);
            }
        });
    }

    /**
     * 栏目修改初始化
     */
    function cColumnInit() {
        //alert(2222);
        $.ajax({
            async: false,
            type: "get",
            url: host+"/column/findAll",
            dataType: "json",
            success: function(response) {
                if(response.code == "000000") {
                    var dd1 = $("#c_tag2"); //列表框id
                    dd1.empty();
                    //方法1：添加默认节点
                    dd1.append("<option value='-1' disabled='true'>--请选择--</option>");
                    //循环遍历 下拉框绑定
                    $.each(response.data, function(index, value) {
                        //第一种方法
                        var opt = "<option value='" + value.id + "'>" + value.colName + "</option>";
                        dd1.append(opt);
                    });
                }
                $("#c_tag2").selectpicker('refresh');
                //alert($("#u_tag option").length);
            }
        });
    }
	/*
	资讯更改
	 */
	$("#file1c").fileinput('destroy');
	$("#file1c").fileinput({
		language: 'zh', //设置语言
		uploadUrl: host+"/news/saveImage", //上传地址
		autoReplace: true,
		maxFileCount: 1, //表示允许同时上传的最大文件个数
		allowedFileExtensions: ["jpg"], //可接收的文件后缀["jpg", "png", "jpeg", "ico"]
		browseClass: "btn btn-primary", //按钮样式
		dropZoneEnabled: false, //是否显示拖拽区域
		minImageWidth: 50, //图片的最小宽度
		minImageHeight: 50,//图片的最小高度
		maxImageWidth: 300,//图片的最大宽度
		maxImageHeight: 130,//图片的最大高度
		showUpload: false,
		showRemove: true,
        showSize:true,
		showCaption: false,
		showPreview: true, //是否显示预览
		maxFileSize:150,	//上传图片的最大kb
		enctype: 'multipart/form-data', //上传的文件格式
		/*uploadExtraData: function(previewId, index) {
			var obj = {
				"id":$("#id").val(),
				"title":$("#titlec").val(),
				"content":$("#contentc").val(),
				"author":$("#authorc").val(),
				"summary":$("#summaryc").val(),
				"columnName":$("#columnNamec").val(),
				"contentId":"1",
				"cloId":1,
				"ticket":"123",
				"coverName":"qwe",
				"coverURL":"123",
				"tag":"1,2,3,4"
			};
			return obj;
		},
		previewFileIcon: "<i class='glyphicon glyphicon-king'></i>", //这个不要动
		layoutTemplates: { //这个控制预览图标
			actionUpload: "",
		},*/
		initialPreview: "<img style='width:213px;height: 131px' src='' class='file-preview-image'/>" // IMAGE RAW MARKUP
	}).on("filebatchselected", function(event, files) { //异步上传返回结果处理
        $(this).fileinput("upload");
        $("#file1c").fileinput("dest");
    }).on("fileuploaded", function(e, data) { //异步上传返回结果处理
        imagec = data.response.data[0];
        imageNamec = data.response.data[1];
        $("#filepathc").val(data.response.data[0]);
        $("#fileheadc").val(data.response.data[1]);
    });
	/*
	*删除一条资讯
	 */
	function deleteOne(){
        $("input[name='delete']").click(function() {
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
                                "url": host+"/news/deleteById?id="+id,
                                "type": "get",
                                "async":false,
                                "data": "",
                                "dataType": "json",
                                "success": function (result) {
                                    c.remove();
                                    window.location.reload();
                                },
                                "error": function () {
                                    alert("出现错误！");
                                }
                            });
                        }
                    },
                    close: function() {
                        text:'取消'
                    }
                }
            });
        });
	}
	/**
	 * 对应资讯的评论填充
	 */
	function newsPlinit() {
        $.ajax({
            "url": host + "/comment/findByNewsId",
            "type": "get",
            "async": false,
            "data": {
                "newsId":id
            },
            "dataType": "json",
            "success": function (result) {
                P_count = result.data.length;
                $("#P_nextPageval").val(Math.floor(P_count / P_size) + (P_count % P_size > 0 ? 1 : 0));
            },
            "error": function () {
                alert("出现错误！");
            }
        });
    }
    
	function initNewsPl(){
        newsPlinit();
            $.ajax({
                "url": host+"/news/findByPageAndNewsId",
                "type": "get",
                "async":false,
                "data": {
                    "page":P_page,
                    "size":P_size,
                    "newsId":$("#hidden").val()
                },
                "dataType": "json",
                "success": function (result) {
                    DisplayListItems(result);
                },
                "error": function () {
                    alert("出现错误！");
                }
            });
            function DisplayListItems(result) {
                $("#selectpl").html("");
                $("#selectpl").append("<tr><th>选中</th><th>评论作者</th><th>评论详情</th><th>评论时间</th></tr><tr><td><input type='checkbox' onclick='checkAllpl(this)'/></td><td></td><td></td><td><a href='javascript:void(0)' class='btn btn-danger' role='button' id='delAllpl'>删除选中</a></td></tr>\n");
                /*$
                            $("#mytablep").html("<tr> <th>选中</th> <th>评论作者</th> <th>评论详情</th> <th>评论时间</th> <th>操作</th> </tr> <tr> <td><input type='checkbox'/> </td><td></td><td></td><td></td><td><a href='javascript:void(0)' class='btn btn-danger' role='button' id='delAllpl'>全部删除</a></td></tr>");
                */			$("#mytablep").html("");
                if(result==null){
                    return;
                }
                 $("#mytablep").html("");
                console.log(result);
                $.each(result, function(index, element) {
                    var itemHTML = "<tr>"+
                        "<td>" +
                        "<input type='checkbox' name='itempl' value=" + element[0] + ">" +
                        "</td>" +
                        "<td>" +
                        element[4] +
                        "</td>" +
                        "<td>" +
                        element[1] +
                        "</td>" +
                        "<td>" +
                        new Date(element[2]).toLocaleString() +
                        "</td>" +
                        /*"<td>" +
                        element.ticket +
                        "</td>" +
                        "<td>" +
                        element.userId +
                        "</td>" +
                        "<td>" +
                        element.newsId +
                        "</td>" +
                        "<td>" +
                        element.headerUrl +
                        "</td>" +*/
                        /*"<td>" +
                    "<input type='button' name='change' value='修改' class='btn btn-info' data-toggle='modal' data-target='#mynewschange'/>&nbsp;" +
                    "<input type='hidden'  class='hid' value=" + element.id + ">" +
                    "<input type='button' name='show' value='查看评论' class='btn btn-info' data-toggle='modal' data-target='#mycontent'/>" +
                    "</td>" +*/
                        "</tr>";
                    $("#mytablep").append(itemHTML);
                });
                $("#P_now").text(P_courrentpage);
                dellall();
            }
	}
    $("#title").keyup(function () {
        if($("#title").val()==null||$("#title").val().trim().length==0){
            $("#titlev").text("资讯标题不能为空!");
        }else {
            $("#titlev").text("");
        }
    })
    $("#content").keyup(function () {
        if($("#content").val()==null||$("#content").val().trim().length==0){
            $("#contentv").text("资讯内容不能为空!");
        }else {
            $("#contentv").text("");
        }
    })
    $("#author").keyup(function () {
        if($("#author").val()==null||$("#author").val().trim().length==0){
            $("#authorv").text("作者必填!");
        }else {
            $("#authorv").text("");
        }
    })
    /*$("#u_tag1").keyup(function () {
        if($("#userName").val()==null||$("#u_tag1").val().trim().length==0){
            $("#u_tag1v").text("所属标签不能为空!");
        }else {
            $("#u_tag1v").text("");
        }
    })
    $("#u_tag2").keyup(function () {
        if($("#u_tag2").val()==null||$("#u_tag2").val().trim().length==0){
            $("#u_tag2v").text("所属栏目不能为空!");
        }else {
            $("#u_tag2v").text("");
        }
    })*/
    $("#summary").keyup(function () {
        if($("#summary").val()==null||$("#summary").val().trim().length==0){
            $("#summaryv").text("摘要不能为空!");
        }else {
            $("#summaryv").text("");
        }
    })
    $("#file1").keyup(function () {
        if($("#file1").val()==null||$("#file1").val().trim().length==0){
            $("#file1v").text("资讯封面不能为空!");
        }else {
            $("#file1v").text("");
        }
    })
    /**
     * 添加资讯
     */
    $("#submit").click(function () {
        if($("#title").val()==null||$("#title").val().trim().length==0){
            $("#titlev").text("资讯标题不能为空!");
            boo = true;
        }else {
            boo = false;
            $("#titlev").text("");
        }
        if($("#content").val()==null||$("#content").val().trim().length==0){
            $("#contentv").text("资讯内容不能为空!");
            boo1 = true;
        }else {
            boo1 = false;
            $("#contentv").text("");
        }
        if($("#author").val()==null||$("#author").val().trim().length==0){
            $("#authorv").text("作者必填!");
            boo2 = true;
        }else {
            boo2 = false;
            $("#authorv").text("");
        }
       /* if($("#u_tag1").val()==null||$("#u_tag1").val().trim().length==0){
            $("#u_tag1v").text("所属标签不能为空!");
            boo3 = true;
        }else {
            boo3 = false;
            $("#u_tag1v").text("");
        }
        if($("#u_tag2").val()==null||$("#u_tag2").val().trim().length==0){
            $("#u_tag2v").text("所属栏目不能为空!");
            boo4 = true;
        }else {
            boo4 = false;
            $("#u_tag2v").text("");
        }*/
        if($("#summary").val()==null||$("#summary").val().trim().length==0){
            $("#summaryv").text("摘要不能为空!");
            boo5 = true;
        }else {
            boo5 = false;
            $("#summaryv").text("");
        }

        /*if($("#file1").val()==null||$("#file1").val().trim().length==0){
            $("#file1v").text("资讯封面不能为空!");
            boo6 = true;
        }else {
            boo6 = false;
            $("#file1v").text("");
        }*/
        if(boo||boo3||boo2||boo1||boo4||boo5){
            return;
        }

        var arr1 = $("#u_tag1 option:selected");
        var temp1 = new Array();

        for(var i = 0; i < arr1.length; i++) {
            temp1[i] = $(arr1[i]).val();
        }
        var tagids = temp1.join(",");

        var arr2 = $("#u_tag2 option:selected");
        var temp2 = new Array();
        var temp3 = new Array();
        for(var j = 0; j < arr2.length; j++) {
            temp2[j] = $(arr2[j]).text();
            temp3[j] = $(arr2[j]).val();
        }
        var columnNames = temp2.join(",");
        var cloIds = temp3.join(",");
        //alert(columnNames+"/"+cloIds);
        json={
            "title":$("#title").val(),
            "content":$("#content").val(),
            "authorName":$("#author").val(),
            "authorId":"1",
            "author":"jim",
            "summary":$("#summary").val(),
            "columnName":columnNames,
            "cloId":cloIds,
            "contentId":0,
            "readCount":0,
            "praiseCount":0,
            "commentCount":0,
            "ticket":0,
            "coverName":$("#filehead").val(),
            "coverURL":$("#filepath").val(),
            "tag":tagids
        };
        $.ajax({
            "url": host+"/news/save",
            "type": "post",
            "async":false,
            "data": json,
            "dataType": "json",
            "success": function (result) {
                console.log(result);
                window.location.reload();
            },
            "error": function () {
                alert("出现错误！");
            }
        })
    });
    /**
     * 修改资讯
     */
    $("#submitchange").click(function () {
        var arr1 = $("#c_tag1 option:selected");
        var temp1 = new Array();

        for(var i = 0; i < arr1.length; i++) {
            temp1[i] = $(arr1[i]).val();
        }
        var tagids = temp1.join(",");

        var arr2 = $("#c_tag2 option:selected");
        var temp2 = new Array();
        var temp3 = new Array();
        for(var j = 0; j < arr2.length; j++) {
            temp2[j] = $(arr2[j]).text();
            temp3[j] = $(arr2[j]).val();
        }
        var columnNames = temp2.join(",");
        var cloIds = temp3.join(",");
        //alert(columnNames+"/"+cloIds);
        json={
            "id":$("#id").val(),
            "title":$("#titlec").val(),
            "content":$("#contentc").val(),
            "authorName":$("#authorc").val(),
            "authorId":"1",
            "author":"jim",
            "summary":$("#summaryc").val(),
            "columnName":columnNames,
            "cloId":cloIds,
            "contentId":0,
            "readCount":0,
            "praiseCount":0,
            "commentCount":0,
            "ticket":0,
            "coverName":$("#fileheadc").val(),
            "coverURL":$("#filepathc").val(),
            "tag":tagids
        };
        $.ajax({
            "url": host+"/news/save",
            "type": "post",
            "async":false,
            "data": json,
            "dataType": "json",
            "success": function (result) {
                console.log(result);
               window.location.reload();
            },
            "error": function () {
                alert("出现错误！");
            }
        })
    });
	/**
	 * 修改资讯数据填充
	 */
	function changeInit(){
        var ddl=$("#c_tag1");
        var dd2=$("#c_tag2");
        $("input[name='change']").click(function() {
            cTagInit();
            cColumnInit();
            var id = $(this).next().val();
            $.ajax({
                "url": host+"/news/findById?id="+id,
                "type": "get",
                "async":false,
                "data": "",
                "dataType": "json",
                "success": function (result) {
                    DisplayListItems(result);
                },
                "error": function () {
                    ("出现错误！");
                }
            });
            function DisplayListItems(result) {
                var menu1 = result.data.tag;
                if(menu1 != null) {
                    var menus = menu1.split(",");
                    console.log(result);
                    var all_select = $("#c_tag1 > option");
                    $("#c_tag1").selectpicker('val', menus);
                }
                var menu2 = result.data.cloId;
                if(menu2 != null) {
                    var all_select = $("#c_tag1 > option");
                    $("#c_tag2").selectpicker('val', menu2);
                }
                $("#id").val(result.data.id);
                $("#titlec").val(result.data.title);
                $("#contentc").val(result.data.content);
                $("#authorc").val(result.data.authorName);
                $("#summaryc").val(result.data.summary);
                $("#fileheadc").val(result.data.coverName);
                $("#filepathc").val(result.data.coverURL);
                $("img").attr("src", "http://p81lqfddq.bkt.clouddn.com/"+result.data.coverURL);
            }
        });
	}
    $("#homePage").click(function () {
        $("#listTable").html("");
        page=0;
        courrentpage = 1;
        init();
    });
    $("#preciousPage").click(function () {
        var nowpage = parseInt($("#now").text());
        if(nowpage<=1){
            return;
        }else{
            page = nowpage-2;
        }
        courrentpage = nowpage-1;
        $("#listTable").html("");
        init();
    });
    $("#nextPage").click(function () {
        var nowpage = parseInt($("#now").text());
        if(nowpage>=$("#nextPageval").val()){
            return;
        }else{
            page = nowpage;
        }
        courrentpage = nowpage+1;
        $("#listTable").html("");
        init();
    });
    $("#trailerPage").click(function () {
        $("#listTable").html("");
        page=$("#nextPageval").val()-1;
        courrentpage=$("#nextPageval").val();
        init();
    });
    $("#P_homePage").click(function () {
        $("#mytablep").html("");
        P_page=0;
        P_courrentpage = 1;
        initNewsPl();
    });
    $("#P_preciousPage").click(function () {
        var P_nowpage = parseInt($("#P_now").text());
        if(P_nowpage<=1){
            return;
        }else{
            P_page = P_nowpage-2;
        }
        P_courrentpage = P_nowpage-1;
        $("#mytablep").html("");
        initNewsPl();
    });
    $("#P_nextPage").click(function () {
        var P_nowpage = parseInt($("#P_now").text());
        //alert(P_nowpage+"/"+$("#P_nextPageval").val())
        if(P_nowpage>=$("#P_nextPageval").val()){
            return;
        }else{
            P_page = P_nowpage;
        }
        P_courrentpage = P_nowpage+1;
        $("#mytablep").html("");
        initNewsPl();
    });
    $("#P_trailerPage").click(function () {
        $("#mytablep").html("");
        P_page=$("#P_nextPageval").val()-1;
        P_courrentpage=$("#P_nextPageval").val();
        initNewsPl();
    });
});