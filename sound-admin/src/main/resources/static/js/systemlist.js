/**
 * Created by Administrator on 2018/4/21.
 */
/**
 * 选中所有的系统消息
 * @param c
 */

function checkAll(c){
    var status = c.checked;
    var oItems = $("input[name='item']");
    for(var i=0;i<oItems.length;i++){
        oItems[i].checked=status;
    }
}
$(document).ready(function () {
    var host = "";
    var json;
    var boo = false;
    var boo1 = false;
    var boo2 = false;
    var boo3 = false;
    var booAll = false;
    var page = 0;
    var size = 5;
    var count = 0;
    var courrentpage = 1;
    /*
     页面初始化
     */
    //获得年月日      得到日期oTime
   function deleteAll() {
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
                                       "url": host+"/sysmsg/deleteById?id="+id,
                                       "type": "get",
                                       "data": "",
                                       "dataType": "json",
                                       "success": function(result) {
                                           if(result.code=="00000"){
                                               info = true;
                                           }
                                       },
                                       "error": function() {
                                           alert("出现错误");
                                       }
                                   });
                                   items[j].parentNode.parentNode.remove();
                               }
                           }
                           window.location.reload();
                       }
                   },
                   close: function() {
                       text:'取消'
                   }
               }
           });
       });
   }
    /**页面初始化，加载所有的系统消息
     * 网址，端口，以及data
     * @type {string}
     */
    function pageInit() {
        $.ajax({
            "url":host+"/sysmsg/findAll",
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
    function init(){
        pageInit();
        $.ajax({
            "url": host+"/sysmsg/findByPage?page="+page+"&size="+size,
            "type": "get",
            "data": "",
            "async":false,
            "dataType": "json",
            "success": function (result) {
                /*
                 数据展示
                 */
                $("#head").html("");
                $("#head").append("<td><input type=\"checkbox\" onclick=\"checkAll(this)\" /></td><td></td><td></td><td></td><td></td><td><a href='javascript:void(0)' class='btn btn-danger' role='button' id='deleteall'>全部删除</a>&nbsp;<button class='btn btn-primary' data-toggle='modal' data-target='#mysystem'>添加</button></td>");
                $.each(result.data.content, function(index, element) {
                    var info;
                    if(element.able){
                        info = "有效!";
                    }else{
                        info = "无效！";
                    }
                    var itemHTML = "<tr>"+
                        "<td>"+
                        "<input type='checkbox' name='item' value="+element.id+">"+
                        "</td>"+
                        "<td>"+
                        element.title+
                        "</td>"+
                        "<td>"+
                        element.content+
                        "</td>"+
                        "<td>"+
                        new Date(element.createTime).toLocaleString()+
                        "</td>"+
                        "<td>"+
                        info+
                        "</td>"+
                        "<td>"+
                        "<input type='hidden'  class='hid' value="+element.id+">"+
                        "<input type='button' name='delete' value='删除' class='btn btn-danger'/>&nbsp;"+
                        "</td>"+
                        "</<tr>";
                    $("#listTable").append(itemHTML);
                });
                $("#now").text(courrentpage);
                deleteStstemInfo();
                addSystenInfo();
                deleteAll();
            },
            "error": function () {
                alert("出现错误！");
            }
        });
    }
    init();
    /**
     * 删除当前的系统消息
     */
   function deleteStstemInfo() {
        $("input[name='delete']").click(function () {
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
                                "url": host+"/sysmsg/deleteById?id="+id,
                                "type": "get",
                                "data": "",
                                "dataType": "json",
                                "success": function (result) {
                                    if(result.code){}
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
     * 修改当前的系统消息
     */
    /*function updateStstemInfo() {
        $("input[name='change']").click(function () {
            alert("click");
            var id = $(this).prev().prev().val();
            var c = $(this).parent().parent();
            alert(id);
            $.ajax({
                "url": "./json/data.json",
                "type": "get",
                "data": id,
                "dataType": "json",
                "success": function (result) {
                    DisplayListItems(result);
                },
                "error": function () {
                    alert("出现错误！");
                }
            });
            function DisplayListItems(list) {
                alert(list.name);
                /!*$("#num").val(list.name);
                    $("#file-1").html(list.path);
                    $("#username").val(list.auther);
                    $("#pwd").val(list.titl);
                    $("#typ").val(list.type);
                    $("#addre").val(list.address);
                    $("#hid").show();*!/
            }
        });
    }*/
    /**
     * 系统消息上传
     */
   function addSystenInfo(){
        $("#file1").fileinput({
            language: 'zh', //设置语言
            uploadUrl: host+"/sysmsg/saveImage", //上传地址
            autoReplace: true,
            maxFileCount: 1, //表示允许同时上传的最大文件个数
            allowedFileExtensions: ["jpg"], //可接收的文件后缀["jpg", "png", "jpeg", "ico"]
            browseClass: "btn btn-primary", //按钮样式
            dropZoneEnabled: false, //是否显示拖拽区域
            showUpload: false,
            showRemove: true,
            showCaption: false,
            showPreview: true, //是否显示预览
            enctype: 'multipart/form-data', //上传的文件格式
           /* uploadExtraData: function(previewId, index) {
                json={
                    "title":$("#title").val(),
                    "content":$("#content").val(),
                    "isable":$("#isable").val(),
                    "imgurl":"123",
                    "imgName":"123"
                };

                return json;
            },*/
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>", //这个不要动
            layoutTemplates: { //这个控制预览图标
                actionUpload: "",
            }
        }).on("filebatchselected", function(e, data) { //异步上传返回结果处理
            $(this).fileinput("upload");
        }).on('fileuploaded', function(event, data, previewId, index) { //上传后
            //alert(data.response.data[1]);
            $("#filepath").val(data.response.data[0]);
            $("#filehead").val(data.response.data[1]);
        });
    }
    $("#user").keyup(function () {
        if($("#user").val()==null||$("#user").val().trim().length==0){
            $("#userv").text("指定用户不能为空!");
        }else {
            $("#userv").text("");
        }
    })
    $("#title").keyup(function () {
        if($("#title").val()==null||$("#title").val().trim().length==0){
            $("#titlev").text("消息标题不能为空!");
        }else {
            $("#titlev").text("");
        }
    })
    $("#content").keyup(function () {
        if($("#content").val()==null||$("#content").val().trim().length==0){
            $("#contentv").text("消息内容不能为空!");
        }else {
            $("#contentv").text("");
        }
    })
    $("#submitchange").click(function () {

        if($("#user").val()==null||$("#user").val().trim().length==0){
            $("#userv").text("指定用户不能为空!");
            boo = true;
        }else {
            boo = false;
            $("#userv").text("");
        }
        if($("#content").val()==null||$("#content").val().trim().length==0){
            $("#contentv").text("消息内容不能为空!");
            boo1 = true;
        }else {
            boo1 = false;
            $("#contentv").text("");
        }
        if($("#title").val()==null||$("#title").val().trim().length==0){
            $("#titlev").text("消息标题不能为空!");
            boo1 = true;
        }else {
            boo1 = false;
            $("#titlev").text("");
        }
       /* if(b==null||b.trim().length==0){
            $("#a_tagNull").text("权限必选!");
            boo2 = true;
        }else {
            boo2 = false;
            $("#a_tagNull").text("");
        }*/
        /*if($("#ext").val()==null||$("#ext").val().trim().length==0){
            $("#extNull").text("预留字段不能为空!");
            boo3 = true;
        }else {
            boo3 = false;
            $("#extNull").text("");
        }*/
        if(boo||boo1){
            return;
        }
        json={
            "title":$("#title").val(),
            "content":$("#content").val(),
            "isable":$("#isable").val(),
            "imgurl":$("#filepath").val(),
            "imgName":$("#filehead").val()
        };
        $.ajax({
            async: false,
            type: "post",
            url: host+"/sysmsg/save",
            dataType: "json",
            data: json,
            success: function(response) {
				window.location.reload();
            }
        });
    });
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
});



