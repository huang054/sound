/**
 * Created by Administrator on 2018/4/21.
 */
/**
 * 选中所有
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
    /**
     * 端口以及data
     * @type {string}
     */
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
    function pageInit() {
        $.ajax({
            "url":host+"/admin/findAll",
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
            "url":host+"/admin/findByPage?page="+page+"&size="+size,
            "type": "get",
            "data": "",
            "async":false,
            "dataType": "json",
            "success": function (result) {
                /*
                 数据展示
                 */
                $("#head").html("");
                $("#head").append("<td><input type=\"checkbox\" onclick=\"checkAll(this)\" /></td><td></td><td></td><td></td><td></td><td></td><td></td><td><a href='javascript:void(0)' class='btn btn-danger' role='button' id='deleteall'>全部删除</a>&nbsp;<button class='btn btn-primary' data-toggle='modal' data-target='#myadmin'>添加</button></td>");
                $.each(result.data.content, function(index, element) {
                    var lastip;
                    if(element.lastIp==null||element.lastIp==""){
                        lastip = "暂无记录!";
                    }else {
                        lastip = element.lastIp;
                    }
                    var itemHTML = "<tr>"+
                        "<td>"+
                        "<input type='checkbox' name='item' value="+element.id+">"+
                        "</td>"+
                        "<td>"+
                        element.userName+
                        "</td>"+
                        "<td>"+
                        element.passwd+
                        "</td>"+
                        "<td>"+
                        new Date(element.lastTime).toLocaleString()+
                        "</td>"+
                        "<td>"+
                        element.privilege+

/*
                        element.privilege.replace("/audio","音频管理").replace("/admin","后台用户管理").replace("/sysmsg","系统消息管理").replace("/tag","标签管理").replace("/type","类型管理").replace("/user","用户管理").replace("/recommend","系统推荐管理").replace("/news","资讯管理").replace("/menu","菜单管理").replace("/upload","文件上传管理").replace("/device","系统设备管理").replace("/comment","评论管理").replace("/collect","收藏管理").replace("/column","栏目管理").replace("/bigType","大类型管理").replace("/album","专辑管理")+
*/

                        "</td>"+
                        "<td>"+
                        lastip+
                        "</td>"+
                        "<td>"+
                        element.ext+"|"+element.ext1+
                        "</td>"+
                        "<td>"+
                        "<input type='hidden'  class='hid' value="+element.lastIp+">"+
                        "<input type='hidden'  class='hid' value="+element.id+">"+
                        "<input type='button' name='delete' value='删除' class='btn btn-danger'/>&nbsp;"+
                        "<input type='button' name='change' value='修改' class='btn btn-info' data-toggle='modal' data-target='#myadminu'/>"+
                        "</td>"+
                        "</<tr>";
                    $("#listTable").append(itemHTML);
                });
                $("#now").text(courrentpage);
                initselect();
                deleteadmin();
                dellalladmin();
                updateAdmin();
            },
            "error": function () {
                //alert("出现错误！");
            }
        });
    }
    init();
    add();
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
    /**
     * 删除相应的admin
     */
    function deleteadmin(){
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
                                "url": host+"/admin/deleteById?id="+id,
                                "type": "get",
                                "data": '',
                                "dataType": "json",
                                "success": function (result) {
                                    c.remove();
                                    window.location.reload();
                                },
                                "error": function () {
                                    //alert("出现错误！");
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
     * 一键删除所有选中的admin
     */
    function dellalladmin() {
        $("#deleteall").click(function(){
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
                            for (var j = 0; j < items.length; j++) {
                                if (items[j].checked) {
                                    var id = items[j].value;
                                    $.ajax({
                                        "url": host+"/admin/deleteById?id="+id,
                                        "type": "get",
                                        "data": "",
                                        "dataType": "json",
                                        "success": function (result) {
                                            code = result.code;
                                        },
                                        "error": function () {
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
                        text:'取消'
                    }
                }
            });
        });
    }
    /**
     * 更新时数据的填充
     */
   function updateinit(){
        $("input[name='change']").click(function () {
            upTagInit();
            var id = $(this).prev().prev().val();
            $.ajax({
                "url": host+"/admin/findById?id="+id,
                "type": "get",
                "data": "",
                "dataType": "json",
                "success": function (result) {
                    DisplayListItems(result.data);
                },
                "error": function () {
                    //alert("出现错误！");
                }
            });
            function DisplayListItems(result) {
                var lastip;
                if(result.lastIp==null||result.lastIp==""){
                    lastip = "暂无记录!";
                }else {
                    lastip = result.lastIp;
                }
                var menu = result.privilege;
                if(menu != null) {
                    //menu = menu.replace("音频管理","1").replace("后台用户管理","2").replace("系统消息管理","3").replace("标签管理","4").replace("用户管理","6").replace("系统推荐管理","7").replace("资讯管理","8").replace("菜单管理","9").replace("文件上传管理","10").replace("系统设备管理","11").replace("评论管理","12").replace("收藏管理","13").replace("栏目管理","14").replace("大类型管理","15").replace("专辑管理","16").replace("类型管理","5");
                    var menus = menu.split(",");
                    var all_select = $("#u_tag > option");
                    $("#u_tag").selectpicker('val', menus);
                }

                $("#id").val(result.id);
                $("#userNameu").val(result.userName);
                $("#passwdu").val(result.passwd);
                $("#typeu").val(result.type);
                $("#lastTimeu").val(result.lastTime);
                /* $("#u_tag").val(result.privilege);*/
                $("#lastIpu").val(lastip);
                $("#extu").val(result.ext);
                $("#ext1u").val(result.ext1);
            }
        });
    }
    /**
     * admin添加操作
     */
    function add(){
        $("#userName").keyup(function () {
            if($("#userName").val()==null||$("#userName").val().trim().length==0){
                $("#userNameNull").text("用户名不能为空!");
            }else {
                $("#userNameNull").text("");
            }
        })
        $("#passwd").keyup(function () {
            if($("#passwd").val()==null||$("#passwd").val().trim().length==0){
                $("#passwdNull").text("密码不能为空!");
            }else {
                $("#passwdNull").text("");
            }
        })
        $("#ext").keyup(function () {
            if($("#ext").val()==null||$("#ext").val().trim().length==0){
                $("#extNull").text("预留字段不能为空!");
            }else {
                $("#extNull").text("");
            }
        })
        $("#add").click(function () {
            var arr = $("#a_tag option:selected");
            var temp = new Array();
            for(var i = 0; i < arr.length; i++) {
                temp[i] = $(arr[i]).val();
            }
            var b = temp.join(",");
            if($("#userName").val()==null||$("#userName").val().trim().length==0){
                $("#userNameNull").text("用户名不能为空!");
                boo = true;
            }else {
                boo = false;
                $("#userNameNull").text("");
            }
            if($("#passwd").val()==null||$("#passwd").val().trim().length==0){
                $("#passwdNull").text("密码不能为空!");
                boo1 = true;
            }else {
                boo1 = false;
                $("#passwdNull").text("");
            }
            if(b==null||b.trim().length==0){
                $("#a_tagNull").text("权限必选!");
                boo2 = true;
            }else {
                boo2 = false;
                $("#a_tagNull").text("");
            }
            if($("#ext").val()==null||$("#ext").val().trim().length==0){
                $("#extNull").text("预留字段不能为空!");
                boo3 = true;
            }else {
                boo3 = false;
                $("#extNull").text("");
            }
            if(boo||boo3||boo2||boo1){
                return;
            }
            json = {
                userName:$("#userName").val(),
                passwd:$("#passwd").val(),
                lastTime:$("#lastTime").val(),
                privilege:b,
                lastIp:$("#lastIp").val(),
                ext:$("#ext").val(),
                ext1:$("#ext1").val()
            };
            $.ajax({
                "url": host+"/admin/save",
                "type": "get",
                "data": json,
                "dataType": "json",
                "success": function (result) {
                    window.location.reload();
                },
                "error": function () {
                    //alert("出现错误！");
                }
            });
        });
    }
    /**
     * 进行管理员数据更新
     */
    function updateAdmin(){
        updateinit();
        $("#update").click(function () {
            /*var privileges=$("#privileges").val();
            var items = $("input[name='itemsu']");
            for (var j = 0; j < items.length; j++) {
                if (items[j].checked) {
                    if(privileges.length>0){
                        privileges = privileges+",";
                    }
                    privileges = privileges+"/"+items[j].nextSibling.value;
                }
            }*/
            var arr = $("#u_tag option:selected");
            var temp = new Array();
            for(var i = 0; i < arr.length; i++) {
                temp[i] = $(arr[i]).val();
            }
            var b = temp.join(",");
            json = {
                id:$("#id").val(),
                userName:$("#userNameu").val(),
                passwd:$("#passwdu").val(),
                type:$("#typeu").val(),
                lastTime:$("#lastTimeu").val(),
                privilege:b,
                lastIp:$("#lastIpu").val(),
                ext:$("#extu").val(),
                ext1:$("#ext1u").val()
            };
            $.ajax({
                "url": host+"/admin/save",
                "type": "get",
                "data": json,
                "dataType": "json",
                "success": function (list) {
                    DisplayListItems(list);
                },
                "error": function () {
                    //alert("出现错误！");
                }
            });
            function DisplayListItems(list) {
                window.location.reload();
            }

        });
    }
    /**
     * 添加时menu菜单的初始化
     */
  /*  $("#showadd").click(function(){
        initselect();
    });*/
    function initselect(){
        var ddl=$("#a_tag");
        $.ajax({
            "url": host+"/menu/findAll",
            "type": "get",
            "data": "",
            "dataType": "json",
            "success": function (list) {
                DisplayListItems(list,ddl);
            },
            "error": function () {
                //alert("出现错误！");
            }
        }) ;
        function DisplayListItems(list,ddl) {
            $.each(list.data, function (index, element) {
                var opt = "<option value='" + element.id + "'>" + element.menuName + "</option>";
				ddl.append(opt);
				$("#a_tag").selectpicker('refresh');
            })
        }
    }



    /**
     * 更改菜单初始化
     */
    function upTagInit() {
        //alert(2222);
        $.ajax({
            async: false,
            type: "get",
            url: host+"/menu/findAll",
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
                        var opt = "<option value='" + value.id + "'>" + value.menuName + "</option>";
                        dd1.append(opt);

                    });
                }
                $("#u_tag").selectpicker('refresh');
                //alert($("#u_tag option").length);
            }
        });
    }
});





