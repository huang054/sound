/*
 * 
 */
$(function() {
	$(".menu-item").click(function() {
		$(".menu-item").removeClass("menu-item-active");
		$(this).addClass("menu-item-active");
		var itmeObj = $(".menu-item").find("img");
		itmeObj.each(function() {
			var items = $(this).attr("src");
			items = items.replace("_grey.png", ".png");
			items = items.replace(".png", "_grey.png");
			$(this).attr("src", items);
		});
		var attrObj = $(this).find("img").attr("src");
		attrObj = attrObj.replace("_grey.png", ".png");
		$(this).find("img").attr("src", attrObj);
	});
});

function changeFrameHeight() {
	var ifm = document.getElementById("fourIfm");
	ifm.height = document.documentElement.clientHeight;
}
window.onresize = function() {
	changeFrameHeight();
};

$(function() {
	$("#file-1").fileinput({
		uploadUrl: '',
		allowedFileExtensions: ['jpg', 'png', 'gif', 'txt', 'zip', 'ico', 'jpeg', 'js', 'css', 'java', 'mp3', 'mp4', 'doc', 'docx'], //允许的文件类型
		overwriteInitial: false,
		maxFileSize: 1500, //文件的最大大小 单位是k
		maxFilesNum: 10, //最多文件数量
		// allowedFileTypes: ['image', 'video', 'flash'],
		slugCallback: function(filename) {
			return filename;
		}
	});

	function uploadFile() { // upload file
		$("#file-1").fileinput({
			language: 'zh', //设置语言
			uploadUrl: "/type/image", //上传地址
			autoReplace: true,
			maxFileCount: 1, //表示允许同时上传的最大文件个数
			allowedFileExtensions: ["jpg", "png", "jpeg", "ico"], //可接收的文件后缀
			browseClass: "btn btn-primary", //按钮样式
			dropZoneEnabled: false, //是否显示拖拽区域
			showUpload:true,
			showRemov:true,
			showPreview: true, //是否显示预览
			enctype: 'multipart/form-data', //上传的文件格式
			uploadExtraData:function (previewId, index) {  
                var obj = {};  
                $('#addForm').find('input').each(function() {  
                    var id = $(this).attr('id'), val = $(this).val();  
                    obj[id] = val;  
                });  
                return obj;  
      },  
			previewFileIcon: "<i class='glyphicon glyphicon-king'></i>"
		}).on("fileuploaded", function(e, data) { //异步上传返回结果处理
			console.log(data);
			var res = data.response;
			alert(res.success);
			$("#logo").attr("value", res.success);
		}).on('filepreupload', function(event, data, previewId, index) { //上传前
			var form = data.form;
			var files = data.files;
			var extra = data.extra;
			var response = data.response;
			var reader = data.reader;
		});
	}
});