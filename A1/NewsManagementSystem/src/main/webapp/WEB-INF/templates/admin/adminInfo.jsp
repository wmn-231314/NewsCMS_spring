<!DOCTYPE html>
<html>
<html xmlns:th="http://www.thymeleaf.org" xmlns:sec="http://www.thymeleaf.org/extras/spring-security">
<head>
    <meta charset="UTF-8">
    <title>The Lemonade</title>
    <link th:fragment="favicon" rel="shortcut icon" th:href="@{/static/favico.ico}">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 注意：项目正式环境请勿引用该地址 -->
    <link th:href="@{/static/layui/css/layui.css}" rel="stylesheet">
</head>
<body>

<div class="layui-layout layui-layout-admin">
    <div th:replace="fragments/adminHeader :: adminHeader" />

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">账号管理</a>
                    <dl class="layui-nav-child">
                        <dd class="layui-nav-itemed"><a th:href="@{adminInfo}">管理员管理</a></dd>
                        <dd><a th:href="@{userInfo}" >用户管理</a></dd>
                        <dd><a th:href="@{editorInfo}">编辑管理</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a th:href="@{categoryInfo}">分类管理</a></li>
                <li class="layui-nav-item"><a href="">系统日志</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <table class="layui-hide" id="userList" lay-filter="user"></table>
        </div>
    </div>


    <footer class="footer layui-footer">
        <div th:replace="fragments/footer :: footer" />
    </footer>
</div>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="getCheckData">删除选中</button>
    </div>
</script>

<script type="text/html" id="barDemo">
    {{#  if(d.banned == 2){ }}
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="ban">解除</a>
    {{#  } else { }}
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="ban">禁言</a>
    {{#  } }}
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>


<script type="text/html" id="checkBanned">
    {{#  if(d.banned == 2){ }}
    <a class="layui-btn layui-btn-xs layui-btn-warm">已禁言</a>
    {{#  } else { }}
    <a class="layui-btn layui-btn-xs layui-btn-normal">正常</a>
    {{#  } }}
</script>


<!-- 注意：项目正式环境请勿引用该地址 -->
<script th:src="@{/static/layui/layui.js}"></script>

<script th:inline="none">
    layui.use(['form', 'layer', 'table'], function(){
        var form = layui.form,
            layer = layui.layer,
            $ = layui.jquery,
            table = layui.table;

        var tableIns = table.render({
            elem: '#userList'
            ,method:'post'
            ,url:'getAdminInfo'
            ,toolbar: '#toolbarDemo'
            ,cellMinWidth: 10 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}
                ,{field:'id', title: 'ID',fixed: 'left', sort: true}
                ,{field:'account',title: '账号'}
                ,{field:'name', title: '用户名', sort: true}
                ,{field:'permission', title: '权限'}
                ,{field:'lastLoginTime', title: '上次登陆时间',width:200, sort: true, templet: '<div>{{!d.lastLoginTime? "null":layui.util.toDateString(d.lastLoginTime,\'yyyy-MM-dd HH:mm:ss\')}}</div>'}
                ,{field:'registerTime', title: '注册时间',width:200, sort: true, templet: '<div>{{!d.registerTime? "null":layui.util.toDateString(d.registerTime,\'yyyy-MM-dd HH:mm:ss\')}}</div>'}
                ,{field:'banned', title: '状态', templet: '#checkBanned'}
                ,{field:'operation',fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
            ]]
            ,page: true
        });

        //工具栏事件
        table.on('toolbar(user)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'getCheckData':
                    var data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                    break;
            };
        });

        //行工具事件
        table.on('tool(user)', function(obj){
            var data = obj.data;
            console.log(obj)
            if(obj.event === 'del'){
                layer.confirm('确认删除', function(index){
                    $.post("delete/user",
                        {
                            "id": data.id
                        }, function (data) {
                            if(data==="success"){
                                parent.layer.msg('删除成功！', {icon: 1,time:1000,shade:0.2});
                                location.reload();
                            }else if(data === "same"){
                                parent.layer.msg('请勿删除自己的账号', {icon: 2,time:2000,shade:0.2});
                            }else{
                                parent.layer.msg('删除失败！', {icon: 2,time:2000,shade:0.2});
                            }
                        });
                    layer.close(index);
                    tableIns.reload();
                });
            } else if(obj.event === 'ban'){
                $.post("ban/user",
                    {
                        "id":data.id,
                        "banned": ((data.banned-1)^1)+1
                    }, function (data) {
                        if(data==="success"){
                            layer.msg('修改成功！', {icon: 1,time:1000,shade:0.2});
                            location.reload();
                        }else{
                            layer.msg('修改失败！', {icon: 2,time:2000,shade:0.2});
                        }
                        parent.reload();
                    });
                tableIns.reload();
            }
        });
    });
</script>

</body>
</html>