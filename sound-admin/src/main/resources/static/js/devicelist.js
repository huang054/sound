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
    var status;
    var id;
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
    $("#delAll").click(function(){
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
                                id = items[j].value;
                                alert(id);
                                $.ajax({
                                    "url": host+"/device/deleteById?id="+id,
                                    "type": "get",
                                    "data": "",
                                    "dataType": "json",
                                    "success": function (result) {
                                        alert(result.code);
                                    },
                                    "error": function () {
                                        alert("出现错误！");
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
    /*
     页面初始化
     */
    function init(){
        pageInit();
        $.ajax({
            "url":host+"/device/findByPage?page="+page+"&size="+size,
            "type": "get",
            "data": "",
            "async":false,
            "dataType": "json",
            "success": function (result) {
                /*
                 数据展示
                 */
                $.each(result.data.content, function(index, element) {
                    if(element.status){
                        status = "在线";
                    }else{
                        status = "离线";
                    }
                    var itemHTML = "<tr>"+
                        "<td>"+
                        "<input type='checkbox' name='item' value="+element.id+">"+
                        "</td>"+
                        "<td>"+
                        element.deviceName+
                        "</td>"+
                        "<td>"+
                        element.phoneNum+
                        "</td>"+
                        "<td>"+
                        element.deviceId+
                        "</td>"+
                        "<td>"+
                        status+
                        "</td>"+
                        "<td>"+
                        new Date(element.createTime).toLocaleString()+
                        "</td>"+
                        "<td>"+
                        element.version+
                        "</td>"+
                        "<td>"+
                        "<input type='hidden'  class='hid' value="+element.id+">"+
                        "<input type='button' name='delete' value='删除' class='btn btn-danger'/>"+
                        "</td>"+
                        "</<tr>";
                    $("#listTable").append(itemHTML);
                });
                $("#now").text(courrentpage);
                deletedevice();
            },
            "error": function () {
                alert("出现错误！");
            }
        });
    }
    init();
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

    /**
     * 删除操作
     */
    function deletedevice(){
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
                                "url": host+"/device/deleteById?id="+id,
                                "type": "get",
                                "data": id,
                                "dataType": "json",
                                "success": function (result) {
                                    alert(result.code);
                                    if(result.code=="00000") {
                                        c.remove();
                                        window.location.reload();
                                    }
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




