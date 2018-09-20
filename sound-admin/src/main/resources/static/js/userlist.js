/**
 * Created by Administrator on 2018/4/21.
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
    var size = 10;
    var b = false;
    var innertext = "";
    var typearr;
    var str;
    var boo = false;
    var boo1 = false;
    var boo2 = false;
    var boo3 = false;
    var booAll = false;
    var page = 0;
    var size = 5;
    var count = 0;
    var courrentpage = 1;
    /**
     * 删除操作
     */
    $("#delAll").click(function() {
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
                        for (var j = 0; j < items.length; j++) {
                            if (items[j].checked) {
                                var id = items[j].value;
                                $.ajax({
                                    "url": host+"/user/deleteById?id="+id,
                                    "type": "get",
                                    "data": "",
                                    "dataType": "json",
                                    "success": function (result) {

                                    },
                                    "error": function () {
                                        alert("出现错误！");
                                    }
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
    });
    function pageInit() {
        $.ajax({
            "url":host+"/user/findAll",
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
    /*
    页面初始化
     */
    function init(){
        pageInit();
    $.ajax({
         "url": host+"/user/findByPage?page="+page+"&size="+size,
        "type": "get",
        "data": "",
        "async":false,
        "dataType": "json",
        "success": function (result) {
            /*
            数据展示
             */
            $.each(result.data.content, function(index, element) {
                var itemHTML = "<tr>"+
                    "<td>"+
                    "<input type='checkbox' name='item' value="+element.id+">"+
                    "</td>"+
                    "<td>"+
                    element.userId+
                    "</td>"+
                    "<td>"+
                    element.name+
                    "</td>"+
                    "<td>"+
                    element.phoneNum+
                    "</td>"+
                    "<td>"+
                    new Date(element.createTime).toLocaleString()+
                    "</td>"+
                    "<td class='tdt'>"+
                    "<input type='hidden'  class='hid' value="+element.id+">"+
                    "<a name='selftype' class='btn btn-primary' data-toggle='modal' data-target='#selftype'>" +
                    "点击查看" +
                    "</a>"+
                    "</td>"+
                    "<td>"+
                    element.baiduAccount+
                    "</td>"+
                    "<td class='tdd'>"+
                    "<input type='hidden'  class='hid' value="+element.id+">"+
                    "<a name='device' class='btn btn-primary' data-toggle='modal' data-target='#device'>" +
                    "点击查看" +
                    "</a>"+
                    "</td>"+
                    "<td>"+
                    "<input type='hidden'  class='hid' value="+element.id+">"+
                    "<input type='button' name='delete' value='删除' class='btn btn-danger'/>"+
                    "</td>"+
                    "</tr>";
                $("#listTable").append(itemHTML);
            });
            $("#now").text(courrentpage);
            device();
            deleteuser();
            selfType();
        },
        "error": function () {
            alert("出现错误！");
        }
    });
    }
    init();
    /**
     *
     */
   function device() {
        $("a[name='device']").click(function(){
            var status = "待用！";
            var id = $(this).prev().val();
            $.ajax({
                "url": host+"/device/findByUserId?userId="+id,
                "type": "get",
                "data": "",
                "async":false,
                "dataType": "json",
                "success": function (result) {
                    if(result.data==null||result.data==""){
                        $("#devicelist").html("<p style='font-size: large;'>此用户暂无设备！</p>");
                        $("#showdevice").hide();
                        return;
                    }
                    $("#showdevice").show();
                    $("#devicelist").html("");
                    $.each(result.data, function(index, element) {

                        if(element.status){
                            status = "在用！";
                        }
                        var itemHTML = "<tr>"+
                            "<td>"+
                            "<input type='hidden'  class='hid' value="+element.deviceId+">"+
                            element.deviceName+
                            "</td>"+
                            "<td>"+
                            element.version+
                            "</td>"+
                            "<td>"+
                            status+
                            "</td>"+
                            /* "<td>"+
                             element.userId+
                             "</td>"+*/
                            "</tr>";
                        $("#devicelist").append(itemHTML);
                    });

                },
                "error": function () {
                    alert("出现错误！");
                }
            });
        });
    }
    /**
     * ids转换成names
     */
    function selfType() {
        $("a[name='selftype']").click(function(){
            innertext = ""
            $("#selftypes").text("");
            var id = $(this).prev().val();
            $.ajax({
                "url": host+"/userType/findByUserId?userId="+id,
                "type": "get",
                "data": "",
                "dataType": "json",
                "success": function (result) {
                    if(result.data.length==0){
                        $("#selftypes").text("暂无选择！");
                        return;
                    }
                    $.each(result.data,function (index, element) {
                        typearr = element.typeIds.split(",");
                        $.each(typearr,function (ind, ele) {
                            $.ajax({
                                "url": host+"/type/findById?id="+ele,
                                "type": "get",
                                "data": "",
                                "async":false,
                                "dataType": "json",
                                "success": function (c) {
                                    if(c.data==null){
                                        return;
                                    }
                                    innertext = innertext+"<li>"+c.data.name+"</li><hr>";
                                },
                                "error": function () {
                                    alert("出现错误！");
                                }
                            });
                        })

                    });
                    $("#selftypes").html(innertext);
                },
                "error": function () {
                    alert("出现错误！");
                }
            });
        });
    }
    /**
     * 删除操作
     */
        function deleteuser(){
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
                            //alert(333);
                            $.ajax({
                                "url": host+"/user/deleteById?id="+id,
                                "type": "get",
                                "data": "",
                                "dataType": "json",
                                "success": function (result) {
                                    alert(result.code);
                                    if(result.code=="00000"){
                                        c.remove();
                                    }
                                },
                                "error": function () {
                                    alert("error");
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



