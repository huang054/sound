/**
 * Created by Administrator on 2018/4/21.
 */
/**
 * 选中所有的栏目
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
    var boo = false;
    var boo1 = false;
    var boo2 = false;
    var boo3 = false;
    var booAll = false;
    var page = 0;
    var size = 5;
    var count = 0;
    var courrentpage = 1;
    var host = "";
    var json;
    /*
     页面初始化
     */
    function init(){
        pageInit();
        $.ajax({
            "url": host+"/column/findByPage?page="+page+"&size="+size,
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
                        element.colName+
                        "</td>"+
                        "<td>"+
                        "<input type='hidden'  class='hid' value="+element.id+">"+
                        "<input type='button' name='delete' value='删除' class='btn btn-danger'/>&nbsp;"+
                        "<input type='hidden'  class='hid' value="+element.colName+">"+
                        "<input type='button' name='change' value='修改' class='btn btn-info' data-toggle='modal' data-target='#mytypechange'/>&nbsp;"+
                        "</td>"+
                        "</<tr>";
                    $("#listTable").append(itemHTML);
                });
                $("#now").text(courrentpage);
                deletecolumn();
                updateinit();
                update();
            },
            "error": function () {
                alert("出现错误1！");
            }
        });
    }
    function pageInit() {
        $.ajax({
            "url":host+"/column/findAll",
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
    init();
    /**
     * 删除所有选中的栏目
     */
    $("#delall").click(function(){
        var listTable = $('#listTable');
        var boo = false;
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
                        var code;
                        for(var j=0;j<items.length;j++){
                            if(items[j].checked)
                            {
                                var id= items[j].value;
                                $.ajax({
                                    "url": host+"/column/deleteById?id="+id,
                                    "type": "get",
                                    "data": id,
                                    "dataType": "json",
                                    "success": function (result) {
                                        code = result.code;
                                        window.location.reload();
                                    },
                                    "error": function () {
                                        alert("err")
                                    }
                                });
                                //items[j].parentNode.parentNode.remove();
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
    /**
     * 删除当前的节目
     */
   function deletecolumn(){
        $("input[name='delete']").click(function () {
            var id = $(this).prev().val();
            id = parseInt(id);
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
                                "url": host+"/column/deleteById?id="+id,
                                "type": "get",
                                "data": "",
                                "dataType": "json",
                                "success": function (result) {
                                    if(result.code=="000000"){
                                        c.remove();
                                        window.location.reload();
                                    }
                                },
                                "error": function () {
                                    alert("出现错误3！");
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
     * 修改栏目的初始化
     */
   function updateinit(){
        $("input[name='change']").click(function () {
            var id = $(this).prev().prev().prev().val();
            var colName =  $(this).prev().val();
            $("#namec").val(colName);
            $("#idc").val(id);
            json = {
                "id":id,
                "colName":colName
            };
        });
    }
    /**
     * 进行对应栏目的修改
     */
   function update(){
        $("#change").click(function () {
            var colName = $("#namec").val();
            var id = $("#idc").val();
            json = {
                "id":id,
                "colName":colName
            };
            $.ajax({
                "url": host+"/column/save?id="+id+"&colName="+colName,
                "type": "get",
                "data": "",
                "dataType": "json",
                "success": function (result) {
                    window.location.reload();
                },
                "error": function () {
                    alert("error");
                }
            });
        });
    }
    /**
     * 添加栏目
     */
    $("#name").keyup(function () {
        var name = $("#name").val();
        if(name.trim().length==0){
            $("#column").text("栏目不能为空！");
            return;
        }else {
            $("#column").text("");
        }
    });
    $("#add").click(function () {
        var name = $("#name").val();
        if(name.trim().length==0){
            $("#column").text("栏目不能为空！");
            return;
        }
        json = {"colName":name};
        $.ajax({
            "url": host+"/column/save",
            "type": "get",
            "data": json,
            "dataType": "json",
            "success": function (result) {
                window.location.reload();
            },
            "error": function () {

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




